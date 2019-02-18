package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DriverRowMapper implements RowMapper<Driver> {

	@Override
	public Driver mapRow(ResultSet row, int rowNum) throws SQLException {
		System.out.println("Driver Mapper");
		Driver driver = new Driver();
		driver.setDriverID(row.getInt("driverID"));
		driver.setUserID(row.getInt("userID"));
		driver.setCredit(row.getInt("credit"));

		return driver;
	}

}
