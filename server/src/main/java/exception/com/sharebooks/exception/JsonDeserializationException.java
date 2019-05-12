package com.sharebooks.exception;

public class JsonDeserializationException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String message = "Exception occurred while deserializing";
	
	public JsonDeserializationException(){}

	public JsonDeserializationException(String message){
		super(JsonDeserializationException.message + "\n" + message);
	}
}
