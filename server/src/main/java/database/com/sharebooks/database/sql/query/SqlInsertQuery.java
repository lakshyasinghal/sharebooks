package com.sharebooks.database.sql.query;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;



public class SqlInsertQuery implements SqlQuery{
	private static final Logger LOGGER = Logger.getLogger(SqlInsertQuery.class.getName());
	private final String table;
	private final Map<String,Object> objMap;
	
	private String queryStr = null;
	
	
	public SqlInsertQuery(String table , Map<String,Object> objMap){
		this.table = table;
		this.objMap = objMap;
	}
	
	
	public String toString(){
		if(queryStr==null){
			build();
		}
		return queryStr;
	}
	
	
	//function will return string like    INSERT INTO BOOKS (id,title,authorName,category) VALUES (2,'Physics','H.C Verma','Science');
	public void build(){
		LOGGER.trace("Enetering build");
		List<String> fields = fieldsFromMap(objMap);
		List<Object> values = valuesFromMap(objMap);
		//LOGGER.entering(this.getClass().getName(), "build");
		StringBuilder builder = new StringBuilder();
		builder.append("INSERT INTO ");
		builder.append(table);
		builder.append(" ");
		builder.append("(");
		int i=0,len=fields.size();
		for( ; i<len-1 ; i++){
			builder.append(fields.get(i));
			builder.append(",");
		}
		builder.append(fields.get(i));
		builder.append(")");
		builder.append(" ");
		builder.append("VALUES");
		builder.append(" ");
		builder.append("(");
		i=0;len=values.size();
		for( ; i<len-1 ; i++){
			builder.append(getValue(values.get(i)));
			builder.append(",");
		}
		builder.append(getValue(values.get(i)));
		builder.append(")");
		builder.append(";");
		queryStr = builder.toString();
		LOGGER.debug("Query =>"+queryStr);
		LOGGER.trace("Enetering build");
	}
	
	
	private String getValue(Object obj){
		String value = null;
		if(obj instanceof Integer || obj instanceof Long || obj instanceof Float || obj instanceof Double || obj instanceof Boolean){
			value = obj.toString();
		}
		else if(obj instanceof String){
			value = "'" + obj.toString() + "'";
		}
		return value;
	}
	
}
