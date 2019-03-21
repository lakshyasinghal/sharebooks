package com.sharebooks.database.sql.query;

import java.util.List;
import java.util.logging.Logger;



public class SqlInsertQuery implements SqlQuery{
	private static final Logger LOGGER = Logger.getLogger(SqlInsertQuery.class.getName());
	private final String table;
	private final List<String> fields; //column names
	private final List<Object> values; //column values
	
	private String queryStr = null;
	
	
	public SqlInsertQuery(String table , List<String> fields , List<Object> values){
		this.table = table;
		this.fields = fields;
		this.values = values;
	}
	
	
	public String toString(){
		if(queryStr==null){
			build();
		}
		return queryStr;
	}
	
	
	//function will return string like    INSERT INTO BOOKS (id,title,authorName,category) VALUES (2,'Physics','H.C Verma','Science');
	public void build(){
		LOGGER.entering(this.getClass().getName(), "build");
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
		LOGGER.exiting(this.getClass().getName(), "build");
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
