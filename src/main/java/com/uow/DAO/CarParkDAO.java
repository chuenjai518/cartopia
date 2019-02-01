package com.uow.DAO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.uow.Model.CarPark;

@Repository
public class CarParkDAO {
	@Autowired
	private JdbcTemplate db;

	public void addCarPark(CarPark carpark) {
		System.out.println("EXCUTE INSERT MEMBER");
		db.update("INSERT INTO carpark(name, address) " + "Values (?,?)", carpark.getName(), carpark.getAddress());

	}

}
