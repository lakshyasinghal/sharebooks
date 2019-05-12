//package com.sharebooks.requestProcessor;
//
////import java.sql.SQLException;
//import org.apache.log4j.Logger;
//import com.sharebooks.factory.misc.ResponseFactory;
//import com.sharebooks.services.entityServices.OrderService;
//import com.sharebooks.sources.FactorySource;
//import com.sharebooks.sources.ServiceSource;
//
//public class OrderRequestProcessor {
//	private static OrderRequestProcessor instance = new OrderRequestProcessor();
//	private static final Logger LOGGER = Logger.getLogger(OrderRequestProcessor.class.getName());
//	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
//	private final OrderService orderService = ServiceSource.getOrderService();
//	
//	//private constructor to help make the class singleton
//	private OrderRequestProcessor(){
//		
//	}
//	
//	//get singleton instance of the class
//	public static OrderRequestProcessor getInstance(){
//		return instance;
//	}	
//	
//	
//}
