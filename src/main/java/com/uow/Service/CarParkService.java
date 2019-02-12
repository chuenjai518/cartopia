package com.uow.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.CarParkDAO;
import com.uow.Model.CarPark;

@Service
public class CarParkService {
	
	@Autowired
	CarParkDAO carParkDAO;
	
	public CarPark getCarPark(int carParkID) {
		System.out.println("Get CarPark!!");
		
		CarPark carPark = carParkDAO.getCarPark(carParkID);
		return carPark;
	}
	
	public void addCarPark(CarPark carpark) {
		carParkDAO.addCarPark(carpark);
	}

}
