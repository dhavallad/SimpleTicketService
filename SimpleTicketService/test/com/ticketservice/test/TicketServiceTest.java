package com.ticketservice.test;

import org.junit.Assert;
import org.junit.Test;

import com.simpleticket.model.SeatHold;
import com.simpleticket.model.Venue;
import com.simpleticket.service.TicketServiceImpl;
import com.simpleticket.util.ErrorHandler;
import com.simpleticket.util.SeatStatus;

public class TicketServiceTest {

	private TicketServiceImpl service = new TicketServiceImpl();

	@Test
	public void testServices() throws InterruptedException {
		SeatHold sh1 = service.findAndHoldSeats(4, TestData.TEST_EMAIL_1);
		for (int i = 0; i < sh1.getSeatList().size(); i++) {
			Assert.assertEquals(SeatStatus.HOLD, Venue.getSingleSeat(0, i).getStatus());
			Assert.assertEquals(TestData.TEST_EMAIL_1, Venue.getSingleSeat(0, i).getCustomerEmail());
		}
		Assert.assertEquals(TestData.MAX_SEATS - 4, Venue.getAvailableSeats());

		Thread.sleep(5 * 1000);

		SeatHold sh2 = service.findAndHoldSeats(2, TestData.TEST_EMAIL_2);
		for (int i = 4; i < 4 + sh2.getSeatList().size(); i++) {
			Assert.assertEquals(SeatStatus.HOLD, Venue.getSingleSeat(0, i).getStatus());
			Assert.assertEquals(TestData.TEST_EMAIL_2, Venue.getSingleSeat(0, i).getCustomerEmail());
		}
		Assert.assertEquals(TestData.MAX_SEATS - 6, Venue.getAvailableSeats());

		service.reserveSeats(sh1.getSeatId(), TestData.TEST_EMAIL_1);
		for (int i = 0; i < sh1.getSeatList().size(); i++) {
			Assert.assertEquals(SeatStatus.RESERVED, Venue.getSingleSeat(0, i).getStatus());
			Assert.assertEquals(TestData.TEST_EMAIL_1, Venue.getSingleSeat(0, i).getCustomerEmail());
		}
		Assert.assertEquals(TestData.MAX_SEATS - 6, Venue.getAvailableSeats());

		Thread.sleep(25 * 1000);

		for (int i = 0; i < sh1.getSeatList().size(); i++) {
			Assert.assertEquals(SeatStatus.RESERVED, Venue.getSingleSeat(0, i).getStatus());
			Assert.assertEquals(TestData.TEST_EMAIL_1, Venue.getSingleSeat(0, i).getCustomerEmail());
		}

		for (int i = 4; i < 4 + sh2.getSeatList().size(); i++) {
			Assert.assertEquals(SeatStatus.AVAILABLE, Venue.getSingleSeat(0, i).getStatus());
			Assert.assertEquals(null, Venue.getSingleSeat(0, i).getCustomerEmail());
		}
		Assert.assertEquals(TestData.MAX_SEATS - 4, Venue.getAvailableSeats());
	}

	@Test
	public void testNumSeatsNotValid() {
		SeatHold seatHold = new SeatHold(TestData.TEST_EMAIL_1);
		Assert.assertTrue(service.isValidHoldRequest(-1, TestData.TEST_EMAIL_1, seatHold));
		Assert.assertEquals(String.format(ErrorHandler.INVALID_NUMBER_OF_SEATS, TestData.TEST_EMAIL_1, -1),
				seatHold.getMessage());
	}

	@Test
	public void testNotEnoughSeats() {
		SeatHold seatHold = new SeatHold(TestData.TEST_EMAIL_1);
		int seatNum = TestData.MAX_SEATS + 1;
		Assert.assertTrue(service.isValidHoldRequest(seatNum, TestData.TEST_EMAIL_1, seatHold));

		String expect = String.format(ErrorHandler.NOT_ENOUGH_SEATS_AVAILABLE, TestData.TEST_EMAIL_1, seatNum,
				TestData.MAX_SEATS);
		String actual = seatHold.getMessage();
		Assert.assertEquals(expect, actual);
	}

	@Test
	public void testEmptyEmail() {
		SeatHold seatHold = new SeatHold(TestData.TEST_EMAIL_1);
		int seatNum = TestData.MAX_SEATS + 1;
		Assert.assertTrue(service.isValidHoldRequest(seatNum, "", seatHold));
		Assert.assertEquals(ErrorHandler.HOLD_EMPTY_EMAIL, seatHold.getMessage());
	}

	@Test
	public void testReserveEmptyEmail() {
		Assert.assertEquals(ErrorHandler.RESERVE_EMPTY_EMAIL, service.isValidReserveRequest(1, ""));
		Assert.assertEquals(ErrorHandler.RESERVE_EMPTY_EMAIL, service.isValidReserveRequest(1, null));
		Assert.assertNull(service.isValidReserveRequest(1, TestData.TEST_EMAIL_1));
	}

	@Test
	public void testReserveInvalidID() {
		String actual = service.isValidReserveRequest(-1, TestData.TEST_EMAIL_1);
		String expect = String.format(ErrorHandler.INVALID_SEATHOLD_ID, TestData.TEST_EMAIL_1, -1);
		Assert.assertEquals(expect, actual);
	}

}
