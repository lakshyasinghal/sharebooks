//package com.sharebooks.sources;
//
//import java.util.*;
//
//public class PropertySource {
//	private static final Map<String,String> serverPropertyMap = new HashMap<String,String>();
//	private static final Map<String , String> dbPropertyMap = new HashMap<String , String>();
//	private static final Map<String , String> cachePropertyMap = new HashMap<String , String>();
//	private static final Map<String , String> daoPropertyMap = new HashMap<String , String>();
//	//private static final Map<String , String> loggingPropertyMap = new HashMap<String , String>();
//	//private static final Map<String , String> sqlConfigPropertyMap = new HashMap<String , String>();
//	
//	
//	public static void init(Map<String , String> propertyMap){
//		initServerPropertyMap(propertyMap);
//		initDBPropertyMap(propertyMap);
//		initCachePropertyMap(propertyMap);
//		initDaoPropertyMap(propertyMap);
//		//initLoggingPropertyMap(propertyMap);
//		//initSqlConfigPropertyMap(propertyMap);
//	}
//	
//	//pm will refer to property map used in the following methods
//	
//	
//	private static void initServerPropertyMap(Map<String,String> pm){
//			Map<String,String> spm = serverPropertyMap; 
//			spm.put("SERVER_PROTOCOL", pm.get("SERVER_PROTOCOL"));
//			spm.put("SERVER_HOST", pm.get("SERVER_HOST"));
//			spm.put("SERVER_PORT", pm.get("SERVER_PORT"));
//			spm.put("SERVER_IDLE_TIMEOUT", pm.get("SERVER_IDLE_TIMEOUT"));
//	}
//	
//	private static void initDBPropertyMap(Map<String , String> pm){
//		Map<String,String> dbpm = dbPropertyMap;
//		
//		dbpm.put("SHAREBOOKS_DB_HOST", pm.get("SHAREBOOKS_DB_HOST"));
//		dbpm.put("SHAREBOOKS_DB_PORT", pm.get("SHAREBOOKS_DB_PORT"));
//		//dbPropertyMap.put("SHAREBOOKS_DB_NAME", SHAREBOOKS_DB_NAME);
//		dbpm.put("SHAREBOOKS_DB_USERNAME", pm.get("SHAREBOOKS_DB_USERNAME"));
//		dbpm.put("SHAREBOOKS_DB_PASSWORD", pm.get("SHAREBOOKS_DB_PASSWORD"));
//		dbpm.put("SHAREBOOKS_CONNECTION_POOL_CAPACITY", pm.get("SHAREBOOKS_CONNECTION_POOL_CAPACITY"));
//	}
//	
//	private static void initCachePropertyMap(Map<String , String> pm){
//		Map<String,String> cpm = cachePropertyMap;
//	
//		cpm.put("BOOK_CACHE_TYPE", pm.get("BOOK_CACHE_TYPE"));
//		cpm.put("BOOK_CACHE_CAPACITY", pm.get("BOOK_CACHE_CAPACITY"));
//		cpm.put("USER_CACHE_TYPE", pm.get("USER_CACHE_TYPE"));
//		cpm.put("USER_CACHE_CAPACITY", pm.get("USER_CACHE_CAPACITY"));
//	}
//	
//	private static void initDaoPropertyMap(Map<String , String> pm){
//		daoPropertyMap.put("DAO_TYPE", pm.get("DAO_TYPE"));
//	}
//
//	
//	//getter methods
//	
//	//Methods need to be updated. Need to return map instead of property. 
//	
//	public static String getServerPrperty(String propName){
//		return serverPropertyMap.get(propName);
//	}
//	
//	public static String getCacheProperty(String propName){
//		return cachePropertyMap.get(propName);
//	}
//	
//	public static String getDBProperty(String propName){
//		return dbPropertyMap.get(propName);
//	}
//	
//	public static String getDaoProperty(String propName){
//		return daoPropertyMap.get(propName);
//	}
//	
//}
