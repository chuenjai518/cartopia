package com.uow.DAO;

import java.util.List;
import java.sql.Time;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uow.Model.Booking;
import com.uow.Model.BookingRowMapper;
import com.uow.Model.Bookmark;
import com.uow.Model.BookmarkRowMapper;
import com.uow.Model.CarPark;
import com.uow.Model.CarParkRowMapper;
import com.uow.Model.Comment;
import com.uow.Model.CommentRowMapper;
import com.uow.Model.User;
import com.uow.Model.UserRowMapper;

@Repository
public class CarParkDAO {
	@Autowired
	private JdbcTemplate db;

	public int addCarPark(CarPark carPark) {
		System.out.println("EXCUTE INSERT CarPark - " + carPark.getName());
		db.update(
				"INSERT INTO CarPark(name, address, description, openTime, closeTime, privateCarSlot, privateCarFee, motorSlot, motorFee, photoLink) "
						+ "Values (?,?,?,?,?,?,?,?,?)",
				carPark.getName(), carPark.getAddress(), carPark.getDescription(), carPark.getOpenTime(),
				carPark.getCloseTime(), carPark.getPrivateCarSlot(), carPark.getPrivateCarFee(), carPark.getMotorSlot(),
				carPark.getMotorFee());
		
		String sql = "Select carParkID from CarPark where address = ?";
		
		return (int) db.queryForObject(sql, int.class, carPark.getAddress());
	}

	public CarPark getCarPark(int carParkID) {
		CarPark carPark = new CarPark();
		String sql = "SELECT carParkID, name, address, Time(openTime), Time(closeTime), Description, privateCarSlot, privateCarFee, motorSlot, motorFee, photoLink FROM CarPark WHERE carParkID = ?";
		RowMapper<CarPark> rowMapper = new CarParkRowMapper();
		try {
			carPark = db.queryForObject(sql, rowMapper, carParkID);
			System.out.println("Found CarPark");
		} catch (EmptyResultDataAccessException e) {

		}

		return carPark;
	}

	public List<CarPark> getAllCarPark() {
		String sql = "SELECT carParkID, name, address, Time(openTime), Time(closeTime), Description, privateCarSlot, privateCarFee, motorSlot, motorFee, photoLink FROM CarPark";
		RowMapper<CarPark> rowMapper = new CarParkRowMapper();
		return this.db.query(sql, rowMapper);
	}
	
	public List<CarPark> getExceptCarPark(int userID) {
		String sql = "SELECT carParkID, name, address, Time(openTime), Time(closeTime), Description, privateCarSlot, privateCarFee, motorSlot, motorFee, photoLink FROM CarPark Where carParkID Not in(Select carParkID FROM userbookmark where userId =?)";
		
		RowMapper<CarPark> rowMapper = new CarParkRowMapper();
		return this.db.query(sql, rowMapper, userID);
	}
	
	public List<CarPark> getBookMarkCarPark(int userID) {
		String sql = "SELECT carParkID, name, address, Time(openTime), Time(closeTime), Description, privateCarSlot, privateCarFee, motorSlot, motorFee, photoLink FROM CarPark Where carParkID in(Select carParkID FROM userbookmark where userId =?)";
		
		RowMapper<CarPark> rowMapper = new CarParkRowMapper();
		return this.db.query(sql, rowMapper, userID);
	}

	public void updateCarPark(CarPark carPark) {
		System.out.println("EXCUTE INSERT CarParkID - " + carPark.getCarParkID());
		db.update(
				"UPDATE CarPark SET name = ? , description = ? , openTime = ? , closeTime = ?, privateCarSlot = ?, privateCarFee = ?, motorSlot = ?, motorFee = ?, photoLink = ? WHERE carParkID = ?",
				carPark.getName(), carPark.getDescription(), carPark.getOpenTime(), carPark.getCloseTime(),
				carPark.getPrivateCarSlot(), carPark.getPrivateCarFee(), carPark.getMotorSlot(), carPark.getMotorFee(),carPark.getPhotoLink(),
				carPark.getCarParkID());
		}

	public void deleteCarPark(int carParkID) {
		db.update("DELETE FROM CarPark WHERE carParkID = ?", carParkID);

	}

	/*public int getCarparkRealTimeSpace(int carParkID) {
		int result;
		String sql = "SELECT numOfSlot FROM CarParkSlotInfo WHERE carParkID = " + carParkID;
		String sql2 = "SELECT COUNT(i.carParkID) FROM CarParkSlotInfo i, CarParkSlot s WHERE i.carParkID = " + carParkID + "and s.statusID = 1 and i.carParkID = s.carParkID;";
		result = db.queryForObject(sql, Integer.class) - db.queryForObject(sql2, Integer.class);
		return result;
	}*/
	
	
	public List<Bookmark> getBookmark(int userID) {
		String sql = "SELECT userID, carParkID FROM userbookmark WHERE userID = ?";
		try {
			RowMapper<Bookmark> rowMapper = new BookmarkRowMapper();
			return this.db.query(sql, rowMapper, userID);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public List<Comment> getComment(int carParkID) {
		String sql = "SELECT userID, carParkID, commentID, comment FROM Comment WHERE carParkID = ?";
		try {
			RowMapper<Comment> rowMapper = new CommentRowMapper();
			return this.db.query(sql, rowMapper, carParkID);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void addComment(Comment comment) {
		String sql = "INSERT INTO Comment (commentID, comment, userID, carParkID) values (?,?,?,?)";
		db.update(sql, comment.getCommentID(), comment.getComment(), comment.getUserID(), comment.getCarParkID());
	}
	
	public List<User> getCPOList(){
		String sql = "Select userID, roleID, username, password, firstName, lastName, email From User where roleID = 3";
		try {
			RowMapper<User> rowMapper = new UserRowMapper();
			return this.db.query(sql, rowMapper);
		} catch (EmptyResultDataAccessException e) {
			return null;
		}
	}
	
	public void carParkToOwner(int userID, int carParkID) {
		String sql = "Insert INTO CarParkOwnerCarPark(userID, carParkID) values(?,?)";
		db.update(sql, userID, carParkID);
	}
	
	public Booking getBookingDetail(int userID) {
		String sql1 = "SELECT Time(NOW());";
		String sql = "SELECT d.credit FROM Driver WHERE userID = ?;";
		Time bookingTime = db.queryForObject(sql1, Time.class);
		double credit = db.queryForObject(sql, Integer.class, userID);
		Booking booking = new Booking();
		booking.setBookingTime(bookingTime);
		booking.setCredit(credit);
		return booking;
	}
}
