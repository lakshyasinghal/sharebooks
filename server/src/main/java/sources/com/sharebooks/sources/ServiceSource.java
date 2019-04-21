package com.sharebooks.sources;

import com.sharebooks.cache.*;
//import com.sharebooks.cache.lruCache.LRUCache;
import com.sharebooks.coreEntities.*;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.dao.generic.*;
import com.sharebooks.services.entityServices.BookRequestService;
//import com.sharebooks.factory.entityFactory.BookFactory;
//import com.sharebooks.factory.entityFactory.EntityFactory;
//import com.sharebooks.factory.entityFactory.UserFactory;
import com.sharebooks.services.entityServices.BookService;
import com.sharebooks.services.entityServices.UserService;


public class ServiceSource {
	private static BookService bookService;
	private static UserService userService;
	private static BookRequestService bookRequestService;
	
	public static void init() throws Exception{
		try{
			initBookService();
			initUserService();
			initBookRequestService();
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void initBookService() throws Exception{
		try{
			Cache<Book> bookCache = (Cache<Book>) CacheSource.getCache(EntityType.BOOK.desc());
			BookDao dao = (BookDao)DaoSource.getDao("book");
			//EntityFactory<Book> factory = BookFactory.getInstance();
			BookService.init(bookCache , dao);
			bookService = BookService.getInstance();
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	@SuppressWarnings("unchecked")
	private static void initUserService() throws Exception{
		try{
			Cache<User> userCache = (Cache<User>) CacheSource.getCache(EntityType.USER.desc());
			UserDao dao = (UserDao)DaoSource.getDao("user");
			UserService.init(userCache , dao);
			userService = UserService.getInstance();
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	
	//not using cache with book requests
	private static void initBookRequestService() throws Exception{
		try{
			//Cache<User> userCache = (Cache<User>) CacheSource.getCache(EntityType.USER.desc());
			BookRequestDao dao = (BookRequestDao)DaoSource.getDao(EntityType.BOOK_REQUEST.desc());
			BookRequestService.init(dao);
			bookRequestService = BookRequestService.getInstance();
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	
	public static BookService getBookService(){
		return bookService;
	}
	
	public static UserService getUserService(){
		return userService;
	}
	
	public static BookRequestService getBookRequestService(){
		return bookRequestService;
	}
}
