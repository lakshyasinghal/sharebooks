package com.sharebooks.config.propertyReader;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {

	public static Properties read(String filePath) throws Exception {
		try (InputStream input = new FileInputStream(filePath)) {
			Properties props = new Properties();
			// load a properties file
			props.load(input);
			return props;
		} catch (IOException ex) {
			throw ex;
		}
	}
}
