package com.sharebooks.database.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.entity.Entity;
import com.sharebooks.exception.NonFunctionalMethodException;


//The role of SqlReadQueryProcessor and other similar classes will be to first execute the query and then return the processed result set
public class SqlReadQueryProcessor extends AbstractSqlQueryProcessor{
	private static SqlReadQueryProcessor queryProcessor = new SqlReadQueryProcessor();
	
	private SqlReadQueryProcessor(){
		
	}
	
	public static SqlReadQueryProcessor getInstance(){
		return queryProcessor;
	}
	
	public List<? extends Entity> processReadQuery(String dbName , String query , EntityType entityType) throws SQLException,Exception{
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try{
			SqlResultProcessor resultProcessor = SqlResultProcessor.getInstance();
			SqlQueryExecutor executor = SqlQueryExecutor.getInstance();
			conn = getConnection(dbName);
			stmt = conn.createStatement();
			rs = executor.executeRead(stmt, query);
			List<? extends Entity> entityList = resultProcessor.process(rs, entityType);
			
			return entityList;
		}
		finally{
			if(rs!=null){
				rs.close();
			}
			if(stmt != null){
				stmt.close();
			}
			if(conn != null){
				releaseConnection(dbName , conn);
			}
		}
	}

	@Override
	public int processDeleteQuery(String dbName, String query) throws Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public int processInsertQuery(String dbName, String query, boolean processInBatch) throws Exception {
		throw new NonFunctionalMethodException();
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
