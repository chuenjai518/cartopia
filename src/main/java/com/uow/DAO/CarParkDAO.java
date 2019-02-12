package com.uow.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.uow.Model.CarPark;

@Repository
public class CarParkDAO {
	@Autowired
	private JdbcTemplate db;

	public void addCarPark(CarPark carPark) {
		System.out.println("EXCUTE INSERT CarPark - " + carPark.getName());
		db.update("INSERT INTO carpark(name, address, description, openTime, closeTime) " + "Values (?,?,?,?,?)",
				carPark.getName(), carPark.getAddress(), carPark.getDescription(), carPark.getOpenTime(), carPark.getCloseTime());
	}
	
	public CarPark getCarPark(int carParkID) {
		
		String sql = "SELECT carParkID, name FROM CarPark WHERE carParkID = ?";
		RowMapper<CarPark> rowMapper = new BeanPropertyRowMapper<CarPark>(CarPark.class);
		CarPark carPark = db.queryForObject(sql, rowMapper, carParkID);
		return carPark;
	}
	

}
