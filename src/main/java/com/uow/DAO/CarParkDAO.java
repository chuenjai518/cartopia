package com.uow.DAO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uow.Model.CarPark;
import com.uow.Model.CarParkRowMapper;

@Repository
public class CarParkDAO {
	@Autowired
	private JdbcTemplate db;

	public void addCarPark(CarPark carPark) {
		System.out.println("EXCUTE INSERT CarPark - " + carPark.getName());
		db.update(
				"INSERT INTO CarPark(name, address, description, openTime, closeTime, privateCarSlot, privateCarFee, motorSlot, motorFee, photoLink) "
						+ "Values (?,?,?,?,?,?,?,?,?)",
				carPark.getName(), carPark.getAddress(), carPark.getDescription(), carPark.getOpenTime(),
				carPark.getCloseTime(), carPark.getPrivateCarSlot(), carPark.getPrivateCarFee(), carPark.getMotorSlot(),
				carPark.getMotorFee());
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

	public int getCarparkRealTimeSpace(CarPark carpark) {
		int result;
		String sql = "SELECT COUNT(CarParkSlotID) FROM CarParkSlotInfo WHERE carParkID = " + carpark.getCarParkID()
				+ "and status = 'free';";
		String sql2 = "SELECT NumOfSlot FROM CarParkSlotInfo WHERE carParkID = " + carpark.getCarParkID();
		result = db.queryForObject(sql2, Integer.class) - db.queryForObject(sql, Integer.class);
		return result;
	}
}
