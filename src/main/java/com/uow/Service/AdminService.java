package com.uow.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.UserDAO;
import com.uow.Model.User;

@Service
public class AdminService {

	@Autowired
	UserDAO userDAO;
	
	public boolean createUserProcess(User user) {
		
		boolean valid = userDAO.checkUsername(user.getUsername());
		if(valid) {
			userDAO.registerProcess(user);
		}
		return valid;
		
	}
}
