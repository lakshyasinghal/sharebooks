package com.sharebooks.requestProcessor.requestValidators;

public class ValidationMessage {
	private boolean success;
	private int statusCode = -1;
	private int errorCode = -1;
	
	public ValidationMessage(){
		success = true;
	}
	
	public ValidationMessage(boolean success){
		this.success = success;
	}
	
	public ValidationMessage(boolean success, int statusCode, int errorCode){
		this.success = success;
		this.statusCode = statusCode;
		this.errorCode = errorCode;
	}
	
	public boolean success(){
		return success;
	}
	
	public int statusCode(){
		return statusCode;
	}
	
	public int errorCode(){
		return errorCode;
	}
}
