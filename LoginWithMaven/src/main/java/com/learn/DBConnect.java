package com.learn;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnect {

	private static final String URL = "jdbc:mysql://localhost:3306/sampleregistration";
	private static final String USER ="root";
	private static final String PASSWORD = "password";
	
	public static Connection getConnection() throws ClassNotFoundException {
		Connection conn = null;
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			
			conn = DriverManager.getConnection(URL, USER, PASSWORD);
			System.out.println("DBConnection is loaded");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		return conn;
	}
}
