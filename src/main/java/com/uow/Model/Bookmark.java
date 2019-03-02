package com.uow.Model;

import org.springframework.stereotype.Component;

@Component 
public class Bookmark {
	private int carParkID;
	private int userID;
	
	public int getCarParkID() {
		return carParkID;
	}
	public void setCarParkID(int carParkID) {
		this.carParkID = carParkID;
	}
	public int getUserID() {
		return userID;
	}
	public void setUserID(int userID) {
		this.userID = userID;
	}
		
}
