package com.sharebooks.entity;

import java.util.UUID;

import org.json.simple.JSONObject;

import com.sharebooks.dateTime.LocalDateTime;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;
import com.sharebooks.util.JsonUtility;

public abstract class CoreEntity extends Entity{
	
	protected String uid;
	protected LocalDateTime creationTime;
	protected LocalDateTime lastModificationTime;
	
	protected CoreEntity(){
		uid = UUID.randomUUID().toString();
		creationTime = new LocalDateTime();
		lastModificationTime = new LocalDateTime();
	}
	
	protected CoreEntity(int id){
		super(id);
		uid = UUID.randomUUID().toString();
		creationTime = new LocalDateTime();
		lastModificationTime = new LocalDateTime();
	}
	
	protected CoreEntity(int id, String uid , LocalDateTime creationTime , LocalDateTime lastModificationTime){
		super(id);
		if(uid==null){
			this.uid = UUID.randomUUID().toString();
		}
		else{
			this.uid = uid;
		}
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
	
	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException{
		super.serializeAsJson(jo);
		jo.put("uid", uid);
		jo.put("creationTime", JsonUtility.getJSONObjFromObj(creationTime));
		jo.put("lastModificationTime", JsonUtility.getJSONObjFromObj(lastModificationTime));
	}
	
	public String uid(){
		return uid;
	}
	
	public LocalDateTime creationTime(){
		return creationTime;
	}
	
	public LocalDateTime lastModificationTime(){
		return lastModificationTime;
	}
}
