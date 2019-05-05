package com.sharebooks.database.sql.customQueries;

import org.apache.log4j.Logger;

public class QuoteQueries {
	private static final Logger LOGGER = Logger.getLogger(QuoteQueries.class.getName());
	private static final QuoteQueries instance = new QuoteQueries();
	
	private QuoteQueries(){
		
	}
	
	public static QuoteQueries instance(){
		return instance;
	}
}
