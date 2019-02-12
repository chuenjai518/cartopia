package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



public class CarParkRowMapper implements RowMapper<CarPark>{

		@Override
		public CarPark mapRow(ResultSet row, int rowNum) throws SQLException {
			CarPark carPark = new CarPark();
			carPark.setCarParkID(row.getInt("carParkID"));
			carPark.setName(row.getString("name"));
			return carPark;
		}

	

}
