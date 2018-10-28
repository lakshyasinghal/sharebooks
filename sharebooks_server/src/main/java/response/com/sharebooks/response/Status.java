package com.sharebooks.response;

public enum Status {
	LOGIN_SUCCESSFUL(1 , "User login request successful"),
	LOGIN_CREDENTIALS_INCORRECT(2 , "Incorrect login credentials entered"),
	FETCH_ALL_ENTITIES_SUCCESSFUL(3 , "Fetch all entities request successful"),
	FETCH_ENTITIES_SUCCESSFUL(4 , "Fetch entities request successful"),
	FETCH_BY_ID_SUCCESSFUL(5 , "Fetch entity by id successful"),
	INSERT_ENTITY_SUCCESSFUL(6 , "Entity inserted into storage successfully"),
	NO_RESULT_AVAILABLE_FOR_GIVEN_ID(7 , "No result is available in storage for given id"),
	DELETE_BY_ID_SUCCESSFUL(8 , "Entity deleted for given id successfully."),
	DELETE_BY_ID_FAILED(8 , "Delete entity for given id was unsuccessful. Maybe the entity with given id doesn't exist.");
	
	
	private int id;
	private String desc;
	
	private Status(int id , String desc){
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
