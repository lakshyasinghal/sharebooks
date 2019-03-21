package com.sharebooks.coreEntities.enums;

public enum AvailableStatus {
	NOT_AVAILABLE(0 , "Not Available") , AVAILABLE(1 , "Available");
	
	private final int id;
	private final String description;
	
	private AvailableStatus(int id , String description){
		this.id = id;
		this.description = description;
	}
	
	public static AvailableStatus valueOf(int id){
		if(id==0){
			return NOT_AVAILABLE;
		}
		else if(id==1){
			return AVAILABLE;
		}
		else{
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
