package com.sharebooks.coreEntities.enums;

public enum RequestStatus {
	PENDING(1 , "Pending") , ACCEPTED(2 , "Accepted") , REJECTED(3 , "Rejected");
	
	private int id;
	private String description;
	
	private RequestStatus(int id , String description){
		this.id = id;
		this.description = description;
	}
	
	public static RequestStatus valueOf(int id){
		switch(id){
		case 1:
			return PENDING;
		case 2:
			return ACCEPTED;
		case 3:
			return REJECTED;
		default:
			return null;
		}
	}
	
	public int id(){
		return id;
	}
	
	public String description(){
		return description;
	}
}
