package com.uow.Model;

import java.sql.Time;

import org.springframework.stereotype.Component;


@Component
public class Booking{
	private int bookingID;
	private int carParkID;
	private int driverCarID;
	private int carTypeID;
	private Time bookingTime;
	private String name;
	private String address;
	private String photoLink;
	private double credit;
	
	public int getCarTypeID() {
		return carTypeID;
	}

	public void setCarTypeID(int carTypeID) {
		this.carTypeID = carTypeID;
	}
	
	public double getCredit() {
		return credit;
	}

	public void setCredit(double credit) {
		this.credit = credit;
	}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhotoLink() {
		return photoLink;
	}

	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}

}