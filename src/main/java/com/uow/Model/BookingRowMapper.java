package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookingRowMapper implements RowMapper<Booking> {
	
	@Override
	public Booking mapRow(ResultSet row, int rowNum) throws SQLException {
		Booking booking = new Booking();
		booking.setAddress(row.getString("address"));
		booking.setName(row.getString("name"));
		booking.setPhotoLink(row.getString("photoLink"));
		booking.setCredit(row.getDouble("credit"));
		booking.setBookingID(row.getInt("bookingID"));
		booking.setCarParkID(row.getInt("carParkID"));
		booking.setCarTypeID(row.getInt("carTypeID"));
		booking.setDriverCarID(row.getInt("driverCarID"));
		booking.setBookingTime(row.getTime("Time(bookingTime)"));
		return booking;
	}
	
}