package com.tonin.animaltrack.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class JDBCUtils {

	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/animaltrackesp",
					"root",
					"abc123."
			);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
