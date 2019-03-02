package com.uow.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.CarParkDAO;
import com.uow.Model.Bookmark;
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
	
	public List<CarPark> getAllCarPark(){
		return carParkDAO.getAllCarPark();
		
	}
	
	public void updateCarPark(CarPark carPark) {
		carParkDAO.updateCarPark(carPark);
	}
	
	public void addCarPark(CarPark carPark) {
		carParkDAO.addCarPark(carPark);
	}
	
	public void deleteCarPark(int carParkID){
		carParkDAO.deleteCarPark(carParkID);
	}
	
	public int getCarparkRealTimeSpace(CarPark carPark) {
		return carParkDAO.getCarparkRealTimeSpace(carPark);
	}
	public List<Bookmark> getBookmark(int userID){
		return getBookmark(userID);
	}
}
