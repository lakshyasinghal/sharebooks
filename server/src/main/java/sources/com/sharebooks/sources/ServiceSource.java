package com.sharebooks.sources;

import com.sharebooks.cache.*;
import com.sharebooks.cache.lruCache.LRUCache;
import com.sharebooks.coreEntities.*;
import com.sharebooks.dao.generic.*;
import com.sharebooks.factory.entityFactory.BookFactory;
import com.sharebooks.factory.entityFactory.EntityFactory;
import com.sharebooks.services.entityServices.BookService;
import com.sharebooks.services.entityServices.UserService;


public class ServiceSource {
	private static BookService bookService;
	//private static UserService userService;
	
	public static void init() throws Exception{
		try{
			initBookService();
			initUserService();
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	private static void initBookService() throws Exception{
		try{
			Cache<Book> bookCache = new LRUCache<Book>();
			BookDao dao = (BookDao)DaoSource.getDao("book");
			EntityFactory<Book> factory = BookFactory.getInstance();
			BookService.init(bookCache , dao , factory);
			bookService = BookService.getInstance();
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	private static void initUserService() throws Exception{
		try{
			Cache<User> userCache = new LRUCache<User>();
			UserDao dao = (UserDao)DaoSource.getDao("user");
			UserService.init(userCache , dao);
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	public static BookService getBookService(){
		return bookService;
	}
}
