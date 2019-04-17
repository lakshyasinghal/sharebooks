package com.sharebooks.entity;

import com.sharebooks.dateTime.LocalDateTime;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;

public abstract class Entity implements JsonSerializable{
	
	protected long id;
	protected LocalDateTime creationTime;
	protected LocalDateTime lastModificationTime;
	
	protected Entity(){
		id = -1;
		creationTime = new LocalDateTime();
		lastModificationTime = new LocalDateTime();
	}
	
	protected Entity(long id){
		this.id = id;
		creationTime = new LocalDateTime();
		lastModificationTime = new LocalDateTime();
	}
	
	protected Entity(long id , LocalDateTime creationTime , LocalDateTime lastModificationTime){
		this.id = id;
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
	
	public long id(){
		return id;
	}
}
