package com.sharebooks.appConfig;

public class AppConfig {
//	private static final Map<String, String> serverPropertyMap = new HashMap<String, String>();
//	private static final Map<String, String> dbPropertyMap = new HashMap<String, String>();
//	private static final Map<String, String> cachePropertyMap = new HashMap<String, String>();
//	private static final Map<String, String> daoPropertyMap = new HashMap<String, String>();
//	private static final Map<String, String> paymentGatewayPropertyMap = new HashMap<String, String>();
	// private static final Map<String , String> loggingPropertyMap = new
	// HashMap<String , String>();
	// private static final Map<String , String> sqlConfigPropertyMap = new
	// HashMap<String , String>();

	public static void init(String configFilesFolderPath) throws Exception {
		CacheConfig.run(configFilesFolderPath);
		DaoConfig.run(configFilesFolderPath);
		DatabaseConfig.run(configFilesFolderPath);
		PaymentConfig.run(configFilesFolderPath);
		ServerConfig.run(configFilesFolderPath);
	}

	// getter methods

	// Methods need to be updated. Need to return map instead of property.

	public static String cacheProp(String propName) {
		return CacheConfig.propVal(propName);
	}

	public static String daoProp(String propName) {
		return DaoConfig.propVal(propName);
	}

	public static String databaseProp(String propName) {
		return DatabaseConfig.propVal(propName);
	}

	public static String paymentProp(String propName) {
		return PaymentConfig.propVal(propName);
	}

	public static String serverProp(String propName) {
		return ServerConfig.propVal(propName);
	}
}
