package com.sharebooks.requestProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.sharebooks.entities.helperEntities.State;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.response.Response;
import com.sharebooks.response.enums.Status;
import com.sharebooks.services.entityServices.MiscService;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;

public class MiscRequestProcessor extends AbstractRequestProcessor{
	private static MiscRequestProcessor processor = new MiscRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(MiscRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final MiscService miscService = ServiceSource.getMiscService();
	
	//private constructor to help make the class singleton
	private MiscRequestProcessor(){
		
	}
	
	//get singleton instance of the class
	public static MiscRequestProcessor getInstance(){
		return processor;
	}
	
	
	public String processGetStatesRequest() throws Exception{
		//LOGGER.entering("BookRequestProcessor", "processGetAllBooksRequest");
		Map<String,Object> map = new HashMap<String,Object>();
		List<State> states = null;
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		try{
			states = miscService.getStates();
			success = true;
			statusCode = Status.FETCH_STATES_SUCCESSFUL.id();
			map.put("states", states);
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		
		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , map);
		//LOGGER.exiting("BookRequestProcessor", "processGetAllBooksRequest");
		return response.process();
	}
	
}
