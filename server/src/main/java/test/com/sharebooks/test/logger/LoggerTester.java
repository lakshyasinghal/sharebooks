package com.sharebooks.test.logger;

import org.apache.log4j.Logger;
//import com.sharebooks.test.AbstractTester;

public class LoggerTester {
	private static Logger logger = Logger.getLogger(LoggerTester.class.getName());
	
	public static void main(String[] args){
		LoggerTester loggerTester = new LoggerTester();
		loggerTester.test();
	}
	
	public void test(){
		try {
			logger.debug("Logger is working");
			logger.info("Info message");
			logger.trace("Trace message");
			logger.warn("Warning");
		} catch (Exception ex) {
			System.out.println("Error in logger methods");
		}
	}
}
