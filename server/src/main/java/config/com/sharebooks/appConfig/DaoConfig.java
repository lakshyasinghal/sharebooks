package com.sharebooks.appConfig;

import java.util.Properties;

import com.sharebooks.propertyReader.PropertyReader;

public class DaoConfig {
	private static Properties props = null;

	public static void run(String configFolderPath) throws Exception {
		props = PropertyReader.read(configFolderPath + "/" + ConfigFiles.DAO.fileName());
	}

	public static String propVal(String propName) {
		return props.getProperty(propName);
	}
}
