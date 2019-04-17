package com.sharebooks.exception;


public class BookAlreadyExistsException extends Exception {
	
	private static final long serialVersionUID = 1L;
	private static final String message = "Book Already exists in database";
	
	public BookAlreadyExistsException(){
		super(message);
	}
}
