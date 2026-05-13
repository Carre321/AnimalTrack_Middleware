package com.tonin.animaltrack.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCUtils {

	private static Logger logger = LogManager.getLogger(JDBCUtils.class.getName());
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			return DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/animaltrackesp",
					"root",
					"abc123."
			);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

	public static void close(ResultSet rs, PreparedStatement ps) {
		try { if (rs != null) rs.close(); } catch (Exception e) { logger.error(e.getMessage(), e); }
		try { if (ps != null) ps.close(); } catch (Exception e) { logger.error(e.getMessage(), e); }
	}

	public static void close(Connection c) {
		try { if (c != null) c.close(); } catch (Exception e) { logger.error(e.getMessage(), e); }
	}

	public static void close(Connection c, boolean commitOrRollback) {
		if (c != null) {
			try {
				if (commitOrRollback) {
					c.commit();
				} else {
					c.rollback();
				}
			} catch (SQLException e) {
				logger.error(e.getMessage(), e);
			}
			try {
				c.close();
			} catch (SQLException e) {
				logger.warn(e.getMessage(), e);
			}
		}
	}
}
