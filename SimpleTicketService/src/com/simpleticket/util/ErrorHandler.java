package com.simpleticket.util;

public class ErrorHandler {
	public static final String NOT_ENOUGH_SEATS_AVAILABLE = "Customer(%s). Not enough seats available. Required %d, Currently Available %d.";
	public static final String INVALID_NUMBER_OF_SEATS = "Customer(%s). Number of seats required is invalid : %d.";
	public static final String HOLD_SEATS_SUCCESS = "Customer(%s). %d Seats held. Seat hold id %d.";
	public static final String HOLD_EMPTY_EMAIL = "Email address to hold seats is empty.";
	public static final String INVALID_SEATHOLD_ID = "Customer(%s). SeatHold ID to reserve seats is invalid: %d";
	public static final String RESERVE_EMPTY_EMAIL = "Email address to reserve seats is empty.";
	public static final String RESERVE_SEAT_SUCCESS = "Customer(%s). Seat hold id: %d reserved.";
	public static final String SEATHOLD_NOT_FOUND = "Customer(%s). Seat hold id: %d not found.";
}
