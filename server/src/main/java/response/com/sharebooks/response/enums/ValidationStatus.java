package com.sharebooks.response.enums;

//The status codes from this enum will be used when user input is not correct
public enum ValidationStatus {
	//For user form
	INVALID_MOBILE_NUMBER(2001,"Invalid mobile number."),
	INVALID_USERNAME(2002,"The username must be a valid email id."),
	INVALID_NAME(2003,"Provided name is not a valid one."),
	INVALID_CITY(2004,"The city should be selected from the given list."),
	INVALID_STATE(2005,"The state should be selected from the given list."),
	INVALID_PINCODE(2006,"Invalid pincode. Please enter a six digit pincode."),
	
	//For book form
	INVALID_TITLE(2101,"Invalid mobile number."),
	INVALID_AUTHOR(2102,"The username must be a valid email id."),
	INVALID_PAGE_NUMBER(2103,"Provided name is not a valid one."),
	INVALID_CATEGORY(2104,"The city should be selected from the given list.");
	
	
	
	private int id;
	private String description;
	
	private ValidationStatus(int id, String desc){
		this.id = id;
		this.description = desc;
	}
	
	public int id(){
		return id;
	}
	
	public String desc(){
		return description;
	}
}
