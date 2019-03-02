package com.uow.Model;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;



public class TransactionRowMapper implements RowMapper<Transaction>{

		@Override
		public Transaction mapRow(ResultSet row, int rowNum) throws SQLException {
			Transaction transaction = new Transaction();
			transaction.setTransactionID(row.getInt("transactionID"));
			transaction.setDriverID(row.getInt("driverID"));
			transaction.setStartTime(row.getTime("Time(startTime)"));
			transaction.setEndTime(row.getTime("Time(endTime)"));
			transaction.setTotalAmount(row.getDouble("totalAmount"));
			transaction.setLicensePlateNum(row.getString("licensePlateNum"));
			return transaction;
		}

}