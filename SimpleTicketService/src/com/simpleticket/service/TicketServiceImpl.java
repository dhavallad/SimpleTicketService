/**
 * 
 */
package com.simpleticket.service;

import com.simpleticket.model.SeatHold;
import com.simpleticket.model.Venue;
import com.simpleticket.util.ErrorHandler;

/**
 * @author dhavallad
 *
 */
public class TicketServiceImpl implements TicketService {

	private final SeatHoldService seatHoldServiceObj = new SeatHoldService();

	@Override
	public int numSeatsAvailable() {
		// TODO Auto-generated method stub
		return Venue.getAvailableSeats();
	}

	@Override
	public synchronized SeatHold findAndHoldSeats(int numSeats, String customerEmail) {
		// TODO Auto-generated method stub
		SeatHold seatToHold = new SeatHold(customerEmail);

		if (isValidHoldRequest(numSeats, customerEmail, seatToHold)) {
			return seatToHold;
		}

		seatHoldServiceObj.holdSeats(numSeats, customerEmail, seatToHold);
		seatHoldServiceObj.startTimer();
		return seatToHold;
	}

	@Override
	public synchronized String reserveSeats(int seatHoldId, String customerEmail) {
		// TODO Auto-generated method stub
		String message = isValidReserveRequest(seatHoldId, customerEmail);
		if (message != null) {
			return message;
		}

		return seatHoldServiceObj.reserveSeats(seatHoldId, customerEmail);
	}

	/**
	 * Check if Request for hold is valid or not.
	 * 
	 * @param numSeats
	 *            number of seats requested.
	 * @param customerEmail
	 *            customer email address.
	 * @param seatToHold
	 *            SeatHold Object to hold.
	 * @return true or false
	 * 
	 */
	public boolean isValidHoldRequest(int numSeats, String customerEmail, SeatHold seatToHold) {

		int availableSeats = Venue.getAvailableSeats();

		if (customerEmail == null || customerEmail.isEmpty()) {
			seatToHold.setMessage(ErrorHandler.HOLD_EMPTY_EMAIL);
			return true;
		} else if (availableSeats < numSeats) {
			seatToHold.setMessage(
					String.format(ErrorHandler.NOT_ENOUGH_SEATS_AVAILABLE, customerEmail, numSeats, availableSeats));
			return true;
		} else if (numSeats <= 0) {
			seatToHold.setMessage(String.format(ErrorHandler.INVALID_NUMBER_OF_SEATS, customerEmail, numSeats));
			return true;
		}

		return false;
	}

	/**
	 * Check if Request for reserve is valid or not.
	 * 
	 * @param SeatHoldId
	 *            seat id of SeatHold object.
	 * @param customerEmail
	 *            customer email address.
	 * @return error message or null if its valid.
	 */
	public String isValidReserveRequest(int seatHoldId, String customerEmail) {
		if (customerEmail == null || customerEmail.isEmpty()) {
			return ErrorHandler.RESERVE_EMPTY_EMAIL;
		} else if (seatHoldId < 0) {
			return String.format(ErrorHandler.INVALID_SEATHOLD_ID, customerEmail, seatHoldId);
		}

		return null;
	}

}
