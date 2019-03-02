package com.uow.Model;

import java.sql.Time;

import org.springframework.stereotype.Component;


@Component
public class Booking{
	private int bookingID;
	private int carParkID;
	private int driverCarID;
	private Time bookingTime;
	private boolean valid;
	private boolean cancel;

	public int getBookingID() {
		return bookingID;
	}
	
	public void setBookingID(int bookingID){
		this.bookingID= bookingID;
	}
	
	public int getCarParkID() {
			return carParkID;
		}

	public void setCarParkID(int carParkID) {
		this.carParkID = carParkID;
	}

	public int getDriverCarID() {
			return driverCarID;
		}

	public void setDriverCarID(int driverCarID) {
		this.driverCarID = driverCarID;
	}

	public Time getBookingTime() {
		return bookingTime;
	}
	public void setBookingTime(Time bookingTime) {
		this.bookingTime = bookingTime;
	}

	public boolean getValid() {
		return valid;
	}

	public void setValid(boolean valid) {
		this.valid = valid;
	}
	public boolean getCancel() {
		return cancel;
	}

	public void setCancel(boolean cancel) {
		this.cancel = cancel;
	}

}