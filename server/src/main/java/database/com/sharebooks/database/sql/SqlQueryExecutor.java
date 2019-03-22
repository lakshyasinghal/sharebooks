package com.sharebooks.database.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class SqlQueryExecutor {
	private static SqlQueryExecutor executor = new SqlQueryExecutor();
	
	private SqlQueryExecutor(){
		
	}
	
	public static SqlQueryExecutor getInstance(){
		return executor;
	}
   	
	public ResultSet executeRead(Statement stmt , String query) throws SQLException , Exception{
		try{
			ResultSet rs = stmt.executeQuery(query);
			return rs;
		}
		catch(SQLException ex){
			throw ex;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	public int executeUpdate(Statement stmt , String query) throws SQLException , Exception{
		try{
			int rowsAffected = stmt.executeUpdate(query);
			return rowsAffected;
		}
		catch(SQLException ex){
			throw ex;
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
