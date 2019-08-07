package com.sharebooks.config.appConfig;

import java.util.HashMap;
import java.util.Map;

import com.sharebooks.config.enums.ConfigFile;
import com.sharebooks.database.enums.Database;

public class AppConfig {
	private static Config cacheConfig;
	private static Config daoConfig;
	private static Map<String, Config> databaseConfigMap = new HashMap<String, Config>();
	// private static Config databaseConfig;
	private static Config paymentConfig;
	private static Config serverConfig;
	private static Config mailConfig;
	private static Config geocodingConfig;
	private static Config smsConfig;
	private static Config urlShortenerConfig;
	private static Config tasksConfig;

	public static void init(String ConfigFileFolderPath) throws Exception {
		cacheConfig = new Config(ConfigFileFolderPath, ConfigFile.CACHE.fileName());
		daoConfig = new Config(ConfigFileFolderPath, ConfigFile.DAO.fileName());
		// database config
		{
			databaseConfigMap.put(Database.CORE.desc(),
					new Config(ConfigFileFolderPath, ConfigFile.DATABASE_CORE.fileName()));
			databaseConfigMap.put(Database.MASTER.desc(),
					new Config(ConfigFileFolderPath, ConfigFile.DATABASE_MASTER.fileName()));
			databaseConfigMap.put(Database.USER_ACCOUNTS.desc(),
					new Config(ConfigFileFolderPath, ConfigFile.DATABASE_USERACCOUNTS.fileName()));
			databaseConfigMap.put(Database.USER_EXPERIENCE.desc(),
					new Config(ConfigFileFolderPath, ConfigFile.DATABASE_USEREXPERIENCE.fileName()));
			databaseConfigMap.put(Database.PAYMENTS.desc(),
					new Config(ConfigFileFolderPath, ConfigFile.DATABASE_PAYMENTS.fileName()));
		}

		paymentConfig = new Config(ConfigFileFolderPath, ConfigFile.PAYMENT.fileName());
		serverConfig = new Config(ConfigFileFolderPath, ConfigFile.SERVER.fileName());
		mailConfig = new Config(ConfigFileFolderPath, ConfigFile.MAIL.fileName());
		geocodingConfig = new Config(ConfigFileFolderPath, ConfigFile.GEOCODING.fileName());
		smsConfig = new Config(ConfigFileFolderPath, ConfigFile.SMS.fileName());
		urlShortenerConfig = new Config(ConfigFileFolderPath, ConfigFile.URL_SHORTENER.fileName());
		tasksConfig = new Config(ConfigFileFolderPath, ConfigFile.SMS.fileName());
	}

	// getter methods

	// Methods need to be updated. Need to return map instead of property.

	public static String cacheProp(String propName) {
		return cacheConfig.propVal(propName);
	}

	public static String daoProp(String propName) {
		return daoConfig.propVal(propName);
	}

	public static String databaseProp(String dbName, String propName) {
		return databaseConfigMap.get(dbName).propVal(propName);
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

	public static String urlShortenerProp(String propName) {
		return urlShortenerConfig.propVal(propName);
	}

	public static String tasksProp(String propName) {
		return tasksConfig.propVal(propName);
	}
}
