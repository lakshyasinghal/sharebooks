package com.sharebooks.database.sql;

//this table will contain table names in the database in the desc part
public enum Table {
	
	BOOKS(1,"Books") , USERS(2,"Users") , BOOK_REQUESTS(3,"BookRequests") , ORDERS(4,"Orders") , NOTIFICATIONS(5 , "Notifications")
	 , CITIES(6 , "Cities") , STATES(7 , "States"), BOOK_CATEGORIES(8, "BookCategories");
	
	private int id;
	private String desc;
	
	private Table(int id , String desc){
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
