package com.sharebooks.services.entityServices;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
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
	private final EntityFactory<Book> factory;
	
	private BookService(Cache<Book> cache , BookDao dao , EntityFactory<Book> factory){
		this.cache = cache;
		this.dao = dao;
		this.factory = factory;
	}
	
	public static void init(Cache<Book> cache , BookDao dao , EntityFactory<Book> factory){
		if(bookService == null){
			bookService = new BookService(cache , dao , factory);
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
			LOGGER.log(Level.SEVERE, ex.getSQLState(), ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			throw ex;
		}
	}
	
	
	public Book getBookById(int id) throws CacheException,SQLException,Exception{
		//LOGGER.entering("BookService", "getBookById");
		try{
			Book book = null;
			book = cache.get(id);
			if(book == null){
				book = dao.getBookById(id);
				if(book!=null){
					cache.insert(id, book);
				}
			}
			//LOGGER.exiting("BookService", "getBookById");
			return book;
		}
		catch(CacheException ex){
			sendExceptionMail(ExceptionType.CACHE , ex);
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			throw ex;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			LOGGER.log(Level.SEVERE, ex.getSQLState(), ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			throw ex;
		}
	}
	
	
	//insert book method with book argument
	public boolean insertBook(Book book) throws SQLException,Exception{
		//LOGGER.entering("BookService", "getBookById");
		try{
			boolean inserted = dao.insertBook(book);
			//LOGGER.exiting("BookService", "insertBook");
			return inserted;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			LOGGER.log(Level.SEVERE, ex.getSQLState(), ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			throw ex;
		}
	}
	
	
	//insert book method with bookJson argument
	public boolean insertBook(String bookJson) throws SQLException,Exception{
		//LOGGER.entering("BookService", "insertBook");
		try{
			Book book = factory.createFromJson(bookJson);
			boolean inserted = dao.insertBook(book);
			//LOGGER.exiting("BookService", "insertBook");
			return inserted;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			LOGGER.log(Level.SEVERE, ex.getSQLState(), ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
			throw ex;
		}
	}
	
	
	public boolean deleteBooks() throws SQLException,Exception{
		
		return false;
	}
	
	
	public boolean deleteBookById(int id) throws SQLException,Exception{
		return false;
	}
}
