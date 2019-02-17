package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class LoginRowMapper implements RowMapper<Login> {

	// userID, username, password, roleID
	@Override
	public Login mapRow(ResultSet row, int rowNum) throws SQLException {
		System.out.println("checkLogin Mapper");
		Login login = new Login();
		login.setRoleID(row.getInt("roleID"));
		login.setUserID(row.getInt("userID"));
		login.setUsername(row.getString("username"));

		return login;
	}

}
