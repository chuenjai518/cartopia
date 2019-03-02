package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookingRowMapper implements RowMapper<Booking> {
	
	@Override
	public Booking mapRow(ResultSet row, int rowNum) throws SQLException {
		Booking booking = new Booking();
		CarPark carPark = new CarPark();
		carPark.setAddress(row.getString("address"));
		carPark.setName(row.getString("name"));
		carPark.setPhotoLink(row.getString("photoLink"));
		booking.setBookingID(row.getInt("bookingID"));
		booking.setCarParkID(row.getInt("carParkID"));
		booking.setDriverCarID(row.getInt("driverCarID"));
		booking.setBookingTime(row.getTime("Time(bookingTime)"));
		return booking;
	}
	
}