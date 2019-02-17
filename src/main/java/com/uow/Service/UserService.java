package com.uow.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.uow.DAO.UserDAO;
import com.uow.Model.CarPark;
import com.uow.Model.User;

@Service
public class UserService {
	
	@Autowired
	UserDAO userDAO;
	
	public void addUser(User user) {
		userDAO.addUser(user);
	}

}
