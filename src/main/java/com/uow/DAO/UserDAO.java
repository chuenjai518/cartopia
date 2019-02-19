package com.uow.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uow.Model.CarPark;
import com.uow.Model.CarParkRowMapper;
import com.uow.Model.Driver;
import com.uow.Model.DriverRowMapper;
import com.uow.Model.Login;
import com.uow.Model.LoginRowMapper;
import com.uow.Model.User;
import com.uow.Model.UserRowMapper;

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
			System.out.println("Found User - " + login.getUsername());
		} catch (EmptyResultDataAccessException e) {
		}
		return login;
	}

	public User getUserInfo(int userID) {
		User user = new User();
		String sql = "SELECT roleID, userID, username, firstName, lastName, email FROM User WHERE userID = ?";
		RowMapper<User> rowMapper = new UserRowMapper();
		try {
			user = db.queryForObject(sql, rowMapper, userID);
			System.out.println("Found User - " + user.getUsername());
		} catch (EmptyResultDataAccessException e) {

		}
		return user;
	}

	public Driver getDriverInfo(int userID) {
		Driver driver = new Driver();
		String sql = "SELECT driverID, userID, credit FROM Driver WHERE userID = ?";
		RowMapper<Driver> rowMapper = new DriverRowMapper();
		try {
			driver = db.queryForObject(sql, rowMapper, userID);
			System.out.println("Found User - " + driver.getDriverID());
		} catch (EmptyResultDataAccessException e) {

		}
		return driver;
	}

	public boolean addCredit(int driverID, int amount) {
		boolean success = false;
		if (driverID != 0) {
			String sql = "UPDATE Driver set credit = credit + ? where driverID = ?";
			try {
				db.update(sql, amount, driverID);
				System.out.println(driverID + " has increase " + amount + " credit");
				success = true;
			} catch (EmptyResultDataAccessException e) {

			}
		}
		return success;
	}

}