package com.sharebooks.sources;

import java.util.*;

public class PropertySource {
	private static final Map<String , String> dbPropertyMap = new HashMap<String , String>();
	private static final Map<String , String> cachePropertyMap = new HashMap<String , String>();
	private static final Map<String , String> daoPropertyMap = new HashMap<String , String>();
	private static final Map<String , String> loggingPropertyMap = new HashMap<String , String>();
	private static final Map<String , String> sqlConfigPropertyMap = new HashMap<String , String>();
	
	
	public static void init(Map<String , String> propertyMap){
		initDBPropertyMap(propertyMap);
		initCachePropertyMap(propertyMap);
		initDaoPropertyMap(propertyMap);
		initLoggingPropertyMap(propertyMap);
		initSqlConfigPropertyMap(propertyMap);
	}
	
	private static void initDBPropertyMap(Map<String , String> propertyMap){
		String SHAREBOOKS_DB_HOST = propertyMap.get("SHAREBOOKS_DB_HOST");
		String SHAREBOOKS_DB_PORT = propertyMap.get("SHAREBOOKS_DB_PORT");
		//String SHAREBOOKS_DB_NAME = propertyMap.get("SHAREBOOKS_DB_NAME");
		String SHAREBOOKS_DB_USERNAME = propertyMap.get("SHAREBOOKS_DB_USERNAME");
		String SHAREBOOKS_DB_PASSWORD = propertyMap.get("SHAREBOOKS_DB_PASSWORD");
		String SHAREBOOKS_CONNECTION_POOL_CAPACITY = propertyMap.get("SHAREBOOKS_CONNECTION_POOL_CAPACITY");
		
		dbPropertyMap.put("SHAREBOOKS_DB_HOST", SHAREBOOKS_DB_HOST);
		dbPropertyMap.put("SHAREBOOKS_DB_PORT", SHAREBOOKS_DB_PORT);
		//dbPropertyMap.put("SHAREBOOKS_DB_NAME", SHAREBOOKS_DB_NAME);
		dbPropertyMap.put("SHAREBOOKS_DB_USERNAME", SHAREBOOKS_DB_USERNAME);
		dbPropertyMap.put("SHAREBOOKS_DB_PASSWORD", SHAREBOOKS_DB_PASSWORD);
		dbPropertyMap.put("SHAREBOOKS_CONNECTION_POOL_CAPACITY", SHAREBOOKS_CONNECTION_POOL_CAPACITY);
	}
	
	private static void initCachePropertyMap(Map<String , String> propertyMap){
		String BOOK_CACHE_TYPE = propertyMap.get("BOOK_CACHE_TYPE");
		String BOOK_CACHE_CAPACITY = propertyMap.get("BOOK_CACHE_CAPACITY");
		String USER_CACHE_TYPE = propertyMap.get("USER_CACHE_TYPE");
		String USER_CACHE_CAPACITY = propertyMap.get("USER_CACHE_CAPACITY");
		
		cachePropertyMap.put("BOOK_CACHE_TYPE", BOOK_CACHE_TYPE);
		cachePropertyMap.put("BOOK_CACHE_CAPACITY", BOOK_CACHE_CAPACITY);
		cachePropertyMap.put("USER_CACHE_TYPE", USER_CACHE_TYPE);
		cachePropertyMap.put("USER_CACHE_CAPACITY", USER_CACHE_CAPACITY);
	}
	
	private static void initDaoPropertyMap(Map<String , String> propertyMap){
		String DAO_TYPE = propertyMap.get("DAO_TYPE");
		daoPropertyMap.put("DAO_TYPE", DAO_TYPE);
	}
	
	private static void initLoggingPropertyMap(Map<String , String> propertyMap){
		String LOGGER_CONFIG_FILE_PATH = propertyMap.get("LOGGER_CONFIG_FILE_PATH");
		loggingPropertyMap.put("LOGGER_CONFIG_FILE_PATH", LOGGER_CONFIG_FILE_PATH);
	}
	
	
	private static void initSqlConfigPropertyMap(Map<String , String> propertyMap){
		String SQL_CONFIG_FILE_PATH = propertyMap.get("SQL_CONFIG_FILE_PATH");
		sqlConfigPropertyMap.put("SQL_CONFIG_FILE_PATH", SQL_CONFIG_FILE_PATH);
	}
	
	//getter methods
	
	//Methods need to be updated. Need to return map instead of property. 
	
	public static String getCacheProperty(String propName){
		return cachePropertyMap.get(propName);
	}
	
	public static String getDBProperty(String propName){
		return dbPropertyMap.get(propName);
	}
	
	public static String getDaoProperty(String propName){
		return daoPropertyMap.get(propName);
	}
	
	//Method needs to be updated. Needs to send copy instead of original. 
	public static Map<String,String> getLoggingPropertyMap(){
		return loggingPropertyMap;
	}
	
	//Method needs to be updated. Needs to send copy instead of original. 
	public static Map<String,String> getSqlConfigPropertyMap(){
		return sqlConfigPropertyMap;
	}
}
