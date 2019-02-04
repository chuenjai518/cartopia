package com.uow.Model;

import org.springframework.stereotype.Component;

@Component
public class CarParkSlot {
	private int carParkSlotID;
	private int carTypeID;
	private int statusID;
	private int carParkID;
	private int slotIndex;
	
	public int getCarParkSlotID() {
		return carParkSlotID;
	}
	public void setCarParkSlotID(int carParkSlotID) {
		this.carParkSlotID = carParkSlotID;
	}
	public int getCarTypeID() {
		return carTypeID;
	}
	public void setCarTypeID(int carTypeID) {
		this.carTypeID = carTypeID;
	}
	public int getStatusID() {
		return statusID;
	}
	public void setStatusID(int statusID) {
		this.statusID = statusID;
	}
	public int getCarParkID() {
		return carParkID;
	}
	public void setCarParkID(int carParkID) {
		this.carParkID = carParkID;
	}
	public int getSlotIndex() {
		return slotIndex;
	}
	public void setSlotIndex(int slotIndex) {
		this.slotIndex = slotIndex;
	}
	
	
}
