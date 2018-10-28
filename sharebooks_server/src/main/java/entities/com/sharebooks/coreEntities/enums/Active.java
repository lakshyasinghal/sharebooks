package com.sharebooks.coreEntities.enums;

public enum Active {
	
	ACTIVE(1 , "active") , INACTIVE(0 , "inactive");
	
	private int id;
	private String description;
	
	private Active(int id , String description){
		this.id = id;
		this.description = description;
	}
	
	public static Active valueOf(int id){
		switch(id){
		case 1 :
			return ACTIVE;
		case 2 :
			return INACTIVE;
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
