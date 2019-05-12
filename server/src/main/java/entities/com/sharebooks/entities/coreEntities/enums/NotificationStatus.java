package com.sharebooks.entities.coreEntities.enums;


public enum NotificationStatus {
	NEW(1,"new"),OLD(2,"old");
	
	private int id;
	private String description;
	
	private NotificationStatus(int id,String description){
		this.id = id;
		this.description = description;
	}
	
	public static NotificationStatus valueOf(int id){
		NotificationStatus status = null;
		if(id==1){
			status = NotificationStatus.NEW;
		}
		else if(id==2){
			status = NotificationStatus.OLD;
		}
		
		return status;
	}
	
	public int id(){
		return id;
	}
	
	public String desc(){
		return description;
	}
}

