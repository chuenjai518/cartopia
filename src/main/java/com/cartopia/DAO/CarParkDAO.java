package com.cartopia.DAO;


import java.sql.ResultSet;
import java.sql.SQLException;

import com.cartopia.Entity.CarPark;
import com.cartopia.Database.*;

public class CarParkDAO {

	Database db;
	
	public CarPark getCarPark(int id) throws SQLException {
		CarPark temp = new CarPark();

		ResultSet rs = db.GetResultSet("Select name from CarPark where carParkid = " + id);
		
		while(rs.next()) {
			temp.setCarParkID(id);
		}
		
		return temp;
	}
	
	
}
