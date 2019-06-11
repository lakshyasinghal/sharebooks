package com.sharebooks.appConfig;

import static com.sharebooks.properties.CacheProperties.BOOK_CACHE_CAPACITY;
import static com.sharebooks.properties.CacheProperties.BOOK_CACHE_TYPE;
import static com.sharebooks.properties.CacheProperties.USER_CACHE_CAPACITY;
import static com.sharebooks.properties.CacheProperties.USER_CACHE_TYPE;
import static com.sharebooks.properties.DaoProperties.DAO_TYPE;
import static com.sharebooks.properties.DatabaseProperties.SHAREBOOKS_CONNECTION_POOL_CAPACITY;
import static com.sharebooks.properties.DatabaseProperties.SHAREBOOKS_DB_HOST;
import static com.sharebooks.properties.DatabaseProperties.SHAREBOOKS_DB_PASSWORD;
import static com.sharebooks.properties.DatabaseProperties.SHAREBOOKS_DB_PORT;
import static com.sharebooks.properties.DatabaseProperties.SHAREBOOKS_DB_USERNAME;
import static com.sharebooks.properties.PaymentProperties.INSTAMOJO_PAYMENT_REQUEST_API;
import static com.sharebooks.properties.ServerProperties.SERVER_HOST;
import static com.sharebooks.properties.ServerProperties.SERVER_IDLE_TIMEOUT;
import static com.sharebooks.properties.ServerProperties.SERVER_PORT;
import static com.sharebooks.properties.ServerProperties.SERVER_PROTOCOL;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import com.sharebooks.propertyReader.PropertyReader;

public class AppConfig {
	private static final Map<String, String> serverPropertyMap = new HashMap<String, String>();
	private static final Map<String, String> dbPropertyMap = new HashMap<String, String>();
	private static final Map<String, String> cachePropertyMap = new HashMap<String, String>();
	private static final Map<String, String> daoPropertyMap = new HashMap<String, String>();
	private static final Map<String, String> paymentGatewayPropertyMap = new HashMap<String, String>();
	// private static final Map<String , String> loggingPropertyMap = new
	// HashMap<String , String>();
	// private static final Map<String , String> sqlConfigPropertyMap = new
	// HashMap<String , String>();

	public static void init(String configFilesFolderPath) throws Exception {
		initServerPropertyMap(configFilesFolderPath);
		initDBPropertyMap(configFilesFolderPath);
		initCachePropertyMap(configFilesFolderPath);
		initDaoPropertyMap(configFilesFolderPath);
		initPaymentPropertyMap(configFilesFolderPath);
	}

	// pm will refer to property map used in the following methods

	private static void initServerPropertyMap(String configFilesFolderPath) throws Exception {
		Properties props = PropertyReader.read(configFilesFolderPath + "/" + ConfigFiles.SERVER.fileName());

		serverPropertyMap.put(SERVER_PROTOCOL, props.getProperty(SERVER_PROTOCOL));
		serverPropertyMap.put(SERVER_HOST, props.getProperty(SERVER_HOST));
		serverPropertyMap.put(SERVER_PORT, props.getProperty(SERVER_PORT));
		serverPropertyMap.put(SERVER_IDLE_TIMEOUT, props.getProperty(SERVER_IDLE_TIMEOUT));
	}

	private static void initDBPropertyMap(String configFilesFolderPath) throws Exception {
		Properties props = PropertyReader.read(configFilesFolderPath + "/" + ConfigFiles.DATABASE.fileName());

		dbPropertyMap.put(SHAREBOOKS_DB_HOST, props.getProperty(SHAREBOOKS_DB_HOST));
		dbPropertyMap.put(SHAREBOOKS_DB_PORT, props.getProperty(SHAREBOOKS_DB_PORT));
		dbPropertyMap.put(SHAREBOOKS_DB_USERNAME, props.getProperty(SHAREBOOKS_DB_USERNAME));
		dbPropertyMap.put(SHAREBOOKS_DB_PASSWORD, props.getProperty(SHAREBOOKS_DB_PASSWORD));
		dbPropertyMap.put(SHAREBOOKS_CONNECTION_POOL_CAPACITY, props.getProperty(SHAREBOOKS_CONNECTION_POOL_CAPACITY));
	}

	private static void initCachePropertyMap(String configFilesFolderPath) throws Exception {
		Properties props = PropertyReader.read(configFilesFolderPath + "/" + ConfigFiles.CACHE.fileName());

		cachePropertyMap.put(BOOK_CACHE_TYPE, props.getProperty(BOOK_CACHE_TYPE));
		cachePropertyMap.put(BOOK_CACHE_CAPACITY, props.getProperty(BOOK_CACHE_CAPACITY));
		cachePropertyMap.put(USER_CACHE_TYPE, props.getProperty(USER_CACHE_TYPE));
		cachePropertyMap.put(USER_CACHE_CAPACITY, props.getProperty(USER_CACHE_CAPACITY));
	}

	private static void initDaoPropertyMap(String configFilesFolderPath) throws Exception {
		Properties props = PropertyReader.read(configFilesFolderPath + "/" + ConfigFiles.DAO.fileName());

		daoPropertyMap.put(DAO_TYPE, props.getProperty(DAO_TYPE));
	}

	private static void initPaymentPropertyMap(String configFilesFolderPath) throws Exception {
		Properties props = PropertyReader.read(configFilesFolderPath + "/" + ConfigFiles.PAYMENT_GATEWAY.fileName());

		paymentGatewayPropertyMap.put(INSTAMOJO_PAYMENT_REQUEST_API, props.getProperty(INSTAMOJO_PAYMENT_REQUEST_API));
	}

	// getter methods

	// Methods need to be updated. Need to return map instead of property.

	public static String getServerProperty(String propName) {
		return serverPropertyMap.get(propName);
	}

	public static String getCacheProperty(String propName) {
		return cachePropertyMap.get(propName);
	}

	public static String getDatabaseProperty(String propName) {
		return dbPropertyMap.get(propName);
	}

	public static String getDaoProperty(String propName) {
		return daoPropertyMap.get(propName);
	}

	public static String getPaymentGatewayProperty(String propName) {
		return paymentGatewayPropertyMap.get(propName);
	}
}
