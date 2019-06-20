package com.sharebooks.appConfig;

import java.util.Properties;

import com.sharebooks.propertyReader.PropertyReader;

public class CacheConfig {
	private static Properties props = null;

	public static void run(String configFolderPath) throws Exception {
		props = PropertyReader.read(configFolderPath + "/" + ConfigFiles.CACHE.fileName());
	}

	public static String propVal(String propName) {
		return props.getProperty(propName);
	}
}
