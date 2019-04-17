package com.sharebooks.requestProcessor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
//		String json = request.getReader().lines().collect(Collectors.joining(System.lineSeparator()));
//		return json;
		
		BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream()));
	    StringBuilder sb = new StringBuilder();
	    String line = reader.readLine();
	    while (line != null) {
	      sb.append(line + "\n");
	      line = reader.readLine();
	    }
	    reader.close();
	    String jsonStr = sb.toString();
	    
	    return jsonStr;
	}

}
