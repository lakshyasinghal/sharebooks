package com.sharebooks.entities.coreEntities;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.json.simple.JSONObject;

import com.sharebooks.entities.coreEntities.enums.NotificationStatus;
import com.sharebooks.entities.coreEntities.enums.NotificationType;
import com.sharebooks.entities.entity.CoreEntity;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonDeserializable;
import com.sharebooks.serialization.json.JsonSerializable;
import com.sharebooks.util.dateTime.LocalDateTime;

public class Notification extends CoreEntity implements JsonSerializable,JsonDeserializable{

	private String receiverUid;
	private NotificationType type;
	private String bookRequestUid;
	private String newBookUid;
	private NotificationStatus status;
	
	
	public Notification(int id, String uid, String receiverUid, NotificationType type, String bookRequestUid, String newBookUid, 
			NotificationStatus status, LocalDateTime creationTime, LocalDateTime lastModificationTime){
		super(id,uid,creationTime,lastModificationTime);
		this.receiverUid = receiverUid;
		this.type = type;
		this.bookRequestUid = bookRequestUid;
		this.newBookUid = newBookUid;
		if(status==null){
			this.status = NotificationStatus.NEW;
		}
		else{
			this.status = status;
		}
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		super.serializeAsJson(jo);
		jo.put("receiverUid", receiverUid());
		jo.put("type", type().id());
		jo.put("bookRequestUid", bookRequestUid());
		jo.put("newBookUid", newBookUid());
		jo.put("status", status.id());
	}
	
	
	//toString method
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("id:" + id + "\n");
		builder.append("uid:" + uid + "\n");
		builder.append("receiverUid:" + receiverUid);
		return builder.toString();
	}
	
	
	//will return a map representation of the notification object
	public Map<String,Object> map() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		//putting super object fields into map
		Map<String,Object> superMap = super.map();
		map.putAll(superMap);
		
		map.put("receiverUid", receiverUid());
		map.put("type", type().id());
		map.put("bookRequestUid", bookRequestUid());
		map.put("newBookUid", newBookUid());
		map.put("status", status.id());
		return map;
	}


	
	
	public String uid(){
		return uid;
	}
	
	public String receiverUid(){
		return receiverUid;
	}
	
	public NotificationType type(){
		return type;
	}
	
	public String bookRequestUid(){
		return bookRequestUid;
	}
	
	public String newBookUid(){
		return newBookUid;
	}
	
	public NotificationStatus status(){
		return status;
	}
}
