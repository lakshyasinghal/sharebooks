package com.sharebooks.requestProcessor;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;

import com.sharebooks.coreEntities.Book;
import com.sharebooks.coreEntities.Quote;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.exception.BookAlreadyExistsException;
import com.sharebooks.exception.CacheException;
import com.sharebooks.factory.entityFactory.EntityFactory;
import com.sharebooks.factory.entityFactory.QuoteFactory;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.response.Error;
import com.sharebooks.response.Response;
import com.sharebooks.response.Status;
import com.sharebooks.services.entityServices.BookService;
import com.sharebooks.services.entityServices.OrderService;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;

public class OrderRequestProcessor {
	private static OrderRequestProcessor instance = new OrderRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(OrderRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final OrderService orderService = ServiceSource.getOrderService();
	
	//private constructor to help make the class singleton
	private OrderRequestProcessor(){
		
	}
	
	//get singleton instance of the class
	public static OrderRequestProcessor getInstance(){
		return instance;
	}	
	
	
}
