package com.sharebooks.init;

import java.util.*;
import javax.servlet.ServletContext;

import com.sharebooks.logging.LoggerConfigurator;
import com.sharebooks.sources.*;

public class AppInitializer {
	//private static Map<String,String> configMapper;
	
//	static {
//		configMapper = new HashMap<String,String>();
//		configMapper.put("SqlConfig", "SqlConfig.xml");
//	}
	
	public void initialize(ServletContext ctxt){
		try{
			Map<String , String> propertyMap = null;
			propertyMap = getPropertyMap(ctxt);
			initialize(propertyMap);
		}
		catch(Exception ex){
			
		}
	}
	
	public void initialize(Map<String , String> propertyMap){
		try{
			PropertySource.init(propertyMap);
			//LoggerConfigurator.configure();
			FactorySource.init();
			ConnectionPoolSource.init();
			DaoSource.init();
			CacheSource.init();
			ServiceSource.init();
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	//will return all the properties and their values in the form of a map
	private Map<String , String> getPropertyMap(ServletContext ctxt){
		Map<String , String> propertyMap = new HashMap<String , String>();
		List<String> paramNames = new ArrayList<String>();
		Enumeration<String> enumeration = ctxt.getInitParameterNames();
		String elem = null;
		String paramValue = null;
		
		while(enumeration.hasMoreElements()){
			elem = enumeration.nextElement();
			paramNames.add(elem);
		}
		
		for(String paramName : paramNames){
			paramValue = ctxt.getInitParameter(paramName);
			propertyMap.put(paramName, paramValue);
		}
		
		return propertyMap;
	}
}
