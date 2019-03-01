package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DriverCarRowMapper implements RowMapper<DriverCar> {

	@Override
	public DriverCar mapRow(ResultSet row, int rowNum) throws SQLException {
		DriverCar car = new DriverCar();
		car.setDriverCarID(row.getInt("driverCarID"));
		car.setDriverID(row.getInt("driverID"));
		car.setCarTypeID(row.getInt("carTypeID"));
		car.setLicensePlateNum(row.getString("licensePlateNum"));

		return car;
	}

}
