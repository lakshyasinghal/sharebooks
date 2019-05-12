package com.sharebooks.entities.coreEntities.enums;

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
		case 0 :
			return INACTIVE;
		case 1 :
			return ACTIVE;
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
