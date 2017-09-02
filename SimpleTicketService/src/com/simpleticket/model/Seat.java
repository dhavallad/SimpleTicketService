/**
 * 
 */
package com.simpleticket.model;

import com.simpleticket.util.SeatStatus;

/**
 * @author dhavallad
 *
 */
public class Seat {

	private SeatStatus status;
	private String customerEmail;
	private final String number;

	/**
	 * 
	 * @param number
	 */
	public Seat(String number) {
		this.number = number;
		this.status = SeatStatus.AVAILABLE;
	}

	/**
	 * Return the status of current seat. e.g. Available, Hold, Reserve.
	 * 
	 * @return
	 */
	public SeatStatus getStatus() {
		return status;
	}

	/**
	 * Set the status of seat. e.g. Available, Hold, Reserve.
	 * 
	 * @param status
	 */
	public void setStatus(SeatStatus status) {
		this.status = status;
	}

	/**
	 * Return the email address of particular customer who has hold/reserve the
	 * seat.
	 * 
	 * @return customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * Set the customer's email address for particular seat.
	 * 
	 * @param customerEmail
	 */
	public void setCustomerEmail(String customerEmail) {
		this.customerEmail = customerEmail;
	}

	/**
	 * Change the status of Seat to HOLD.
	 */
	public void holdSeat() {
		this.status = SeatStatus.HOLD;
	}

	/**
	 * Change the status of Seat to RESERVED.
	 */
	public void reserveSeat() {
		this.status = SeatStatus.RESERVED;
	}

	/**
	 * Change the status of Seat to Available.
	 */
	public void releaseSeat() {
		this.status = SeatStatus.AVAILABLE;
		this.customerEmail = null;
	}

	/**
	 * @return the number
	 */
	public String getNumber() {
		return number;
	}

}
