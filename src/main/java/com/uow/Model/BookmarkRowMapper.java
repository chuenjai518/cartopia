package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class BookmarkRowMapper implements RowMapper<Bookmark> {
	
	@Override
	public Bookmark mapRow(ResultSet row, int rowNum) throws SQLException {
		Bookmark bookmark = new Bookmark();
		bookmark.setCarParkID(row.getInt("carParkID"));
		bookmark.setUserID(row.getInt("userID"));
		return bookmark;
	}
	
}
