package com.uow.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.CarParkDAO;
import com.uow.Model.Booking;
import com.uow.Model.Bookmark;
import com.uow.Model.CarPark;
import com.uow.Model.Comment;
import com.uow.Model.User;

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
	public List<CarPark> getExceptCarPark(int userID){
		return carParkDAO.getExceptCarPark(userID);
		
	}
	public List<CarPark> getBookMarkCarPark(int userID){
		return carParkDAO.getBookMarkCarPark(userID);
		
	}
	
	public void updateCarPark(CarPark carPark) {
		carParkDAO.updateCarPark(carPark);
	}
	
	public void addCarPark(CarPark carPark, int userID) {
		int carParkID = carParkDAO.addCarPark(carPark);
		carParkDAO.carParkToOwner(userID, carParkID);
	}
	
	public void deleteCarPark(int carParkID){
		carParkDAO.deleteCarPark(carParkID);
	}
	
	/*public int getCarparkRealTimeSpace(int carParkID) {
		return carParkDAO.getCarparkRealTimeSpace(carParkID);
	}*/
	public List<Bookmark> getBookmark(int userID){
		return carParkDAO.getBookmark(userID);
	}
	public List<Comment> getComment(int carParkID){
		return carParkDAO.getComment(carParkID);
	}
	public void addComment(Comment comment){
		carParkDAO.addComment(comment);
	}
	
	public List<User> getCPOList(){
		return carParkDAO.getCPOList();
	}
	
	public void carParkToOwner(int userID, int carParkID) {
		carParkDAO.carParkToOwner(userID, carParkID);
	}
	public List<Booking> getBookingDetail(int userID){
		return carParkDAO.getBookingDetail(userID);
	}
}
