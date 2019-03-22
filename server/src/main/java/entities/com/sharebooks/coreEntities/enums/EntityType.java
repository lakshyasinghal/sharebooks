package com.sharebooks.coreEntities.enums;

public enum EntityType {
	BOOK(1 , "book") , USER(2 , "user") , BOOK_REQUEST(3 , "bookRequest") , ORDER(4 , "order");
	
	private int id;
	private String desc; //description
	
	private EntityType(int id , String desc){
		this.id = id;
		this.desc = desc;
	}
	
	public static EntityType valueOf(int id){
		switch(id){
		case 1:
			return BOOK;
		case 2:
			return USER;
		case 3:
			return BOOK_REQUEST;
		case 4:
			return ORDER;
		default:
			return null;
		}
	}
	
	public int id(){
		return id;
	}
	
	public String desc(){
		return desc;
	}
}
