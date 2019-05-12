package com.sharebooks.requestProcessor;

import java.util.*;
import org.apache.log4j.Logger;
import javax.servlet.http.HttpServletRequest;

import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.exception.*;
import com.sharebooks.factory.entityFactory.EntityFactory;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.requestProcessor.AbstractRequestProcessor;
import com.sharebooks.requestProcessor.requestValidators.BookRequestValidator;
import com.sharebooks.requestProcessor.requestValidators.ValidationMessage;
import com.sharebooks.response.*;
import com.sharebooks.response.enums.Status;
import com.sharebooks.services.entityServices.BookService;
import com.sharebooks.sources.FactorySource;
//import com.sharebooks.sources.PropertySource;
import com.sharebooks.sources.ServiceSource;


/*This class will handle all the processing logic related to the HttpServletRequest like getting input json , getting form params etc.
Will call the service class to perform required operations. Will obtain the results and form response object using the response parameters
and will finally return the json response string 
If there isn't any exception during the process the request will be considered successful and an appropriate status code will be 
generated. But in case there is an exception the request will be considered erroneous and an appropriate error response with code will be generated.*/
@SuppressWarnings("unchecked")
public class BookRequestProcessor extends AbstractRequestProcessor{
	private static BookRequestProcessor processor = new BookRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(BookRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final BookService bookService = ServiceSource.getBookService();
	private final EntityFactory<Book> factory = (EntityFactory<Book>) FactorySource.getEntityFactory(EntityType.BOOK.desc());
	private final BookRequestValidator validator = BookRequestValidator.getInstance();
	
	//private constructor to help make the class singleton
	private BookRequestProcessor(){
		
	}
	
	
	//get singleton instance of the class
	public static BookRequestProcessor getInstance(){
		return processor;
	}
	
	
	public String processGetAllBooksRequest() throws Exception{
		//LOGGER.entering("BookRequestProcessor", "processGetAllBooksRequest");
		Map<String,Object> map = new HashMap<String,Object>();
		List<Book> books = null;
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		try{
			books = bookService.getAllBooks();
			success = true;
			statusCode = Status.FETCH_ALL_BOOKS_SUCCESSFUL.id();
			map.put("books", books);
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		
		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , map);
		//LOGGER.exiting("BookRequestProcessor", "processGetAllBooksRequest");
		return response.process();
	}
	
	
	//process get book by id request
	public String processGetBookRequest(String uid) throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		Book book = null;
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		try{
			ValidationMessage vm = validator.validateGetBookRequest(uid);
			if(!vm.success()){
				statusCode = vm.statusCode();
				errorCode = vm.errorCode();
			}
			else{
				book = bookService.getBook(uid);
				if(book==null){
					statusCode = Status.NO_RESULTS_FOUND.id();
				}
				else{
					statusCode = Status.FETCH_BOOK_BY_ID_SUCCESSFUL.id();
				}
				success = true;
				map.put("book", book);
			}
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		
		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , map);
		return response.process();
	}
	
	
	
	//needs to be implemented
	public String processGetBooksRequest(){
		return null;
	}
	
	
	
	public String processGetBooksBySearchTermRequest(String searchTerm) throws Exception{
		LOGGER.trace("Entered processGetBooksBySearchTermRequest");
		Map<String,Object> map = new HashMap<String,Object>();
		List<Book> books = null;
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		try{
			books = bookService.getBooksBySearchTerm(searchTerm);
			if(books!=null){
				success = true;
				if(!books.isEmpty()){statusCode = Status.FETCH_BOOKS_BY_SEARCH_TERM_SUCCESSFUL.id();}
				else{statusCode = Status.NO_RESULTS_FOUND.id();}
				map.put("books", books);
			}
			else{
				statusCode = Status.OPERATION_UNSUCCESSFUL.id();
			}
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		
		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , map);
		LOGGER.trace("Leaving processGetBooksBySearchTermRequest");
		return response.process();
	}
	
	
	public String processGetBooksByCategoryRequest(String category) throws Exception{
		LOGGER.trace("Entered processGetBooksByCategoryRequest");
		Map<String,Object> map = new HashMap<String,Object>();
		List<Book> books = null;
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		try{
			books = bookService.getBooksByCategory(category);
			if(books!=null){
				success = true;
				if(!books.isEmpty()){statusCode = Status.FETCH_BOOKS_BY_CATEGORY_SUCCESSFUL.id();}
				else{statusCode = Status.NO_RESULTS_FOUND.id();}
				map.put("books", books);
			}
			else{
				statusCode = Status.OPERATION_UNSUCCESSFUL.id();
			}
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		
		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , map);
		LOGGER.trace("Leaving processGetBooksByCategoryRequest");
		return response.process();
	}
	
	
	//process insert book request
	public String processCreateRequest(HttpServletRequest req) throws Exception {
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		Response response = null;
		String bookJsonStr = null;
		Book book = null;
		try {
			bookJsonStr = getJsonFromRequest(req);
			book = factory.createFromJson(bookJsonStr);
			success = bookService.createBook(book);
			if(success){
				statusCode = Status.BOOK_CREATED_SUCCESSFULLY.id();
			}
			else{
				statusCode = Status.BOOK_NOT_CREATED.id();
			}
		}catch(BookAlreadyExistsException ex){
			success = true;
			statusCode = Status.BOOK_ALREADY_EXISTS.id();
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		
		response = responseFactory.getJsonResponse(success , statusCode , errorCode , null);
		return response.process();
	}
	
	
	
	//to be implemented later
	public String processDeleteBooksRequest(HttpServletRequest req){
		
		return null;
	}
	
	
	//process delete by id request
	public String processDeleteBookRequest(String uid) throws Exception{
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		try{
			boolean deleted = bookService.deleteBook(uid);
			if(deleted){
				statusCode = Status.BOOK_DELETED_SUCCESSFULLY.id();
			}
			else{
				statusCode = Status.BOOK_DELETION_FAILED.id();
			}
			success = true;
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , null);
		return response.process();
	}
	
	
	
	//to be implemented later
	public String processUpdateRequest(HttpServletRequest req) throws Exception{
		LOGGER.trace("Entering processUpdateRequest");
		boolean success = false;
		int statusCode = -1,errorCode = -1;
		String bookJsonStr = null;
		Book book = null;
		try{
			bookJsonStr = getJsonFromRequest(req);
			book = factory.createFromJson(bookJsonStr);
			success = bookService.updateBook(book);
			if(success){
				statusCode = Status.BOOK_UPDATED_SUCCESSFULLY.id();
			}
			else{
				statusCode = Status.BOOK_UPDATED_SUCCESSFULLY.id();
			}
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}

		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , null);
		LOGGER.trace("Leaving processUpdateRequest");
		return response.process();
	}
	
	
	//to be implemented later
	public String processUploadImageSrcRequest(String id , String imgSrc) throws Exception{
		LOGGER.trace("Entering processUploadImageSrcRequest");
		boolean success = false;
		int statusCode = -1,errorCode = -1;
		String bookJsonStr = null;
		Book book = null;
		try{
			//bookJsonStr = getJsonFromRequest(req);
			book = factory.createFromJson(bookJsonStr);
			success = bookService.updateBook(book);
			if(success){
				statusCode = Status.BOOK_UPDATED_SUCCESSFULLY.id();
			}
			else{
				statusCode = Status.BOOK_UPDATED_SUCCESSFULLY.id();
			}
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}

		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , null);
		LOGGER.trace("Leaving processUploadImageSrcRequest");
		return response.process();
	}
}
