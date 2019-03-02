package com.uow.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.Model.Bookmark;
import com.uow.DAO.UserDAO;
import com.uow.Model.CarPark;
import com.uow.Model.Driver;
import com.uow.Model.DriverCar;
import com.uow.Model.User;
import com.uow.Model.Login;
import com.uow.Model.Transaction;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO;
	
	public void addUser(User user) {
		userDAO.addUser(user);
	}
	
	public User loginProcess(Login login) {
		
		User user = userDAO.checkLogin(login);
		
		if(user != null) {
			System.out.println("OK username:"+ login.getUsername());
			return user;
		}else {
			return null;
		}
	}
	
	public boolean registerProcess(User user) {
		boolean valid = userDAO.checkUsername(user.getUsername());
		if(valid) {
			user.setRoleID(1);
			userDAO.registerProcess(user);
			int userID = userDAO.getUserID(user.getUsername());
			userDAO.addDriver(userID);
		}
		return valid;
	}
	
	public User getUserInfo(int userID) {
		return userDAO.getUserInfo(userID);
	}
	
	public Driver getDriverInfo(int userID) {
		return userDAO.getDriverInfo(userID);
	}
	
	public boolean addCredit(int driverID, int amount) {
		return userDAO.addCredit(driverID, amount);
	}
	
	public List<User> getAllUser(){
		return userDAO.getAllUser();
	}
	
	public List<CarPark> getCPOCarPark(int userID){
		return userDAO.getCPOCarPark(userID);
	}
	
	public void addCar(DriverCar car) {
		userDAO.addCar(car);
	}
	
	public List<DriverCar> getAllCar(int driverID){
		return userDAO.getAllCar(driverID);
	}
	
	public List<DriverCar> getAllCar(){
		return userDAO.getAllCar();
	}
	public void updateCar(DriverCar car) {
		userDAO.updateCar(car);
	}
	public void deleteCar(int driverCarID) {
		userDAO.deleteCar(driverCarID);
	}
	public void bookmark(Bookmark bookmark) {
		userDAO.bookmark(bookmark);
	}
	
	public void resetPassword(String password, int userID) {
		userDAO.resetPassword(password, userID);
	}
	
	public void updateUserProcess(User user) {
		userDAO.updateUserProcess(user);
	}
	public Transaction getTransactionRecord(int driverID) {
		return getTransactionRecord(driverID);
	}

	public List<Bookmark> getBookmark(int userID) {
		return getBookmark(userID);
	}

}
