package com.sharebooks.test.util;

import java.util.*;

public class DummyPropertySource {
	private static Map<String , String> propertyMap = new HashMap<String , String>();
	
	
	static {
		init();
	}

	private static void init(){
		propertyMap.put("SHAREBOOKS_DB_TYPE", "MYSQL");
		propertyMap.put("SHAREBOOKS_DB_HOST", "localhost");
		propertyMap.put("SHAREBOOKS_DB_PORT", "3306");
		propertyMap.put("SHAREBOOKS_DB_USERNAME", "lakshyasinghal33");
		propertyMap.put("SHAREBOOKS_DB_PASSWORD", "nitj1010");
		propertyMap.put("SHAREBOOKS_DB_NAME", "sharebooks");
		propertyMap.put("BOOK_CACHE_TYPE", "LRU");
		propertyMap.put("BOOK_CACHE_CAPACITY", "10");
		propertyMap.put("USER_CACHE_TYPE", "LRU");
		propertyMap.put("USER_CACHE_CAPACITY", "5");
		propertyMap.put("CONNECTION_POOL_TYPE", "MYSQL");
		propertyMap.put("SHAREBOOKS_CONNECTION_POOL_CAPACITY", "3");
		propertyMap.put("DAO_TYPE", "SQL");
		propertyMap.put("SQL_CONFIG_FILE_PATH", "SqlConfig.xml");
	}
	
	public static Map<String , String> getPropertyMap(){
		return propertyMap;
	}
}
