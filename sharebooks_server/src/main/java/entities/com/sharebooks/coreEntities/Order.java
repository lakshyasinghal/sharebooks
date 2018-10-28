package com.sharebooks.coreEntities;

import org.json.simple.JSONObject;
import com.sharebooks.coreEntities.enums.*;
import com.sharebooks.entity.Entity;
import com.sharebooks.exception.JsonSerializationException;

public final class Order extends Entity {
	
	private String referenceNo;
	private String requestRefNo;
	private OrderType type;
	private OrderStatus status;
	private long buyerId;
	private long sellerId;
	private double amount;
	
	public Order(){
		//nothing
	}
	
	public Order(long id , String referenceNo , String requestRefNo , OrderType type , OrderStatus status , long buyerId , long sellerId
			, double amount){
		super(id);
		this.referenceNo = referenceNo;
		this.requestRefNo = requestRefNo;
		this.type = type;
		this.status = status;
		this.buyerId = buyerId;
		this.sellerId = sellerId;
		this.amount = amount;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String serializeAsJson() throws JsonSerializationException {
		try{
			JSONObject jo = new JSONObject();
			jo.put("id", id);
			jo.put("referenceNo" , referenceNo);
			jo.put("requestRefNo", requestRefNo);
			jo.put("type" , type.id());
			jo.put("status" , status.id());
			jo.put("buyerId", buyerId);
			jo.put("sellerId", sellerId);
			jo.put("amount" , amount);
			
			return jo.toString();
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}
	
	
	public static Order deserializeFromJson(JSONObject jo) {
		Order order = new Order();
		order.id = (long)jo.get("id");
		order.referenceNo = (String)jo.get("referenceNo");
		order.requestRefNo = (String)jo.get("requestRefNo");
		order.type = OrderType.valueOf((int)(long)jo.get("referenceNo"));
		order.status = OrderStatus.valueOf((int)(long)jo.get("status"));
		order.buyerId = (long)jo.get("buyerId");
		order.sellerId = (long)jo.get("sellerId");
		order.amount = (double)(long)jo.get("amount");
		
		return order;
	}
	
	
	public String referenceNo(){
		return referenceNo;
	}
	
	public String requestRefNo(){
		return requestRefNo;
	}
	
	public OrderType type(){
		return type;
	}
	
	public OrderStatus status(){
		return status;
	}
	
	public long buyerId(){
		return buyerId;
	}
	
	public long sellerId(){
		return sellerId;
	}
	
	public double amount(){
		return amount;
	}
}
