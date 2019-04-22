package com.sharebooks.entity;

import com.sharebooks.dateTime.LocalDateTime;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;

public abstract class CoreEntity extends Entity{
	
	protected LocalDateTime creationTime;
	protected LocalDateTime lastModificationTime;
	
	protected CoreEntity(){
		creationTime = new LocalDateTime();
		lastModificationTime = new LocalDateTime();
	}
	
	protected CoreEntity(int id){
		super(id);
		creationTime = new LocalDateTime();
		lastModificationTime = new LocalDateTime();
	}
	
	protected CoreEntity(int id , LocalDateTime creationTime , LocalDateTime lastModificationTime){
		super(id);
		if(creationTime == null){
			this.creationTime = new LocalDateTime();
		}
		else{
			this.creationTime = creationTime;
		}
		if(lastModificationTime == null){
			this.lastModificationTime = new LocalDateTime();
		}
		else{
			this.lastModificationTime = lastModificationTime;
		}
	}
	
	public abstract String serializeAsJson() throws JsonSerializationException;
	
}
