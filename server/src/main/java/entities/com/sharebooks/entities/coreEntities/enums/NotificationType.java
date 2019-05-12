package com.sharebooks.entities.coreEntities.enums;

import java.util.*;

public enum NotificationType {
	BOOK_REQUEST(1 , "New Book Request") , BOOK_RETURN_REQUEST(2 , "Book Return Request") , NEW_BOOK_IN_STORE(3 , "New Book Added") , 
	BOOK_REQUEST_ACCEPTANCE(4 , "Book Rquest Accepted") , BOOK_REQUEST_REJECTION(5 , "Book Request Rejected");
	
	private int id;
	private String description;
	
	private NotificationType(int id , String description){
		this.id = id;
		this.description = description;
	}
	
	private static Map<Integer , NotificationType> valuesMap = new HashMap<Integer , NotificationType>();
	
	static{
		for(NotificationType type : NotificationType.values()){
			valuesMap.put(type.id(), type);
		}
	}
	
	public static NotificationType valueOf(int id){
		return valuesMap.get(id);
	}
	
	public int id(){
		return id;
	}
	
	public String description(){
		return description;
	}
}
