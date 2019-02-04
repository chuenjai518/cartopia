package com.uow.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.CarParkDAO;
import com.uow.Model.CarPark;

@Service
public class CarParkService {
	
	@Autowired
	CarParkDAO carParkDAO;
	
	public int getCarParkID() {
		System.out.println("Get CarPark ID!!");
		int carparkID=1;
		
		return carparkID;
	}
	
	public void addCarPark(CarPark carpark) {
		carParkDAO.addCarPark(carpark);
	}

}
