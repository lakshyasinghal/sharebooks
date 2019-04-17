package com.sharebooks.database.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.entity.Entity;
import com.sharebooks.exception.NonFunctionalMethodException;

public class SqlUpdateQueryProcessor extends AbstractSqlQueryProcessor{
	private static SqlUpdateQueryProcessor queryProcessor = new SqlUpdateQueryProcessor();
	
	private SqlUpdateQueryProcessor(){
		
	}
	
	public static SqlUpdateQueryProcessor getInstance(){
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
		throw new NonFunctionalMethodException();
	}

	@Override
	public int processUpdateQuery(String dbName, String query) throws Exception {
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
}
