package com.sharebooks.exception;

public enum ExceptionType {
	UNIDENTIFIED(1),
	CACHE(2),
	SQL(3),
	CONNECTION_POOL(4),
	FACTORY(5),
	JSON_SERIALIZATION(6);
	
	private int id;
	
	private ExceptionType(int id){
		this.id = id;
	}
	
	public int id(){
		return id;
	}
}
