package com.uow.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.UserDAO;
import com.uow.Model.CarPark;
import com.uow.Model.Driver;
import com.uow.Model.User;
import com.uow.Model.Login;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO;
	
	public void addUser(User user) {
		userDAO.addUser(user);
	}
	
	public int loginProcess(Login login) {
		
		login = userDAO.checkLogin(login);
		
		if(login.getUserID() != 0) {
			System.out.println("OK userID:"+ login.getUserID());
			return login.getUserID();
		}else {
			System.out.println(login.getUserID());
			return 0;
		}
	}
	
	public boolean registerProcess(User user) {
		boolean valid = userDAO.checkUsername(user.getUsername());
		if(valid) {
			user.setRoleID(1);
			userDAO.registerProcess(user);
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
	

}
