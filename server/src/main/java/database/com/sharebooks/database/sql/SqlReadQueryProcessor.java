package com.sharebooks.database.sql;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;

//The role of SqlReadQueryProcessor and other similar classes will be to first execute the query and then return the processed result set
public class SqlReadQueryProcessor extends AbstractSqlQueryProcessor {
	private static final Logger LOGGER = Logger.getLogger(SqlReadQueryProcessor.class);
	private static SqlReadQueryProcessor queryProcessor = new SqlReadQueryProcessor();

	private SqlReadQueryProcessor() {

	}

	public static SqlReadQueryProcessor getInstance() {
		return queryProcessor;
	}

	public List<? extends Entity> processReadQuery(String dbName, String query, EntityType entityType)
			throws SQLException, Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			SqlResultProcessor resultProcessor = SqlResultProcessor.getInstance();
			SqlQueryExecutor executor = SqlQueryExecutor.getInstance();
			conn = getConnection(dbName);
			stmt = conn.createStatement();
			rs = executor.executeRead(stmt, query);
			List<? extends Entity> entityList = resultProcessor.process(rs, entityType);

			return entityList;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				releaseConnection(dbName, conn);
			}
		}
	}

	@Override
	// the columns map will be of type "email"=>"string" , "pincode"=>"int"
	// the method will return a list of maps where each map will correspond to a row
	// and will contain column name and column value
	public List<Map<String, Object>> processReadQuery(String dbName, String query, Map<String, String> columnMap)
			throws SQLException, Exception {
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		try {
			SqlQueryExecutor executor = SqlQueryExecutor.getInstance();
			conn = getConnection(dbName);
			stmt = conn.createStatement();
			rs = executor.executeRead(stmt, query);

			return getMapList(rs, columnMap);
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (conn != null) {
				releaseConnection(dbName, conn);
			}
		}
	}

	private List<Map<String, Object>> getMapList(ResultSet rs, Map<String, String> columnMap) throws Exception {
		List<Map<String, Object>> mapList = new ArrayList<Map<String, Object>>();
		Map<String, Object> nameValueMap = null;
		while (rs.next()) {
			nameValueMap = new HashMap<String, Object>();
			for (String column : columnMap.keySet()) {
				nameValueMap.put(column, getColumnValue(rs, column, columnMap.get(column)));
			}
			mapList.add(nameValueMap);
		}
		return mapList;
	}

	private Object getColumnValue(ResultSet rs, String column, String colType) throws Exception {
		Object obj = null;
		switch (colType) {
		case "int":
			obj = rs.getInt(column);
			break;
		case "string":
			obj = rs.getString(column);
			break;
		case "float":
			throw new Exception("Unidentified column type");
		case "double":
			throw new Exception("Unidentified column type");
		default:
			throw new Exception("Unidentified column type");
		}

		return obj;
	}

}
