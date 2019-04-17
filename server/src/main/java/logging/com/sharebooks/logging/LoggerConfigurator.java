package com.sharebooks.logging;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;
import java.util.logging.*;

import com.sharebooks.sources.PropertySource;

/*Deprecated class
 Using apache 4j logger now */
public class LoggerConfigurator {
	
	
//	public static void configure() throws Exception{
//		Map<String,String> loggingPropertyMap = PropertySource.getLoggingPropertyMap();
//		configureLogManager(loggingPropertyMap);
//	}
//	
//	
//	private static void configureLogManager(Map<String,String> loggingPropertyMap) throws Exception{
//		String LOGGER_CONFIG_FILE_PATH = loggingPropertyMap.get("LOGGER_CONFIG_FILE_PATH");
//		LogManager logManager = LogManager.getLogManager();
//		try {
//			logManager.readConfiguration(new FileInputStream(LOGGER_CONFIG_FILE_PATH));
//		} catch (SecurityException | IOException e) {
//			throw e;
//		}
//	}

}
