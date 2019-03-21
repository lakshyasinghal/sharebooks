package com.sharebooks.test.factory;

import java.sql.Connection;
import java.sql.SQLException;
import com.sharebooks.factory.dbConnectionFactory.*;
import com.sharebooks.test.AbstractTester;

public class DBConnFactoryTester extends AbstractTester{
	
	public static void main(String[] args){
		DBConnFactoryTester tester = new DBConnFactoryTester();
		tester.test();
	}
	
	public void test(){
		test1();
	}
	
	public void test1(){
		DBConnFactory factory = new SqlConnFactory();
		String host = "localhost";
		String port = "3306";
		String dbName = "sharebooks";
		String username = "root";
		String password = "root";
		try {
			Connection conn = factory.getSqlConnection(host, port, dbName, username, password);
			conn.close();
		} catch (SQLException e) {
			display("DBConnFactoryTester Test1 failed");
			newline();
			e.printStackTrace();
		}
	}
}
