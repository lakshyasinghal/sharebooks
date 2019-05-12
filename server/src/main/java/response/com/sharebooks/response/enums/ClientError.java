package com.sharebooks.response.enums;

//The client error codes will be used in case invalid path params are coming from client side
//We will start the codes from 101 to prevent clash with application error codes
public enum ClientError {
	EMPTY_UID(101,"Uid is empty."),
	INVALID_UID(102,"Invalid uid.");
	
	private int id;
	private String description;
	
	private ClientError(int id, String desc){
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
