package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class DriverBookmarkRowMapper implements RowMapper<DriverBookmark> {
	
	@Override
	public DriverBookmark mapRow(ResultSet row, int rowNum) throws SQLException {
		DriverBookmark bookmark = new DriverBookmark();
		bookmark.setCarParkID(row.getInt("carParkID"));
		bookmark.setUserID(row.getInt("userID"));
		bookmark.setName(row.getString("name"));
		bookmark.setPhotoLink(row.getString("PhotoLink"));
		return bookmark;
	}
	
}
