//package com.sharebooks.init;
//
//import java.util.*;
//import javax.servlet.ServletContext;
//
//import com.sharebooks.logging.LoggerConfigurator;
//import com.sharebooks.sources.*;
//import com.sharebooks.test.util.DummyPropertySource;
//
//public class AppInitializer {
//	//private static Map<String,String> configMapper;
//	
////	static {
////		configMapper = new HashMap<String,String>();
////		configMapper.put("SqlConfig", "SqlConfig.xml");
////	}
//	
//	public void run(ServletContext ctxt){
//		try{
//			Map<String , String> propertyMap = null;
//			propertyMap = getPropertyMap(ctxt);
//			//initialize(propertyMap);
//		}
//		catch(Exception ex){
//			
//		}
//	}
//	
//	public void run(){
//		try{
//			Map<String,String> propMap = DummyPropertySource.getPropertyMap();
//			PropertySource.init(propMap);
//			//LoggerConfigurator.configure();
//			FactorySource.init();
//			ConnectionPoolSource.init();
//			DaoSource.init();
//			CacheSource.init();
//			ServiceSource.init();
//		}
//		catch(Exception ex){
//			ex.printStackTrace();
//		}
//	}
//	
//	
//	//will return all the properties and their values in the form of a map
//	private Map<String , String> getPropertyMap(ServletContext ctxt){
//		Map<String , String> propertyMap = new HashMap<String , String>();
//		List<String> paramNames = new ArrayList<String>();
//		Enumeration<String> enumeration = ctxt.getInitParameterNames();
//		String elem = null;
//		String paramValue = null;
//		
//		while(enumeration.hasMoreElements()){
//			elem = enumeration.nextElement();
//			paramNames.add(elem);
//		}
//		
//		for(String paramName : paramNames){
//			paramValue = ctxt.getInitParameter(paramName);
//			propertyMap.put(paramName, paramValue);
//		}
//		
//		return propertyMap;
//	}
//}
