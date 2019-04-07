package com.sharebooks.exception;

public final class CacheException extends Exception {

	private static final long serialVersionUID = 1L;
	private static final String message = "Exception occurred in cache";

	public CacheException(String message){
		super(CacheException.message + "\n" + message);
	}
} 
