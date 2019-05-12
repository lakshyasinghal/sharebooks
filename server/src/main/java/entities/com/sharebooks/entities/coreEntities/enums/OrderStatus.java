package com.sharebooks.entities.coreEntities.enums;

public enum OrderStatus {
	INCOMPLETE(1 , "Incomplete") , COMPLETE(2 , "Complete");
	
	private int id;
	private String description;
	
	private OrderStatus(int id , String description){
		this.id = id;
		this.description = description;
	}
	
	public static OrderStatus valueOf(int id){
		switch(id){
		case 1:
			return INCOMPLETE;
		case 2:
			return COMPLETE;
		default : 
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
