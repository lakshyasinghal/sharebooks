package com.sharebooks.response.enums;


/*The error enumeration will contain the exception status and their respective codes
These are meant for the developers for debugging purpose */

/*The codes will be categorized
Example 1-100 will belong to incorrect request errors
100-200 will belong to database errors.

The error 0 will be a special error status indicating that everything went fine.It will an abstract error status meant for the developer.The 
necessary status code will be available via status code meant for end users.*/
public enum ApplicationError {
	EVERYTHING_WENT_FINE(0,"Everything went fine."),
	UNKNOWN_ERROR(1,"Unknown error occurred. Please see the logs for more details or debug."),
	CACHE_ERROR(2, "Error occurred in cache."),
	CONNECTION_POOL_ERROR(3, "Error occurred in connection pool."),
	GENERAL_EXCEPTION(4, "Some genric exception occurred. You will need to debug to know the exact reason."),
	INPUT_JSON_READ_ERROR(5 , "Error occurred during reading input json. Please check the json being sent to the server."),
	JSON_SERIALIZATION_ERROR(6,"Error occurred during serialization."),
	JSON_DESERIALIZATION_ERROR(7,"Error occurred during deserialization."),
	FACTORY_ERROR(8,"Error occurred in factory."),
	MULTIPLE_INSTANCE_ERROR(9,"Multiple instances created from a class meant to be singleton."),
	NON_FUNCTIONAL_METHOD_ERROR(10,"You are trying to access a method which is not functional. Please check your method calls."),
	UNSAFE_ACCESS_ERROR(11,""),
	DATABASE_ERROR(12, "Error occurred in database operation."),
	VALIDATION_ERROR(13, "Error occurred in validation.");
	
	private int id;
	private String desc;
	
	private ApplicationError(int id , String desc){
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
