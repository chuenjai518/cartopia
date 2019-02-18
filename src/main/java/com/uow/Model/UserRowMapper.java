package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class UserRowMapper implements RowMapper<User> {

	@Override
	public User mapRow(ResultSet row, int rowNum) throws SQLException {
		System.out.println("User Mapper");
		User user = new User();
		user.setRoleID(row.getInt("roleID"));
		user.setUserID(row.getInt("userID"));
		user.setUsername(row.getString("username"));
		user.setFirstName(row.getString("firstName"));
		user.setLastName(row.getString("lastName"));
		user.setEmail(row.getString("email"));

		return user;
	}

}
