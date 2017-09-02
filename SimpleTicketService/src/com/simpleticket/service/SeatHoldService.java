/**
 * 
 */
package com.simpleticket.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Timer;
import java.util.TimerTask;

import com.simpleticket.util.ErrorHandler;
import com.simpleticket.util.SeatHoldComparator;
import com.simpleticket.util.SeatStatus;
import com.simpleticket.model.Seat;
import com.simpleticket.model.SeatHold;
import com.simpleticket.model.Venue;

/**
 * @author dhavallad
 *
 */
public class SeatHoldService {
	private static final Queue<SeatHold> seatHoldQ = new PriorityQueue<>(new SeatHoldComparator());
	private static final long TIME_LIMIT = 20 * 1000;
	private static final long INTERVAL = 2 * 1000;
	private static boolean flag;

	public void addToQueue(SeatHold seatHold) {
		seatHoldQ.add(seatHold);
	}

	public boolean isRunning() {
		return flag;
	}

	/**
	 * Method to hold the numSeats in to Seats using customer email address.
	 * 
	 * @param numSeats
	 *            number of seats request to hold.
	 * @param customerEmail
	 *            customer email address
	 * @param seatHold
	 *            Obejct of SeatHold.
	 */
	public void holdSeats(int numSeats, String customerEmail, SeatHold seatHold) {
		int temp = numSeats;
		Seat[][] seats = Venue.getSeats();
		int row = 0;
		while (temp > 0) {
			for (int col = 0; col < seats[row].length && temp > 0; col++) {
				Seat seat = seats[row][col];
				if (seat.getStatus() == SeatStatus.AVAILABLE) {
					seat.setCustomerEmail(customerEmail);
					seat.holdSeat();

					seatHold.addSeatToList(seat.getNumber());
					temp--;
				}
			}
			row++;
		}

		seatHold.setMessage(
				String.format(ErrorHandler.HOLD_SEATS_SUCCESS, customerEmail, numSeats, seatHold.getSeatId()));
		seatHoldQ.add(seatHold);
		Venue.holdSeats(numSeats);
	}

	/**
	 * Method to reserve the seats using customer email address and seatHoldId.
	 * 
	 * @param seatHoldId
	 *            seat id to be reserved.
	 * @param customerEmail
	 *            customer email address.
	 * @return Reservation action message.
	 * 
	 */
	public String reserveSeats(int seatHoldId, String customerEmail) {
		Iterator<SeatHold> itr = seatHoldQ.iterator();
		String message = null;
		while (itr.hasNext()) {
			SeatHold sh = itr.next();

			if (sh.getSeatId() == seatHoldId && sh.getCustomerEmail().equals(customerEmail)) {
				List<Seat> seatList = seatNumToSeat(sh);
				for (Seat seat : seatList) {
					seat.reserveSeat();
				}

				itr.remove();
				message = String.format(ErrorHandler.RESERVE_SEAT_SUCCESS, customerEmail, seatHoldId);
				break;
			}

		}
		if (message == null)
			message = String.format(ErrorHandler.SEATHOLD_NOT_FOUND, customerEmail, seatHoldId);

		return message;
	}

	/**
	 * Seats that are on hold need to be reserved.
	 * 
	 * @param sh
	 * @return List of all reserved seat.
	 */
	private List<Seat> seatNumToSeat(SeatHold sh) {
		// TODO Auto-generated method stub
		List<Seat> li = new ArrayList<>();
		for (String seat : sh.getSeatList()) {
			String[] str = seat.split("-");
			int row = Integer.parseInt(str[0]);
			int col = Integer.parseInt(str[1]);
			li.add(Venue.getSingleSeat(row, col));
		}
		return li;
	}

	/**
	 * Seats that are need be released.
	 * 
	 * @param seatHold
	 *            SeatHold need to be released.
	 */
	public void releaseSeatHeld(SeatHold seatHold) {
		List<Seat> seatList = seatNumToSeat(seatHold);
		for (Seat seat : seatList) {
			seat.releaseSeat();
		}
		Venue.releaseSeats(seatList.size());
	}

	/**
	 * Method to keep track of timer to release the hold from seats.
	 */
	public void startTimer() {
		if (flag) {
			return;
		}
		flag = true;
		final Timer timer = new Timer();
		TimerTask task = new TimerTask() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				if (seatHoldQ.isEmpty()) {
					flag = false;
					timer.cancel();
				} else {
					long currentTime = System.currentTimeMillis();
					if (currentTime - seatHoldQ.peek().getTimeHeld() > TIME_LIMIT) {
						SeatHold sh_obj = seatHoldQ.poll();
						releaseSeatHeld(sh_obj);
					}

				}

			}
		};
		timer.schedule(task, 0, INTERVAL);

	}

}
