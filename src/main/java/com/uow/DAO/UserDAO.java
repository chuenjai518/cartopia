package com.uow.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uow.Model.Bookmark;
import com.uow.Model.CarPark;
import com.uow.Model.CarParkRowMapper;
import com.uow.Model.Driver;
import com.uow.Model.DriverCar;
import com.uow.Model.DriverCarRowMapper;
import com.uow.Model.DriverRowMapper;
import com.uow.Model.Login;
import com.uow.Model.LoginRowMapper;
import com.uow.Model.User;
import com.uow.Model.UserRowMapper;
import com.uow.Model.Transaction;
import com.uow.Model.TransactionRowMapper;
import com.uow.Model.BookmarkRowMapper;

@Repository
public class UserDAO {
	@Autowired
	private JdbcTemplate db;

	public void addUser(User user) {
		System.out.println("EXCUTE INSERT User - " + user.getUsername());
		String sql = "INSERT INTO User(roleID, username, password, firstName, lastName, email) "
				+ "Values (?,?,?,?,?,?)";
		db.update(sql, user.getRoleID(), user.getUsername(), user.getPassword(), user.getFirstName(),
				user.getLastName(), user.getEmail());
	}
	
	public int getUserID(String username) {
		
		String sql = "Select userID from User where username = ?";
		
		int userID = (int) db.queryForObject(sql, int.class, username);
		
		return userID;
	}

	public void registerProcess(User user) {
		System.out.println("EXCUTE INSERT User - " + user.getUsername());
		String sql = "INSERT INTO User(roleID, username, password, firstName, lastName, email) "
				+ "Values (?,?,?,?,?,?)";
		db.update(sql, user.getRoleID(), user.getUsername(), user.getPassword(), user.getFirstName(),
				user.getLastName(), user.getEmail());
	}
	
	public void addDriver(int userID) {
		String sql = "INSERT INTO Driver(userID) " + "VALUES(?)";
		db.update(sql, userID);
	}

	public boolean checkUsername(String username) {
		boolean valid = false;
		String sql = "SELECT username FROM User WHERE username = ?";
		RowMapper<User> rowMapper = new UserRowMapper();
		try {
			User user = db.queryForObject(sql, rowMapper, username);
			System.out.println("Found User - " + user.getUsername());
		} catch (EmptyResultDataAccessException e) {
			valid = true;
		}
		return valid;
	}
	
	public void updateUserProcess(User user) {

		System.out.println("Update user ID -" + user.getUserID() + user.getFirstName());

		String sql = "Update User set firstname = ?, lastname = ?, email = ? where userID = ?";
		db.update(sql, user.getFirstName(), user.getLastName(), user.getEmail(), user.getUserID());
	}

	public User checkLogin(Login login) {
		System.out.println("checkLogin UserDAO");
		String sql = "SELECT userID, username, roleID, firstName, lastName, email FROM User WHERE username = ? AND password = ?";
		RowMapper<User> rowMapper = new UserRowMapper();
		try {
			User user = db.queryForObject(sql, rowMapper, login.getUsername(), login.getPassword());
			System.out.println("Found User - " + login.getUsername());
			return user;
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

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

	public List<User> getAllUser() {
		String sql = "SELECT roleID, userID, username, firstName, lastName, email FROM User";
		RowMapper<User> rowMapper = new UserRowMapper();
		return this.db.query(sql, rowMapper);
	}

	public List<CarPark> getCPOCarPark(int userID) {

		String sql = "SELECT cpo.carParkID, name, address, Time(openTime), Time(closeTime), description, privateCarSlot, privateCarFee, motorSlot, motorFee, photoLink FROM User u right join CarParkOwnerCarPark cpo on u.userID = cpo.userID right join CarPark cp on cpo.carparkID = cp.carparkID where u.userID = ?";
		try {
			RowMapper<CarPark> rowMapper = new CarParkRowMapper();
			return this.db.query(sql, rowMapper, userID);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	public void addCar(DriverCar car) {
		String sql = "INSERT INTO DriverCar(driverID, carTypeID, licensePlateNum) " + "Values(?, ?, ?)";
		db.update(sql, car.getDriverID(), car.getCarTypeID(), car.getLicensePlateNum());
	}

	public List<DriverCar> getAllCar() {
		String sql = "SELECT driverCarID, driverID, carTypeID, licensePlateNum FROM DriverCar";
		try {
			RowMapper<DriverCar> rowMapper = new DriverCarRowMapper();
			return this.db.query(sql, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public List<DriverCar> getAllCar(int driverID) {
		String sql = "SELECT driverCarID, driverID, carTypeID, licensePlateNum FROM DriverCar where driverID = ?";
		try {
			RowMapper<DriverCar> rowMapper = new DriverCarRowMapper();
			return this.db.query(sql, rowMapper, driverID);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public void updateCar(DriverCar car) {
		String sql = "UPDATE DriverCar SET carTypeID = ?, licensePlateNum = ? WHERE DriverCarID =?";

		db.update(sql, car.getCarTypeID(), car.getLicensePlateNum(), car.getDriverCarID());


	}

	public void bookmark(Bookmark bookmark) {
		String sql = "INSERT INTO userbookmark(carParkID, userID) " + "Values(?, ?)";
		db.update(sql, bookmark.getCarParkID(), bookmark.getUserID());
	}
	
	public List<Bookmark> getBookmark(int userID) {
		String sql = "SELECT u.userID, u.carParkID, c.name, c.photoLink FROM userbookmark u, carPark c WHERE UserID = " + userID + "and c.carParkID = u.carParkID";
		try {
			RowMapper<Bookmark> rowMapper = new BookmarkRowMapper();
			return this.db.query(sql, rowMapper, userID);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}

	public int countNewDriver() {
		int result;
		String sql = "SELECT COUNT(driverID) FROM Driver WHERE createDate >= CURDATE() + interval 1 MONTH;";
		result = db.queryForObject(sql, Integer.class);
		return result;
	}

	public void addComment() {
		String sql = "";

	}

	public String getComment() {
		String sql = "";
		String cm = "";
		return cm;
	}
	public void deleteCar(int driverCarID) {
		String sql = "DELETE FROM DriverCar WHERE driverCarID = ?";
		db.update(sql, driverCarID);
	}
	
	public void resetPassword(String password, int userID) {
		String sql = "UPDATE User SET password = ? WHERE userID =?";
		db.update(sql, password, userID);
	}
	
	public List<Transaction> getTransactionRecord(int driverID) {
		String sql = "SELECT transactionID, startTime, endTime, driverID, totalAmount, licensePlateNum FROM Transaction WHERE driverID = ?";
		try {
			RowMapper<Transaction> rowMapper = new TransactionRowMapper();
			return db.query(sql, rowMapper, driverID);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void bookCarPark(int driverCarID,int carParkID ) {
		String sql ="INSERT INTO Booking (carParkID,driverCarID)"+"Values(?, ?)";
		db.update(sql, carParkID, driverCarID);
	}

}
