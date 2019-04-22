package com.sharebooks.exception;

public class MultipleInstanceException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private static final String message = "Non-functional method.Please check the code from where this method is being called.";
	
	public MultipleInstanceException(){
		this("");
	} 
	
	public MultipleInstanceException(String message){
		super(MultipleInstanceException.message + "\n" + message);
	}
}
