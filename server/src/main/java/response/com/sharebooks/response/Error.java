package com.sharebooks.response;

public enum Error {
	DATABASE_ERROR(1, "Error occurred in database operation."),
	CACHE_ERROR(2, "Error occurred in cache."),
	CONNECTION_POOL_ERROR(3, "Error occurred in connection pool."),
	GENERAL_EXCEPTION(4, "Some genric exception occurred. You will need to debug to know the exact reason."),
	INPUT_JSON_READ_ERROR(5 , "Error occurred during reading input json. Please check the json being sent to the server."),
	ID_NOT_AVAILABLE_IN_REQUEST(6 , "Book id is not available in get request.");
	
	private int id;
	private String desc;
	
	private Error(int id , String desc){
		this.id = id;
		this.desc = desc;
	}
	
	public int id(){
		return id;
	}
	
	public String desc(){
		return desc;
	}
}
