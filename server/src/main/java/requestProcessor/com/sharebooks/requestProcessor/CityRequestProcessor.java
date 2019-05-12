package com.sharebooks.requestProcessor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.sharebooks.entities.helperEntities.City;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.response.Response;
import com.sharebooks.response.enums.Status;
import com.sharebooks.services.entityServices.MiscService;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.sources.ServiceSource;

public class CityRequestProcessor extends AbstractRequestProcessor{
	private static CityRequestProcessor processor = new CityRequestProcessor();
	private static final Logger LOGGER = Logger.getLogger(CityRequestProcessor.class.getName());
	private final ResponseFactory responseFactory = FactorySource.getResponseFactory();
	private final MiscService miscService = ServiceSource.getMiscService();
	
	//private constructor to help make the class singleton
	private CityRequestProcessor(){
		
	}
	
	//get singleton instance of the class
	public static CityRequestProcessor getInstance(){
		return processor;
	}
	
	
	public String processGetCitiesRequest() throws Exception{
		LOGGER.trace("Entered processGetCitiesRequest");
		Map<String,Object> map = new HashMap<String,Object>();
		List<City> cities = null;
		boolean success = false;
		int statusCode = -1;
		int errorCode = -1;
		try{
			cities = miscService.getCities();
			if(cities!=null){
				success = true;
				statusCode = Status.FETCH_CITIES_SUCCESSFUL.id();
				map.put("cities", cities);
			}
			else{
				statusCode = Status.CITIES_COULD_NOT_BE_FETCHED.id();
			}
		}
		catch(Exception ex){
			log(ex,LOGGER);
			errorCode = errorCode(ex);
		}
		
		Response response = responseFactory.getJsonResponse(success , statusCode , errorCode , map);
		LOGGER.trace("Leaving processGetCitiesRequest");
		return response.process();
	}
}
