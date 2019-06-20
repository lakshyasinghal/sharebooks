package com.sharebooks.appConfig;

import java.util.Properties;

import com.sharebooks.propertyReader.PropertyReader;

public class DatabaseConfig {
	private static Properties props = null;

	public static void run(String configFolderPath) throws Exception {
		props = PropertyReader.read(configFolderPath + "/" + ConfigFiles.DATABASE.fileName());
	}

	public static String propVal(String propName) {
		return props.getProperty(propName);
	}
}
