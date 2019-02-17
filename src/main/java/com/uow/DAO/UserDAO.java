package com.uow.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uow.Model.CarPark;
import com.uow.Model.User;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate db;
	
	public void addUser(User user) {
		System.out.println("EXCUTE INSERT User - " + user.getUsername());
		db.update("INSERT INTO User(roleID, username, password, firstName, lastName, email) " + "Values (?,?,?,?,?,?)",
				user.getRoleID(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(), user.getEmail());
	}

}
