package com.sharebooks.database.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.exception.NonFunctionalMethodException;

public class SqlInsertQueryProcessor extends AbstractSqlQueryProcessor{
	private static SqlInsertQueryProcessor queryProcessor = new SqlInsertQueryProcessor();
	
	private SqlInsertQueryProcessor(){
		
	}
	
	public static SqlInsertQueryProcessor getInstance(){
		return queryProcessor;
	}
	
	
	@Override
	public List<? extends Entity> processReadQuery(String dbName, String query, EntityType entityType)
			throws Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public int processDeleteQuery(String dbName, String query) throws Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public int processInsertQuery(String dbName, String query , boolean processInBatch) throws SQLException,Exception {
		Connection conn = null;
		Statement stmt = null;
		int rowsAffected = -1;
		try{
			SqlQueryExecutor executor = SqlQueryExecutor.getInstance();
			conn = getConnection(dbName);
			stmt = conn.createStatement();
			rowsAffected = executor.executeUpdate(stmt, query);			
			return rowsAffected;
		}
		catch(SQLException ex){
			throw ex;
		}
		catch(Exception ex){
			throw ex;
		}
		finally{
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				releaseConnection(dbName , conn);
			}
		}
	}

	@Override
	public int processUpdateQuery(String dbName, String query) throws Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public List<Integer> processTransaction(String dbName, List<String> queries) throws SQLException, Exception {
		throw new NonFunctionalMethodException();
	}

}
