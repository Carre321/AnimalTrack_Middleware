package com.tonin.animaltrack.dao.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class JDBCUtils {

	private static Logger logger = LogManager.getLogger(JDBCUtils.class.getName());
    private static boolean schemaChecked = false;
	
	public static Connection getConnection() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/animaltrackesp",
					"root",
					"abc123."
			);
            ensureSchema(connection);
            return connection;
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return null;
	}

    private static synchronized void ensureSchema(Connection connection) {
        if (schemaChecked || connection == null) {
            return;
        }
        try {
            addColumnIfMissing(connection, "ganadero", "direccion", "VARCHAR(256) NULL DEFAULT NULL AFTER email");
            addColumnIfMissing(connection, "ganadero", "codigo_postal", "VARCHAR(10) NULL DEFAULT NULL AFTER direccion");
            addColumnIfMissing(connection, "granja", "codigo_postal", "VARCHAR(10) NULL DEFAULT NULL AFTER direccion");
            addColumnIfMissing(connection, "veterinario", "direccion", "VARCHAR(256) NULL DEFAULT NULL AFTER email");
            addColumnIfMissing(connection, "veterinario", "codigo_postal", "VARCHAR(10) NULL DEFAULT NULL AFTER direccion");
            schemaChecked = true;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private static void addColumnIfMissing(Connection connection, String table, String column, String definition)
            throws SQLException {
        PreparedStatement ps = null;
        ResultSet rs = null;
        Statement statement = null;
        try {
            ps = connection.prepareStatement(
                    "SELECT 1 FROM information_schema.columns WHERE table_schema = DATABASE() AND table_name = ? AND column_name = ?");
            ps.setString(1, table);
            ps.setString(2, column);
            rs = ps.executeQuery();
            if (rs.next()) {
                return;
            }
            statement = connection.createStatement();
            statement.executeUpdate("ALTER TABLE " + table + " ADD COLUMN " + column + " " + definition);
        } finally {
            try { if (statement != null) statement.close(); } catch (Exception e) { logger.error(e.getMessage(), e); }
            close(rs, ps);
        }
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
