package com.sharebooks.entities.coreEntities;

import java.util.HashMap;
import java.util.Map;
import org.json.simple.JSONObject;

import com.sharebooks.entities.coreEntities.enums.*;
import com.sharebooks.entities.entity.CoreEntity;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonDeserializable;
import com.sharebooks.serialization.json.JsonSerializable;
import com.sharebooks.util.JsonUtility;
import com.sharebooks.util.dateTime.LocalDateTime;

public final class BookRequest extends CoreEntity implements JsonSerializable , JsonDeserializable{

	private BookRequestType type;
	private RequestStatus status;
	private String bookUid;
	private String bookOwnerUid;
	private String requesterUid;
	private String quoteUid;
	private String comments;      //the comments might be required from the book owner's side when he accepts or rejects the request 
	
	public BookRequest(){
		//nothing
	}
	
	public BookRequest(int id , String uid , BookRequestType type , RequestStatus status , String bookUid , String bookOwnerUid
			, String requesterUid , String quoteUid , String comments, LocalDateTime creationTime, LocalDateTime lastModificationTime){
		super(id,uid,creationTime,lastModificationTime);
		this.type = type;
		if(status==null){
			this.status = RequestStatus.PENDING;
		}
		else{
			this.status = status;
		}
		this.bookUid = bookUid;
		this.bookOwnerUid = bookOwnerUid;
		this.requesterUid = requesterUid;
		this.quoteUid = quoteUid;
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
			jo.put("quoteUid", quoteUid);
			jo.put("comments", comments);
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}
	
	
	public Map<String,Object> map() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//putting super object fields into map
		Map<String,Object> superMap = super.map();
		map.putAll(superMap);
		
		map.put("type",type.id());
		map.put("status",status.id());
		map.put("bookUid",bookUid);
		map.put("bookOwnerUid",bookOwnerUid);
		map.put("requesterUid",requesterUid);
		map.put("quoteUid",quoteUid);
		map.put("comments",comments);
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
	
	public String quoteUid(){
		return quoteUid;
	}
	
	public String comments(){
		return comments;
	}
}
