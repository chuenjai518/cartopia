package com.cartopia.Database;


import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;


import java.sql.Blob;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	String dbName = "root";
	String dbPw = "rootla";
	
	public Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
		} catch (Exception E) {
		}
		try {
			String connectionURL = "jdbc:mysql://localhost:3306/cartopia?allowMultiQueries=true";
			Connection conn = DriverManager.getConnection(connectionURL, dbName, dbPw);
			return conn;

	}catch(SQLException e) {
	System.out.println(e);
	return null;
		}
	}
	public ResultSet GetResultSet(String sql) {
		try {
			Connection connection  = getConnection();
			
			Statement statement =  connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE);
			ResultSet rs = statement.executeQuery(sql);
			return rs;
		}catch(SQLException e){
			return null;
		}
		
	}
	public int ExecUpdate(String sql) {
		try {
			Connection connection  = getConnection();
			
			Statement statement =  connection.createStatement();
			statement.execute(sql);
			return 0;
		}catch(SQLException e){
			return 0;
		}
		
	}
}