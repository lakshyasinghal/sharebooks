package com.sharebooks.requestProcessor;

import java.util.*;
import java.util.logging.Level;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;
import com.sharebooks.coreEntities.Book;
import com.sharebooks.exception.*;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.requestProcessor.AbstractRequestProcessor;
import com.sharebooks.response.*;
import com.sharebooks.response.Error;
import com.sharebooks.services.entityServices.BookService;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;


/*This class will handle all the processing logic related to the HttpServletRequest like getting input json , getting form params etc.
Will call the service class to perform required operations. Will obtain the results and form response object using the response parameters
and will finally return the json response string 
If there isn't any exception during the process the request will be considered successful and an appropriate status code will be 
generated. But in case there is an exception the request will be considered erroneous and an appropriate error code will be generated.*/
public class BookRequestProcessor extends AbstractRequestProcessor{
	private static BookRequestProcessor processor = new BookRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(BookRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final BookService bookService = ServiceSource.getBookService();
	private static final String EMPTY_STRING = "";
	
	//private constructor to help make the class singleton
	private BookRequestProcessor(){
		
	}
	
	
	//get singleton instance of the class
	public static BookRequestProcessor getInstance(){
		return processor;
	}
	
	
	public String processGetAllBooksRequest() throws Exception{
		//LOGGER.entering("BookRequestProcessor", "processGetAllBooksRequest");
		List<Book> books = null;
		boolean isSuccessful = false;
		int statusCode = -1;
		int errorCode = -1;
		try{
			books = bookService.getAllBooks();
			isSuccessful = true;
			statusCode = Status.FETCH_ALL_ENTITIES_SUCCESSFUL.id();
		}
		catch(CacheException ex){
			isSuccessful = false;
			errorCode = Error.CACHE_ERROR.id();
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
		catch(SQLException ex){
			isSuccessful = false;
			errorCode = Error.DATABASE_ERROR.id();
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
		catch(Exception ex){
			isSuccessful = false;
			errorCode = Error.GENERAL_EXCEPTION.id();
			LOGGER.log(Level.SEVERE, ex.getMessage(), ex);
		}
		LOGGER.fine("isSuccessful : " + isSuccessful);
		LOGGER.fine("statusCode : " + statusCode);
		LOGGER.fine("errorCode : " + errorCode);
		Response response = responseFactory.getJsonResponse(isSuccessful , statusCode , errorCode , books);
		//LOGGER.exiting("BookRequestProcessor", "processGetAllBooksRequest");
		return response.process();
	}
	
	
	//process get book by id request
	public String processGetBookByIdRequest(String id) throws Exception{
		List<Book> books = new ArrayList<Book>();
		Book book = null;
		boolean isSuccessful = false;
		int statusCode = -1;
		int errorCode = -1;
		try{
			if(id==null || id.equals(EMPTY_STRING)){
				errorCode = Error.ID_NOT_AVAILABLE_IN_REQUEST.id();
			}
			else{
				book = bookService.getBookById(Integer.parseInt(id));
				if(book==null){
					statusCode = Status.NO_RESULT_AVAILABLE_FOR_GIVEN_ID.id();
				}
				else{
					statusCode = Status.FETCH_BY_ID_SUCCESSFUL.id();
				}
				isSuccessful = true;
			}
		}
		catch(CacheException ex){
			isSuccessful = false;
			errorCode = Error.CACHE_ERROR.id();
		}
		catch(SQLException ex){
			isSuccessful = false;
			errorCode = Error.DATABASE_ERROR.id();
		}
		catch(Exception ex){
			isSuccessful = false;
			errorCode = Error.GENERAL_EXCEPTION.id();
		}
		books.add(book);
		Response response = responseFactory.getJsonResponse(isSuccessful , statusCode , errorCode , books);
		return response.process();
	}
	
	
	
	//needs to be implemented
	public String processGetBooksRequest(){
		return null;
	}
	
	
	//process insert book request
	public String processInsertBookRequest(HttpServletRequest req) throws Exception {
		boolean isSuccessful = false;
		int statusCode = -1;
		int errorCode = -1;
		Response response = null;
		String bookJson = null;
		try {
			bookJson = getJsonFromRequest(req);
		} catch (IOException e) {
			errorCode = Error.INPUT_JSON_READ_ERROR.id();
			response = responseFactory.getJsonResponse(isSuccessful , statusCode , errorCode , null);
			return response.process();
		}
		
		try{
			isSuccessful = bookService.insertBook(bookJson);
			statusCode = Status.INSERT_ENTITY_SUCCESSFUL.id();
		}
		catch(SQLException ex){
			isSuccessful = false;
			errorCode = Error.DATABASE_ERROR.id();
		}
		catch(Exception ex){
			isSuccessful = false;
			errorCode = Error.GENERAL_EXCEPTION.id();
		}
		
		response = responseFactory.getJsonResponse(isSuccessful , statusCode , errorCode , null);
		return response.process();
	}
	
	
	
	//to be implemented later
	public String processDeleteBooksRequest(HttpServletRequest req){
		
		return null;
	}
	
	
	//process delete by id request
	public String processDeleteBookByIdRequest(String id) throws Exception{
		boolean isSuccessful = false;
		int statusCode = -1;
		int errorCode = -1;
		try{
			if(id==null || id.equals(EMPTY_STRING)){
				errorCode = Error.ID_NOT_AVAILABLE_IN_REQUEST.id();
			}
			else{
				boolean deleted = bookService.deleteBookById(Integer.parseInt(id));
				if(deleted){
					statusCode = Status.DELETE_BY_ID_SUCCESSFUL.id();
				}
				else{
					statusCode = Status.DELETE_BY_ID_FAILED.id();
				}
				isSuccessful = true;
			}
		}
		catch(CacheException ex){
			isSuccessful = false;
			errorCode = Error.CACHE_ERROR.id();
		}
		catch(SQLException ex){
			isSuccessful = false;
			errorCode = Error.DATABASE_ERROR.id();
		}
		catch(Exception ex){
			isSuccessful = false;
			errorCode = Error.GENERAL_EXCEPTION.id();
		}
		Response response = responseFactory.getJsonResponse(isSuccessful , statusCode , errorCode , null);
		return response.process();
	}
	
	
	
	//to be implemented later
	public String processUpdateBooksRequest(){
		return null;
	}
	
	
	
	public String processUpdateBookByIdRequest(){
		return null;
	}
}
