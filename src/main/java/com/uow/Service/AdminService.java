package com.uow.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.CarParkDAO;
import com.uow.DAO.UserDAO;
import com.uow.Model.CarPark;
import com.uow.Model.User;

@Service
public class AdminService {

	@Autowired
	UserDAO userDAO;
	CarParkDAO carParkDAO;
	
	public boolean createUserProcess(User user) {
		
		boolean valid = userDAO.checkUsername(user.getUsername());
		if(valid) {
			userDAO.registerProcess(user);
			int userID = userDAO.getUserID(user.getUsername());
			userDAO.addDriver(userID);
		}
		return valid;
		
	}
	
	public int countNewDriver() {
		return userDAO.countNewDriver();
	}
	
	public void updateUserProcess(User user) {
		userDAO.updateUserProcess(user);
	}
}
