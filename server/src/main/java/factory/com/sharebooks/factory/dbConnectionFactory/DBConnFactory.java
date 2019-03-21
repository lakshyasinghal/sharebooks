package com.sharebooks.factory.dbConnectionFactory;

import java.sql.Connection;
import java.sql.SQLException;

public interface DBConnFactory {
	
	public Connection getSqlConnection(String host , String port , String dbName , String username , String password) throws SQLException;
}
