package com.sharebooks.config.appConfig;

import com.sharebooks.config.enums.ConfigFile;

public class AppConfig {
	private static Config cacheConfig;
	private static Config daoConfig;
	private static Config databaseConfig;
	private static Config paymentConfig;
	private static Config serverConfig;
	private static Config mailConfig;
	private static Config geocodingConfig;
	private static Config smsConfig;

	public static void init(String ConfigFileFolderPath) throws Exception {
		cacheConfig = new Config(ConfigFileFolderPath, ConfigFile.CACHE.fileName());
		daoConfig = new Config(ConfigFileFolderPath, ConfigFile.DAO.fileName());
		databaseConfig = new Config(ConfigFileFolderPath, ConfigFile.DATABASE.fileName());
		paymentConfig = new Config(ConfigFileFolderPath, ConfigFile.PAYMENT.fileName());
		serverConfig = new Config(ConfigFileFolderPath, ConfigFile.SERVER.fileName());
		mailConfig = new Config(ConfigFileFolderPath, ConfigFile.MAIL.fileName());
		geocodingConfig = new Config(ConfigFileFolderPath, ConfigFile.GEOCODING.fileName());
		smsConfig = new Config(ConfigFileFolderPath, ConfigFile.SMS.fileName());
	}

	// getter methods

	// Methods need to be updated. Need to return map instead of property.

	public static String cacheProp(String propName) {
		return cacheConfig.propVal(propName);
	}

	public static String daoProp(String propName) {
		return daoConfig.propVal(propName);
	}

	public static String databaseProp(String propName) {
		return databaseConfig.propVal(propName);
	}

	public static String paymentProp(String propName) {
		return paymentConfig.propVal(propName);
	}

	public static String serverProp(String propName) {
		return serverConfig.propVal(propName);
	}

	public static String mailProp(String propName) {
		return mailConfig.propVal(propName);
	}

	public static String geocodingProp(String propName) {
		return geocodingConfig.propVal(propName);
	}

	public static String smsProp(String propName) {
		return smsConfig.propVal(propName);
	}
}
