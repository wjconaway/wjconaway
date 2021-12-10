package com.reveture.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.postgresql.Driver;

public class JDBCUtility {
	public static Connection getConnection() throws SQLException {

		String url = "jdbc:postgresql://database-1.cm6qdmhjc6up.us-east-2.rds.amazonaws.com:5432/postgres";
		String username = "postgres";
		String password = "password";

		Driver postgresDriver = new Driver();

		DriverManager.registerDriver(postgresDriver);
		Connection con = DriverManager.getConnection(url, username, password);

		return con;
	}


}
