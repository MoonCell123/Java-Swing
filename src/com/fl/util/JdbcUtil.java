package com.fl.util;

import java.sql.*;
import java.util.Properties;


public class JdbcUtil {
	private static final String DRIVERNAME;
	private static final String URL;
	private static final String USERNAME;
	private static final String PASSWORD;

	private static final ThreadLocal<Connection> threadLocal = new ThreadLocal<>();

	static {
		Properties ps = ConfigReader.read("/dbconfig.properties");
		DRIVERNAME = ps.getProperty("driverName");
		URL = ps.getProperty("url");
		USERNAME = ps.getProperty("username");
		PASSWORD = ps.getProperty("password");

		try {
			Class.forName(DRIVERNAME);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static Connection getConnection() throws SQLException {
		Connection conn = threadLocal.get();
		if (conn == null) {
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
			conn.setAutoCommit(false);
			threadLocal.set(conn);
		}
		return conn;
	}

	public static void release(ResultSet rs, Statement stmt, Connection conn) {
		try {
			if (rs != null) {
				rs.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (stmt != null) {
				stmt.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (conn != null) {
				conn.close();
				threadLocal.remove();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
