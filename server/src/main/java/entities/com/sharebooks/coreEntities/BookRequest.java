package com.sharebooks.coreEntities;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;
import org.json.simple.JSONObject;
import com.sharebooks.coreEntities.enums.*;
import com.sharebooks.dateTime.LocalDateTime;
import com.sharebooks.entity.CoreEntity;
import com.sharebooks.exception.JsonSerializationException;

public final class BookRequest extends CoreEntity {

	private BookRequestType type;
	private RequestStatus status;
	private String bookUid;
	private String bookOwnerUid;
	private String requesterUid;
	private int requiredPeriod;
	private String comments;
	
	public BookRequest(){
		//nothing
	}
	
	public BookRequest(int id , String uid , BookRequestType type , RequestStatus status , String bookUid , String bookOwnerUid
			, String requesterUid , int requiredPeriod , String comments, LocalDateTime creationTime, LocalDateTime lastModificationTime){
		super(id,uid,creationTime,lastModificationTime);
		this.type = type;
		this.status = status;
		this.bookUid = bookUid;
		this.bookOwnerUid = bookOwnerUid;
		this.requesterUid = requesterUid;
		this.requiredPeriod = requiredPeriod;
		this.comments = comments;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		try{
			super.serializeAsJson(jo);
			jo.put("type" , type.id());
			jo.put("status", status.id());
			jo.put("bookUid", bookUid);
			jo.put("bookOwnerUid", bookOwnerUid);
			jo.put("requesterUid", requesterUid);
			jo.put("requiredPeriod", requiredPeriod);
			jo.put("comments", comments);
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}
	
	
	public Map<String,Object> map(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		map.put("uid",uid);
		map.put("type",type.id());
		map.put("status",status.id());
		map.put("bookUid",bookUid);
		map.put("bookOwnerUid",bookOwnerUid);
		map.put("requesterUid",requesterUid);
		map.put("requiredPeriod",requiredPeriod);
		map.put("comments",comments);
		map.put("creationTime",creationTime.toString());
		map.put("lastModificationTime",lastModificationTime.toString());
		return map;
	}
	
	
	public String uid(){
		return uid;
	}
	
	public BookRequestType type(){
		return type;
	}
	
	public RequestStatus status(){
		return status;
	}
	
	public String bookUid(){
		return bookUid;
	}
	
	public String bookOwnerUid(){
		return bookOwnerUid;
	}
	
	public String requesterUid(){
		return requesterUid;
	}
	
	public int requiredPeriod(){
		return requiredPeriod;
	}
	
	public String comments(){
		return comments;
	}
}
