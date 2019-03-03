package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

public class CommentRowMapper implements RowMapper<Comment> {
	
	@Override
	public Comment mapRow(ResultSet row, int rowNum) throws SQLException {
		Comment comment = new Comment();
		comment.setCarParkID(row.getInt("carParID"));
		comment.setUserID(row.getInt("userID"));
		comment.setComment(row.getString("comment"));
		comment.setCommentID(row.getInt("commentID"));
		comment.setUsername(row.getString("username"));
		return comment;
	}
	
}
