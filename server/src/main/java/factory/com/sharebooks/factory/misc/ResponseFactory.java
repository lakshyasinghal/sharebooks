package com.sharebooks.factory.misc;

import java.util.List;

import com.sharebooks.entity.Entity;
import com.sharebooks.response.JsonResponse;
import com.sharebooks.response.Response;

public class ResponseFactory {
	private static ResponseFactory factory = new ResponseFactory();
	
	private ResponseFactory(){
		
	}
	
	public static ResponseFactory getInstance(){
		return factory;
	}
	
	public Response getJsonResponse(boolean isSuccessful , int statusCode , int errorCode , List<? extends Entity> list) throws Exception{
		JsonResponse response  = new JsonResponse(isSuccessful , statusCode , errorCode , list);
		return response;
	}
}
