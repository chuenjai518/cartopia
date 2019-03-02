package com.uow.Model;

import java.sql.ResultSet;


import org.springframework.jdbc.core.RowMapper;

public class BookingRowMapper implements RowMapper<Booking> {
	
	@Override
	public Booking mapRow(ResultSet row, int rowNum) throws SQLException {
		Booking booking = new Booking();
		
		booking.setBookingID(row.getInt("bookingID"));
		booking.setCarParkID(row.getInt("carParkID"));
		booking.setDriverCarID(row.getInt("driverCarID"));
		booking.setBookingTime(row.getTime("Time(bookingTime)"));
		booking.setValid(row.getBool("valid"));
		booking.setCancel(row.getBool("cancel"));
		return booking;
	}
	
}