package com.sharebooks.sources.enums;

public enum DaoType {
	SQL(1,"SQL"),
	MYBATIS(2,"MYBATIS");
	
	private int id;
	private String desc;
	
	private DaoType(int id, String desc){
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
