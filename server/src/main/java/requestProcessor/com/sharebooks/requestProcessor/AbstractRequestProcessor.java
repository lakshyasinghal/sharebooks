package com.sharebooks.requestProcessor;

import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;


public abstract class AbstractRequestProcessor{
	
	
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
		String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
		return json;
	}

}
