package com.uow.Model;

import java.sql.Time;

import org.springframework.stereotype.Component;

@Component
public class CarPark {
	private int carParkID;
	private String name;
	private String address;
	private Time openTime;
	private Time closeTime;
	private String description;
	private int privateCarSlot;
	private int privateCarFee;
	private int motorSlot;
	private int motorFee;
	private String photoLink;
	
	
	public int getCarParkID() {
		return carParkID;
	}
	public void setCarParkID(int carParkID) {
		this.carParkID = carParkID;
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
	public Time getOpenTime() {
		return openTime;
	}
	public void setOpenTime(Time openTime) {
		this.openTime = openTime;
	}
	public Time getCloseTime() {
		return closeTime;
	}
	public void setCloseTime(Time closeTime) {
		this.closeTime = closeTime;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getPrivateCarSlot() {
		return privateCarSlot;
	}
	public void setPrivateCarSlot(int privateCarSlot) {
		this.privateCarSlot = privateCarSlot;
	}
	public int getPrivateCarFee() {
		return privateCarFee;
	}
	public void setPrivateCarFee(int privateCarFee) {
		this.privateCarFee = privateCarFee;
	}
	public int getMotorSlot() {
		return motorSlot;
	}
	public void setMotorSlot(int motorSlot) {
		this.motorSlot = motorSlot;
	}
	public int getMotorFee() {
		return motorFee;
	}
	public void setMotorFee(int motorFee) {
		this.motorFee = motorFee;
	}
	public String getPhotoLink() {
		return photoLink;
	}
	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}

}
