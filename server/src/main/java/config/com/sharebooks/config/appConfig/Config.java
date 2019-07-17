package com.sharebooks.config.appConfig;

import java.util.Properties;

import com.sharebooks.config.propertyReader.PropertyReader;

public class Config {
	private Properties props = null;

	public Config(String configFolderPath, String configFile) throws Exception {
		props = PropertyReader.read(configFolderPath + "/" + configFile);
	}

//	public void run(String configFolderPath, String configFile) throws Exception {
//		props = PropertyReader.read(configFolderPath + "/" + configFile);
//	}

	public String propVal(String propName) {
		return props.getProperty(propName);
	}
}
