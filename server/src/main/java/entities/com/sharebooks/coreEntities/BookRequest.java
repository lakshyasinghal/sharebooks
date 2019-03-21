package com.sharebooks.coreEntities;

import org.json.simple.JSONObject;
import com.sharebooks.coreEntities.enums.*;
import com.sharebooks.entity.Entity;
import com.sharebooks.exception.JsonSerializationException;

public final class BookRequest extends Entity {

	private String referenceNo;
	private BookRequestType type;
	private RequestStatus status;
	private long bookId;
	private long bookOwnerId;
	private long requesterId;
	private int requiredPeriod;
	private String comments;
	
	public BookRequest(){
		//nothing
	}
	
	public BookRequest(long id , String referenceNo , BookRequestType type , RequestStatus status , long bookId , long bookOwnerId
			, long requesterId , int requiredPeriod , String comments){
		super(id);
		this.referenceNo = referenceNo;
		this.type = type;
		this.status = status;
		this.bookId = bookId;
		this.bookOwnerId = bookOwnerId;
		this.requesterId = requesterId;
		this.requiredPeriod = requiredPeriod;
		this.comments = comments;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String serializeAsJson() throws JsonSerializationException {
		try{
			JSONObject jo  = new JSONObject();
			jo.put("id", id);
			jo.put("referenceNo", referenceNo);
			jo.put("type" , type.id());
			jo.put("status", status.id());
			jo.put("bookId", bookId);
			jo.put("bookOwnerId", bookOwnerId);
			jo.put("requesterId", requesterId);
			jo.put("requiredPeriod", requiredPeriod);
			jo.put("comments", comments);
			
			return jo.toString();
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}

	
	public static BookRequest deserializeFromJson(JSONObject jo) throws Exception{
		try{
			BookRequest bookRequest = new BookRequest();
			bookRequest.id = (long)jo.get("id");
			bookRequest.referenceNo = (String)jo.get("referenceNo");
			bookRequest.type = BookRequestType.valueOf((int)(long)jo.get("type"));
			bookRequest.status = RequestStatus.valueOf((int)(long)jo.get("status"));
			bookRequest.bookId = (long)jo.get("bookId");
			bookRequest.bookOwnerId = (long)jo.get("bookOwnerId");
			bookRequest.requesterId = (long)jo.get("requesterId");
			bookRequest.requiredPeriod = (int)(long)jo.get("requiredPeriod");
			bookRequest.comments = (String)jo.get("comments");
			
			return bookRequest;
		}
		catch(ClassCastException ex){
			System.out.println(ex.getMessage());
			throw ex;
		}
		catch(NullPointerException ex){
			System.out.println(ex.getMessage());
			throw ex;
		}
		catch(Exception ex){
			System.out.println(ex.getMessage());
			throw ex;
		}
	}
	
	public String referenceNo(){
		return referenceNo;
	}
	
	public BookRequestType type(){
		return type;
	}
	
	public RequestStatus status(){
		return status;
	}
	
	public long bookId(){
		return bookId;
	}
	
	public long bookOwnerId(){
		return bookOwnerId;
	}
	
	public long requesterId(){
		return requesterId;
	}
	
	public int requiredPeriod(){
		return requiredPeriod;
	}
	
	public String comments(){
		return comments;
	}
}
