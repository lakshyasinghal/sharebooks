package com.sharebooks.factory.dbConnectionFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SqlConnFactory implements DBConnFactory {
	private static SqlConnFactory connFactory = new SqlConnFactory();

	static {
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	public static SqlConnFactory instance() {
		return connFactory;
	}

	@Override
	public Connection getSqlConnection(String host, String port, String dbName, String username, String password)
			throws SQLException {
		try {
			String url = getUrl(host, port, dbName);
			Connection connection = DriverManager.getConnection(url, username, password);
			return connection;
		} catch (SQLException ex) {
			throw ex;
		}
	}

	private String getUrl(String host, String port, String dbName) {
		String url = "jdbc:mysql://" + host + ":" + port + "/" + dbName;
		return url;
	}

}
