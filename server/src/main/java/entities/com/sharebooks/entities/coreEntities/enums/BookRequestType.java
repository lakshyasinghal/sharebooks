package com.sharebooks.entities.coreEntities.enums;

public enum BookRequestType {
	RENT(1 , "rent") , BUY(2 , "buy");
	
	private int id;
	private String description;
	
	private BookRequestType(int id , String description){
		this.id = id;
		this.description = description;
	}
	
	public static BookRequestType valueOf(int id){
		switch(id){
		case 1:
			return RENT;
		case 2:
			return BUY;
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
