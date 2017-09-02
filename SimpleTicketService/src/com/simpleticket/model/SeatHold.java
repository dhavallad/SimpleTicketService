/**
 * 
 */
package com.simpleticket.model;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author dhavallad
 *
 */
public class SeatHold {
	// For Atomic Counter
	private static final AtomicInteger counter = new AtomicInteger(0);
	private final int seatId;
	private final String customerEmail;
	private final Long timeHeld;
	private final List<String> seatsList;
	private String message;

	public SeatHold(String email) {
		this.seatId = counter.incrementAndGet();
		this.customerEmail = email;
		this.timeHeld = System.currentTimeMillis();
		seatsList = new ArrayList<>();
	}

	/**
	 * Return seat id of Seat been held.
	 * 
	 * @return the seatId
	 */
	public int getSeatId() {
		return seatId;
	}

	/**
	 * Return email address of Seat owner..
	 * 
	 * @return the customerEmail
	 */
	public String getCustomerEmail() {
		return customerEmail;
	}

	/**
	 * @return the timeHeld
	 */
	public Long getTimeHeld() {
		return timeHeld;
	}

	/**
	 * @return the seatList
	 */
	public List<String> getSeatList() {
		return seatsList;
	}

	/**
	 * 
	 * @param seat
	 */
	public void addSeatToList(String seat) {
		seatsList.add(seat);
	}

	/**
	 * @return the message
	 */
	public String getMessage() {
		return message;
	}

	/**
	 * @param message
	 *            the message to set
	 */
	public void setMessage(String message) {
		this.message = message;
	}

}
