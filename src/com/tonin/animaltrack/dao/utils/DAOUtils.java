package com.tonin.animaltrack.dao.utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

public class DAOUtils {

	public static void setParameters(PreparedStatement ps, Object... params) throws Exception {
		if (params == null) return;
		for (int i = 0; i < params.length; i++) {
			ps.setObject(i + 1, params[i]);
		}
	}

	public static void setParameters(PreparedStatement ps, List<Object> params) throws Exception {
		if (params == null) return;
		for (int i = 0; i < params.size(); i++) {
			ps.setObject(i + 1, params.get(i));
		}
	}

	public static void close(ResultSet rs, PreparedStatement ps, Connection c) {
		try { if (rs != null) rs.close(); } catch (Exception e) { e.printStackTrace(); }
		try { if (ps != null) ps.close(); } catch (Exception e) { e.printStackTrace(); }
		try { if (c != null) c.close(); } catch (Exception e) { e.printStackTrace(); }
	}
}
