package com.sharebooks.requestProcessor;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;
import com.sharebooks.exception.CacheException;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.helperEntities.BookCategory;
import com.sharebooks.response.Error;
import com.sharebooks.response.Response;
import com.sharebooks.response.Status;
import com.sharebooks.services.entityServices.MiscService;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;

public class BookCategoryRequestProcessor {
	private static BookCategoryRequestProcessor processor = new BookCategoryRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(BookCategoryRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final MiscService miscService = ServiceSource.getMiscService();
	
	//private constructor to help make the class singleton
	private BookCategoryRequestProcessor(){
		
	}
	
	//get singleton instance of the class
	public static BookCategoryRequestProcessor getInstance(){
		return processor;
	}
	
	
	public String processGetBookCategoriesRequest() throws Exception{
		LOGGER.trace("Entered processGetBookCategoriesRequest");
		Map<String,Object> map = new HashMap<String,Object>();
		List<BookCategory> bookCategories = null;
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		try{
			bookCategories = miscService.getBookCategories();
			if(bookCategories!=null){
				success = true;
				statusCode = Status.FETCH_BOOK_CATEGORIES_SUCCESSFUL.id();
				map.put("bookCategories", bookCategories);
			}
			else{
				statusCode = Status.BOOK_CATEGORIES_COULD_NOT_BE_FETCHED.id();
			}
		}
		catch(CacheException ex){
			errorCode = Error.CACHE_ERROR.id();
			LOGGER.error("",ex);
		}
		catch(SQLException ex){
			errorCode = Error.DATABASE_ERROR.id();
			LOGGER.error("",ex);
		}
		catch(Exception ex){
			errorCode = Error.GENERAL_EXCEPTION.id();
			LOGGER.error("",ex);
		}
		
		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , map);
		LOGGER.trace("Leaving processGetBookCategoriesRequest");
		return response.process();
	}
}
