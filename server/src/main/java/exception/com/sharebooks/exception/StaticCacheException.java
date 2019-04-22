package com.sharebooks.exception;

public class StaticCacheException extends Exception{
	private static final long serialVersionUID = 1L;
	private static final String message = "Static cache exception.";
	
	public StaticCacheException(){
		this("");
	} 
	
	public StaticCacheException(String message){
		super(StaticCacheException.message + "\n" + message);
	}
}
