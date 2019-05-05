package com.sharebooks.database.sql.customQueries;

import org.apache.log4j.Logger;

import com.sharebooks.database.sql.Table;

public class OrderQueries {
	private static final Logger LOGGER = Logger.getLogger(OrderQueries.class.getName());
	private static final OrderQueries instance = new OrderQueries();
	
	private OrderQueries(){
		
	}
	
	public static OrderQueries instance(){
		return instance;
	}
	
}
