package com.sharebooks.requestProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.SQLException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import org.apache.log4j.Logger;
import com.sharebooks.exception.*;
import static com.sharebooks.response.enums.ApplicationError.*;


public abstract class AbstractRequestProcessor{
	private static final Logger LOGGER = Logger.getLogger(AbstractRequestProcessor.class.getName());
	
	protected Map<String,String> getParamsFromRequest(HttpServletRequest request) throws Exception{
		Map<String,String> paramsMap = new HashMap<String,String>();
		Enumeration<String> e = request.getParameterNames();
		String param = null;
		while(e.hasMoreElements()){
			param = e.nextElement();
			paramsMap.put(param, request.getParameter(param));
		}
		return paramsMap;
	}
	
	
	protected String getJsonFromRequest(HttpServletRequest request) throws IOException{
//		String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//		return json;
		LOGGER.trace("Entered getJsonFromRequest");
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
	    StringBuilder sb = new StringBuilder();
	    String line = reader.readLine();
	    while (line != null) {
	      sb.append(line + "\n");
	      line = reader.readLine();
	    }
	    reader.close();
	    String jsonStr = sb.toString();
	    LOGGER.debug("Payload json => "+jsonStr);
	    LOGGER.trace("Entered getJsonFromRequest");
	    return jsonStr;
	}
	
	
	protected void log(Exception ex, Logger LOGGER){
		LOGGER.error("Exception occurred => ",ex);
	}
	
	
	protected int errorCode(Exception ex){
		int errorCode = -1;
		if(ex instanceof SQLException){
			errorCode = DATABASE_ERROR.id();
		}
		else if(ex instanceof CacheException){
			errorCode = CACHE_ERROR.id();
		}
		else if(ex instanceof ConnectionPoolException){
			errorCode = CONNECTION_POOL_ERROR.id();
		}
		else if(ex instanceof FactoryException){
			errorCode = FACTORY_ERROR.id();
		}
		else if(ex instanceof JsonDeserializationException){
			errorCode = JSON_DESERIALIZATION_ERROR.id();
		}
		else if(ex instanceof JsonSerializationException){
			errorCode = JSON_SERIALIZATION_ERROR.id();
		}
		else if(ex instanceof MultipleInstanceException){
			errorCode = MULTIPLE_INSTANCE_ERROR.id();
		}
		else if(ex instanceof NonFunctionalMethodException){
			errorCode = NON_FUNCTIONAL_METHOD_ERROR.id();
		}
		else if(ex instanceof UnsafeAccessException){
			errorCode = UNSAFE_ACCESS_ERROR.id();
		}
		else if(ex instanceof ValidationException){
			errorCode = VALIDATION_ERROR.id();
		}
		else{
			errorCode = UNKNOWN_ERROR.id();
		}
		
		return errorCode;
	}

}
