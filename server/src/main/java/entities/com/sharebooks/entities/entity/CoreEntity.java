package com.sharebooks.entities.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONObject;

import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.exception.JsonDeserializationException;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonDeserializable;
import com.sharebooks.serialization.json.JsonSerializable;
import com.sharebooks.util.JsonUtility;
import com.sharebooks.util.dateTime.LocalDateTime;

public abstract class CoreEntity extends Entity {
	
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
	
	public boolean equals(Object ob){
		if(ob==null){
			return false;
		}
		CoreEntity coreEnt = (CoreEntity)ob;
		return uid.equals(coreEnt.uid);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException{
		super.serializeAsJson(jo);
		jo.put("uid", uid);
		jo.put("creationTime", JsonUtility.getJSONObjFromObj(creationTime));
		jo.put("lastModificationTime", JsonUtility.getJSONObjFromObj(lastModificationTime));
	}
	
	public void deserializeFromJson(JSONObject jo) throws JsonDeserializationException,Exception{
		
	}
	
	//will return a map representation of the core entity object
	//will be mostly used when inserting new entity or updating existing entity into database
	public Map<String,Object> map() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		Map<String,Object> superMap = super.map();
		map.putAll(superMap);
		
		map.put("uid",uid);
		map.put("creationTime",creationTime.toString());
		map.put("lastModificationTime",lastModificationTime.toString());
		return map;
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
