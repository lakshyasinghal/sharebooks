package com.sharebooks.coreEntities;

import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONObject;

import com.sharebooks.coreEntities.enums.QuoteStatus;
import com.sharebooks.dateTime.LocalDateTime;
import com.sharebooks.entity.CoreEntity;
import com.sharebooks.exception.JsonSerializationException;

public class Quote extends CoreEntity{
	
	private String bookUid;
	private String userUid;
	private QuoteStatus status;
	
	public Quote(){}
	
	public Quote(int id, String uid, String bookUid, String userUid, QuoteStatus status, LocalDateTime creationTime, LocalDateTime lastModificationTime){
		super(id,uid,creationTime,lastModificationTime);
		this.bookUid = bookUid;
		this.userUid = userUid;
		if(status==null){this.status = QuoteStatus.INITIAL;}
		else{this.status = status;}
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		try{
			super.serializeAsJson(jo);
			jo.put("bookUid", bookUid);
			jo.put("userUid", userUid);
			jo.put("status", status.id());
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}
	
	
	public Map<String,Object> map(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		map.put("uid",uid);
		map.put("bookUid",bookUid);
		map.put("userUid",userUid);
		map.put("status", status.id());
		map.put("creationTime",creationTime.toString());
		map.put("lastModificationTime",lastModificationTime.toString());
		return map;
	}
	
	
	public String bookUid(){
		return bookUid;
	}
	
	public String userUid(){
		return userUid;
	}
}
