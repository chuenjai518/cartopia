package com.uow.Model;

import java.sql.Time;

public class Transaction {
	private int transactionID;
	private int driverID;
	private double totalAmount;
	private Time startTime;
	private Time endTime;
	private String licensePlateNum;

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}
	public int getTransactionID() {
		return transactionID;
	}
	public int getDriverID() {
		return driverID;
	}
	public void setDriverID(int driverID) {
		this.driverID = driverID;
	}
	public void setStartTime(Time startTime) {
		this.startTime = startTime;
	}
	public Time getStartTime() {
		return startTime;
	}
	public void setEndTime(Time endTime) {
		this.endTime = endTime;
	}
	public Time getEndTime() {
		return endTime;
	}
	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}
	public double getTotalAmount() {
		return totalAmount;
	}
	public String getLicensePlateNum() {
		return licensePlateNum;
	}
	public void setLicensePlateNum(String licensePlateNum) {
		this.licensePlateNum = licensePlateNum;
	}
	
	
}
