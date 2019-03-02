package com.uow.Model;

import org.springframework.stereotype.Component;

@Component 
public class DriverBookmark {
	private int carParkID;
	private int userID;
	private String name;
	private String photoLink;
	
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPhotoLink() {
		return photoLink;
	}
	public void setPhotoLink(String photoLink) {
		this.photoLink = photoLink;
	}
		
}
