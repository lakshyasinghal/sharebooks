package com.sharebooks.database.sql;

public enum Database {
	
	SHAREBOOKS(1 , "Sharebooks");
	
	private int id;
	private String desc;
	
	private Database(int id , String desc){
		this.id = id;
		this.desc = desc;
	}
	
	public int id(){
		return id;
	}
	
	public String desc(){
		return desc;
	}
}
