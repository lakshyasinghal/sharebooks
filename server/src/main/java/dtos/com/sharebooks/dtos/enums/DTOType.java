package com.sharebooks.dtos.enums;

public enum DTOType {
	RESULT(1,"Result DTO"),
	SUMMARY(2,"Summary");
	
	private int id;
	private String description;
	
	private DTOType(int id, String desc){
		this.id = id;
		this.description = desc;
	}
	
	
	public String desc(){
		return description;
	}
	
	public int id(){
		return id;
	}
}
