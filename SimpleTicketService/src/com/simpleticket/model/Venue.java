/**
 * 
 */
package com.simpleticket.model;

/**
 * @author dhavallad
 *
 */
public class Venue {
	private final static int TOTAL_ROW = 10;
	private final static int TOTAL_COL = 10;
	private final static Seat[][] seats = new Seat[TOTAL_ROW][TOTAL_COL];
	private volatile static int seatsAvailable = TOTAL_COL * TOTAL_ROW;

	/**
	 * @return
	 */
	public static Seat[][] getSeats() {
		return seats;
	}

	static {
		for (int row = 0; row < seats.length; row++) {
			for (int col = 0; col < seats[0].length; col++) {
				Seat seat = new Seat(row + "-" + col);
				seats[row][col] = seat;
			}
		}
	}

	/**
	 * Return the available seats from the Venue.
	 * 
	 * @return
	 */
	public static int getAvailableSeats() {
		return seatsAvailable;
	}

	/**
	 * Return the single one seat based on row and column index.
	 * 
	 * @param row
	 *            Row index of Seat
	 * @param column
	 *            Column index of Seat
	 * @return
	 */
	public static Seat getSingleSeat(int row, int column) {
		return seats[row][column];
	}

	/**
	 * Function to hold the seats.
	 * 
	 * @param numSeats
	 *            number of seats need to hold.
	 */
	public static void holdSeats(int numSeats) {
		seatsAvailable -= numSeats;
	}

	/**
	 * Function to release the hold from the seats.
	 * 
	 * @param numSeats
	 *            number of seats need to be released.
	 */
	public static void releaseSeats(int numSeats) {
		seatsAvailable += numSeats;
	}
}
