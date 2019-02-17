package com.uow.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uow.Model.CarPark;
import com.uow.Model.CarParkRowMapper;
import com.uow.Model.Login;
import com.uow.Model.LoginRowMapper;
import com.uow.Model.User;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate db;

	public void addUser(User user) {
		System.out.println("EXCUTE INSERT User - " + user.getUsername());
		db.update("INSERT INTO User(roleID, username, password, firstName, lastName, email) " + "Values (?,?,?,?,?,?)",
				user.getRoleID(), user.getUsername(), user.getPassword(), user.getFirstName(), user.getLastName(),
				user.getEmail());
	}

	public Login checkLogin(Login login) {
		System.out.println("checkLogin UserDAO");
		String sql = "SELECT userID, username, roleID FROM User WHERE username = ? AND password = ?";
		RowMapper<Login> rowMapper = new LoginRowMapper();
		try {
			login = db.queryForObject(sql, rowMapper, login.getUsername(), login.getPassword());
			System.out.println("Found User");
			return login;
		} catch (EmptyResultDataAccessException e) {
			return login;
		}
	}

}
