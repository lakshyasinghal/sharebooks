package com.sharebooks.factory.misc;


import java.util.Map;
import com.sharebooks.response.JsonResponse;
import com.sharebooks.response.Response;

public class ResponseFactory {
	private static ResponseFactory factory = new ResponseFactory();
	
	private ResponseFactory(){
		
	}
	
	public static ResponseFactory getInstance(){
		return factory;
	}
	
	public Response getJsonResponse(boolean success , int statusCode , int errorCode , Map<String,Object> map) throws Exception{
		JsonResponse response  = new JsonResponse(success , statusCode , errorCode , map);
		return response;
	}
}
