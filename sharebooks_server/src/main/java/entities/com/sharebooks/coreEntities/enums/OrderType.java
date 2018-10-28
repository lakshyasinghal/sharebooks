package com.sharebooks.coreEntities.enums;

public enum OrderType {
	RENT(1 , "rent") , BUY(2 , "buy");
	
	private int id;
	private String description;
	
	private OrderType(int id , String description){
		this.id = id;
		this.description = description;
	}
	
	public static OrderType valueOf(int id){
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
