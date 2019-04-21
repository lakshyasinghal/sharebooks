package com.sharebooks.services.entityServices;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;

import com.sharebooks.cache.Cache;
import com.sharebooks.coreEntities.Book;
import com.sharebooks.dao.generic.BookDao;
import com.sharebooks.exception.*;
import com.sharebooks.factory.entityFactory.EntityFactory;


public class BookService extends EntityService{
	private static BookService bookService;
	private static final Logger LOGGER = Logger.getLogger(BookService.class.getName());
	private final Cache<Book> cache;
	private final BookDao dao;
	
	private BookService(Cache<Book> cache , BookDao dao){
		this.cache = cache;
		this.dao = dao;
	}
	
	public static void init(Cache<Book> cache , BookDao dao){
		if(bookService == null){
			bookService = new BookService(cache , dao);
		}	
	}
	
	public static BookService getInstance(){
		return bookService;
	}
	
	
	//needs to be implemented
	public List<Book> getBooks() throws SQLException,Exception{
		try{
			//List<Book> books = dao.getAllBooks();
			return null;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			throw ex;
		}
	} 
	
	public List<Book> getAllBooks() throws SQLException,Exception{
		//LOGGER.entering("BookService", "getAllBooks");
		try{
			List<Book> books = dao.getAllBooks();
			//LOGGER.finer(books.toString());
			//LOGGER.exiting("BookService", "getAllBooks");
			return books;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			LOGGER.debug(ex.getSQLState());
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			LOGGER.debug(ex.getMessage());
			throw ex;
		}
	}
	
	
	public Book getBook(String uid) throws CacheException,SQLException,Exception{
		//LOGGER.entering("BookService", "getBookById");
		try{
			Book book = null;
			book = cache.get(uid);
			if(book == null){
				book = dao.getBook(uid);
				if(book!=null){
					cache.insert(uid, book);
				}
			}
			//LOGGER.exiting("BookService", "getBookById");
			return book;
		}
		catch(CacheException ex){
			sendExceptionMail(ExceptionType.CACHE , ex);
			LOGGER.debug(ex.getMessage());
			throw ex;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			LOGGER.debug(ex.getSQLState());
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			LOGGER.debug(ex.getMessage());
			throw ex;
		}
	}
	
	
	//insert book method with book argument
	public boolean createBook(Book book) throws SQLException,Exception{
		//LOGGER.entering("BookService", "getBookById");
		try{
			boolean inserted = dao.createBook(book);
			//LOGGER.exiting("BookService", "insertBook");
			return inserted;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			LOGGER.debug(ex.getSQLState());
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			LOGGER.debug(ex.getMessage());
			throw ex;
		}
	}
	
	public boolean updateBook(Book book) throws SQLException,Exception{
		LOGGER.trace("Entering updateBook");
		boolean updated = false;
		try{
			updated = dao.updateBook(book);
			LOGGER.trace("Leaving updateBook");
			return updated;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			LOGGER.debug(ex.getSQLState());
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			LOGGER.debug(ex.getMessage());
			throw ex;
		}
	}
	
	
	public boolean deleteBooks() throws SQLException,Exception{
		
		return false;
	}
	
	
	public boolean deleteBook(String uid) throws SQLException,Exception{
		return false;
	}
}
