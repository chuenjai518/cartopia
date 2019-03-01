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
public class CarParkDAO{
	@Autowired
	private JdbcTemplate db;

	public void addCarPark(CarPark carPark) {
		System.out.println("EXCUTE INSERT CarPark - " + carPark.getName());
		db.update("INSERT INTO carpark(name, address, description, openTime, closeTime, Description) " + "Values (?,?,?,?,?)",
				carPark.getName(), carPark.getAddress(), carPark.getDescription(), carPark.getOpenTime(), carPark.getCloseTime());
	}
	
	public CarPark getCarPark(int carParkID) {
		CarPark carPark = new CarPark();
		String sql = "SELECT carParkID, name, address, Time(openTime), Time(closeTime), Description FROM CarPark WHERE carParkID = ?";
		RowMapper<CarPark> rowMapper = new CarParkRowMapper();
		try {
			carPark = db.queryForObject(sql, rowMapper, carParkID);
			System.out.println("Found CarPark");
		} catch (EmptyResultDataAccessException e) {

		}
		
		return carPark;
	}
	
	public List<CarPark> getAllCarPark(){
		String sql = "SELECT carParkID, name, address, Time(openTime), Time(closeTime), Description FROM CarPark";
		RowMapper<CarPark> rowMapper = new CarParkRowMapper();
		return this.db.query(sql,rowMapper);
	}
	
	public void editCarPark(CarPark carPark) {
	System.out.println("EXCUTE INSERT CarParkID - "+ carPark.getCarParkID());
	db.update("UPDATE carpark SET name = ? , address = ? , description = ? , openTime = ? , closeTime = ? WHERE carParkID = ?" ,
			carPark.getName(), carPark.getAddress(), carPark.getDescription(), carPark.getOpenTime(), carPark.getCloseTime());
	}

	public void deleteCarPark(CarPark carPark) {
		System.out.println("EXCUTE INSERT CarParkID to delete CarPark - "+ carPark.getCarParkID());
		db.update("DELETE FROM carPark WHERE carParkID = ?",
				carPark.getCarParkID());
				
	}
}
