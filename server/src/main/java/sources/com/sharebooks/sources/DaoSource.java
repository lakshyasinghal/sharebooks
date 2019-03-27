package com.sharebooks.sources;

import java.util.*;

import org.apache.ibatis.session.SqlSessionFactory;

import com.sharebooks.coreEntities.Book;
import com.sharebooks.coreEntities.User;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.dao.generic.Dao;
import com.sharebooks.dao.sql.*;
import com.sharebooks.factory.entityFactory.EntityFactory;

public class DaoSource {
	
	private static Map<String , Dao> daoMap = new HashMap<String , Dao>();
	
	private DaoSource(){
		
	}
	
	@SuppressWarnings("unchecked")
	public static void init(){
		SqlSessionFactory sqlSessionFactory = FactorySource.sqlSessionFactory();
		EntityFactory<Book> bookFactory = (EntityFactory<Book>)FactorySource.getEntityFactory("book");
		EntityFactory<User> userFactory = (EntityFactory<User>)FactorySource.getEntityFactory("user");
		daoMap.put(EntityType.BOOK.desc(), new BookSqlDao(bookFactory,sqlSessionFactory));
		daoMap.put(EntityType.USER.desc(), new UserSqlDao(userFactory,sqlSessionFactory));
	}
	
	public static Dao getDao(String name){
		return daoMap.get(name);
	}
	
	
}
