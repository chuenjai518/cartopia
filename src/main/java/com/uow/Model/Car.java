package com.uow.Model;

import org.springframework.stereotype.Component;

@Component 
public class Car {
	private int driverID;
	private int carTypeID;
	private String licensePlateNum;
	
	public int getDriverID() {
		return driverID;
	}
	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}
	public int getCarTypeID() {
		return carTypeID;
	}
	public void setCarTypeID(int carTypeID) {
		this.carTypeID = carTypeID;
	}
	public String getLicensePlateNum() {
		return licensePlateNum;
	}
	public void setLicensePlateNum(String licensePlateNum) {
		this.licensePlateNum = licensePlateNum;
	}
	
	
	
}
