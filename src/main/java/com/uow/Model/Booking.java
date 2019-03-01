package com.uow.Model;

import org.springframework.stereotype.Component;

@Component 
public class Booking {
	private int carParkID;
	private int driverCarID;
	
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
		
}
