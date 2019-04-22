package com.sharebooks.sources;


import com.sharebooks.services.entityServices.BookRequestService;
import com.sharebooks.services.entityServices.BookService;
import com.sharebooks.services.entityServices.MiscService;
import com.sharebooks.services.entityServices.UserService;


public class ServiceSource {
	private static BookService bookService;
	private static UserService userService;
	private static BookRequestService bookRequestService;
	private static MiscService miscService;
	
	public static void init() throws Exception{
		try{
			initBookService();
			initUserService();
			initBookRequestService();
			initMiscService();
		}
		catch(Exception ex){
			throw ex;
		}
	}
	

	private static void initBookService() throws Exception{
		try{
			//Cache<Book> bookCache = (Cache<Book>) CacheSource.getCache(EntityType.BOOK.desc());
			//BookDao dao = (BookDao)DaoSource.getDao("book");
			//EntityFactory<Book> factory = BookFactory.getInstance();
			//BookService.init(bookCache , dao);
			bookService = new BookService();
		}
		catch(Exception ex){
			throw ex;
		}
	}
	

	private static void initUserService() throws Exception{
		try{
//			Cache<User> userCache = (Cache<User>) CacheSource.getCache(EntityType.USER.desc());
//			UserDao dao = (UserDao)DaoSource.getDao("user");
//			UserService.init(userCache , dao);
			userService = new UserService();
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	
	//not using cache with book requests
	private static void initBookRequestService() throws Exception{
		try{
			//Cache<User> userCache = (Cache<User>) CacheSource.getCache(EntityType.USER.desc());
			//BookRequestDao dao = (BookRequestDao)DaoSource.getDao(EntityType.BOOK_REQUEST.desc());
			//BookRequestService.init(dao);
			bookRequestService = new BookRequestService();
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	
	//not using cache with book requests
	private static void initMiscService() throws Exception{
		try{
			//Cache<User> userCache = (Cache<User>) CacheSource.getCache(EntityType.USER.desc());
			//MiscDao dao = (MiscDao)DaoSource.getDao("miscellaneous");
			//BookRequestService.init(dao);
			miscService = new MiscService();
			miscService.init();
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
	
	public static MiscService getMiscService(){
		return miscService;
	}
}
