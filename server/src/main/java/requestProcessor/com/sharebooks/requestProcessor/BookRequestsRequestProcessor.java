package com.sharebooks.requestProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;

import com.sharebooks.entities.coreEntities.BookRequest;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.factory.entityFactory.EntityFactory;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.response.Response;
import com.sharebooks.response.enums.Status;
import com.sharebooks.services.entityServices.BookRequestService;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;

/*This class will handle all the processing logic related to the HttpServletRequest like getting input json , getting form params etc.
Will call the service class to perform required operations. Will obtain the results and form response object using the response parameters
and will finally return the json response string 
If there isn't any exception during the process the request will be considered successful and an appropriate status code will be 
generated. But in case there is an exception the request will be considered erroneous and an appropriate error response with code will be generated.*/
@SuppressWarnings("unchecked")
public class BookRequestsRequestProcessor extends AbstractRequestProcessor {
	private static BookRequestsRequestProcessor processor = new BookRequestsRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(BookRequestsRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final BookRequestService bookRequestService = ServiceSource.getBookRequestService();
	private final EntityFactory<BookRequest> factory = (EntityFactory<BookRequest>) FactorySource.getEntityFactory(EntityType.BOOK_REQUEST.desc());
	
	//private constructor to help make the class singleton
	private BookRequestsRequestProcessor(){
		
	}
	
	
	//get singleton instance of the class
	public static BookRequestsRequestProcessor getInstance(){
		return processor;
	}
	
	
	//the uid is the user's uid which will be used to get book requests fo both types
	//type1 where user will be the receiver or the owner
	//type2 where user will be the sender or requester
	public String processGetBookRequestsByUid(String uid) throws Exception {
		LOGGER.trace("Entering processGetBookRequestsByUid");
		Map<String,Object> map = new HashMap<String,Object>();
		List<List<BookRequest>> bookRequestListList = null;
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		Response response = null;
		try {
			bookRequestListList = bookRequestService.getBookRequestsByUid(uid);
			List<BookRequest> receivedBookRequests = bookRequestListList.get(0);
			List<BookRequest> sentBookRequests = bookRequestListList.get(1);
			success = true;
			statusCode = Status.FETCH_BOOK_REQUESTS_BY_UID_SUCCESSFUL.id();
			map.put("received", receivedBookRequests);
			map.put("sent", sentBookRequests);
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		response = responseFactory.getJsonResponse(success , statusCode , errorCode , map);
		LOGGER.trace("Leaving processGetBookRequestsByUid");
		return response.process();
	}
		
		
		
	
	public String processCreateBookRequest(HttpServletRequest req) throws Exception {
		LOGGER.trace("Entering processCreateRequest");
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		Response response = null;
		String bookJsonStr = null;
		BookRequest bookRequest = null;
		try {
			bookJsonStr = getJsonFromRequest(req);
			bookRequest = factory.createFromJson(bookJsonStr);
			success = bookRequestService.createBookRequest(bookRequest);
			if(success){
				statusCode = Status.BOOK_REQUEST_CREATED_SUCCESSFULLY.id();
			}
			else{
				statusCode = Status.BOOK_REQUEST_NOT_CREATED.id();
			}
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		
		response = responseFactory.getJsonResponse(success , statusCode , errorCode , null);
		LOGGER.trace("Leaving processCreateRequest");
		return response.process();
	}
	

	
	
	
	//to be implemented later
	public String processUpdateBookRequest(HttpServletRequest req) throws Exception{
		LOGGER.trace("Entering processUpdateRequest");
		boolean success = false;
		int statusCode = -1,errorCode = -1;
		String bookJsonStr = null;
		BookRequest bookRequest = null;
		try{
			bookJsonStr = getJsonFromRequest(req);
			bookRequest = factory.createFromJson(bookJsonStr);
			success = bookRequestService.updateBookRequest(bookRequest);
			if(success){
				statusCode = Status.BOOK_REQUEST_UPDATED_SUCCESSFULLY.id();
			}
			else{
				statusCode = Status.BOOK_REQUEST_NOT_UPDATED.id();
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
	
	
	public String processAcceptBookRequest(String uid) throws Exception{
		LOGGER.trace("Entering processAcceptBookRequest");
		boolean success = false;
		int statusCode = -1,errorCode = -1;
		try{
			success = bookRequestService.acceptBookRequest(uid);
			if(success){
				statusCode = Status.BOOK_REQUEST_ACCEPTED_SUCCESSFULLY.id();
			}
			else{
				statusCode = Status.BOOK_REQUEST_COULD_NOT_BE_ACCEPTED.id();
			}
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}

		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , null);
		LOGGER.trace("Leaving processAcceptBookRequest");
		return response.process();
	}
	
	public String processRejectBookRequest(String uid) throws Exception{
		LOGGER.trace("Entered processRejectBookRequest.");
		boolean success = false;
		int statusCode = -1,errorCode = -1;
		try{
			success = bookRequestService.rejectBookRequest(uid);
			if(success){
				statusCode = Status.BOOK_REQUEST_REJECTED_SUCCESSFULLY.id();
			}
			else{
				statusCode = Status.BOOK_REQUEST_COULD_NOT_BE_REJECTED.id();
			}
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		
		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , null);
		LOGGER.trace("Leaving processRejectBookRequest.");
		return response.process();
	}
	
}
