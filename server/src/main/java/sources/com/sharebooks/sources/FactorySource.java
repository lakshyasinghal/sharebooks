package com.sharebooks.sources;

import java.util.*;

import com.sharebooks.dtos.DTO;
import com.sharebooks.dtos.enums.DTOType;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.factory.dbConnectionFactory.DBConnFactory;
import com.sharebooks.factory.dbConnectionFactory.SqlConnFactory;
import com.sharebooks.factory.dtoFactory.DTOFactory;
import com.sharebooks.factory.dtoFactory.ResultDTOFactory;
import com.sharebooks.factory.dtoFactory.SummaryInfoFactory;
import com.sharebooks.factory.entityFactory.BookCategoryFactory;
//import com.sharebooks.factory.dbConnectionFactory.DBConnFactory;
//import com.sharebooks.factory.dbConnectionFactory.SqlConnFactory;
import com.sharebooks.factory.entityFactory.BookFactory;
import com.sharebooks.factory.entityFactory.BookRequestFactory;
import com.sharebooks.factory.entityFactory.CityFactory;
import com.sharebooks.factory.entityFactory.EntityFactory;
import com.sharebooks.factory.entityFactory.NotificationFactory;
import com.sharebooks.factory.entityFactory.OrderFactory;
import com.sharebooks.factory.entityFactory.PreferenceFactory;
import com.sharebooks.factory.entityFactory.QuoteFactory;
import com.sharebooks.factory.entityFactory.StateFactory;
import com.sharebooks.factory.entityFactory.UserFactory;
import com.sharebooks.factory.misc.ResponseFactory;
import java.io.Reader;

import org.apache.ibatis.annotations.Property;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

public class FactorySource {
	private static Map<String , EntityFactory<? extends Entity>> entityFactoryMap = new HashMap<String , EntityFactory<? extends Entity>>();
	private static Map<String , DTOFactory<? extends DTO>> dtoFactoryMap = new HashMap<String, DTOFactory<? extends DTO>>();
	private static Map<String , DBConnFactory> dbConnFactoryMap = new HashMap<String , DBConnFactory>();
	private static SqlSessionFactory sqlSessionFactory;
	private static ResponseFactory responseFactory;
	
	public static void init(){
		initEntityFactoryMap();
		initDTOFactoryMap();
		initDBConnFactoryMap();
		//initSqlSessionFactory(PropertySource.getSqlConfigPropertyMap().get("SQL_CONFIG_FILE_PATH"));
		initResponseFactory();
	}
	
	
	
	
	//init methods
	private static void initEntityFactoryMap(){
		try{
			entityFactoryMap.put(EntityType.BOOK.desc(), BookFactory.getInstance());
			entityFactoryMap.put(EntityType.USER.desc(), UserFactory.getInstance());
			entityFactoryMap.put(EntityType.BOOK_REQUEST.desc(), BookRequestFactory.getInstance());
			entityFactoryMap.put(EntityType.ORDER.desc(), OrderFactory.getInstance());
			entityFactoryMap.put(EntityType.STATE.desc(), StateFactory.getInstance());
			entityFactoryMap.put(EntityType.CITY.desc(), CityFactory.getInstance());
			entityFactoryMap.put(EntityType.BOOK_CATEGORY.desc(), BookCategoryFactory.getInstance());
			entityFactoryMap.put(EntityType.NOTIFICATION.desc(), NotificationFactory.getInstance());
			entityFactoryMap.put(EntityType.PREFERENCE.desc(), PreferenceFactory.getInstance());
			entityFactoryMap.put(EntityType.Quote.desc(), QuoteFactory.getInstance());
		}
		catch(Exception ex){
			
		}
	}
	
	
	private static void initDTOFactoryMap(){
		try{
			dtoFactoryMap.put(DTOType.RESULT.desc(), ResultDTOFactory.getInstance());
			dtoFactoryMap.put(DTOType.SUMMARY.desc(), SummaryInfoFactory.getInstance());
		}
		catch(Exception ex){
			
		}
	}
	
	
	private static void initDBConnFactoryMap(){
		try{
			dbConnFactoryMap.put("sql", new SqlConnFactory());
		}
		catch(Exception ex){
			
		}
	}
	
//	private static void initSqlSessionFactory(String configPath){
//		try{
//			Reader reader = Resources.getResourceAsReader(configPath);
//		    sqlSessionFactory = new SqlSessionFactoryBuilder().build(reader);
//		}
//		catch(Exception ex){
//			System.out.println(ex.getMessage());
//		}
//	}
	
	private static void initResponseFactory(){
		responseFactory = ResponseFactory.getInstance();
	}
	

	
	
	//getter methods
	
	public static EntityFactory<? extends Entity> getEntityFactory(String factoryName){
		return entityFactoryMap.get(factoryName);
	}
	
	public static DTOFactory<? extends DTO> getDTOFactory(String factoryName){
		return dtoFactoryMap.get(factoryName);
	}
	
//	public static DBConnFactory getDBConnFactory(String factoryName){
//		return dbConnFactoryMap.get(factoryName);
//	}
	
	public static ResponseFactory getResponseFactory(){
		return responseFactory;
	}
	
	public static SqlSessionFactory sqlSessionFactory(){
		return sqlSessionFactory;
	}
}
