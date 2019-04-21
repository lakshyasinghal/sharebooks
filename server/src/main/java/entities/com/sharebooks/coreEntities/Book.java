package com.sharebooks.coreEntities;


import java.util.*;
import org.json.simple.JSONObject;
import com.sharebooks.coreEntities.enums.AvailableStatus;
import com.sharebooks.dateTime.LocalDateTime;
import com.sharebooks.entity.Entity;
import com.sharebooks.exception.*;
//import com.sharebooks.helperEntities.*;

public final class Book extends Entity implements Comparable<Book> {
	
	private String uid;
	private String title;
	private String author;
	private String category;
	private String subcategory;
	private int pages;
	private long ownerId;
	private String imgSrc;
	private AvailableStatus available;
	private AvailableStatus buy;
	private AvailableStatus rent;
	private long buyAmount;
	private long rentAmount;
	
	
	public Book(){
		//nothing
	}

	
	//constructor
	public Book(long id, String uid, String title , String author , String category , String subcategory , int pages , long ownerId , String imgSrc , AvailableStatus available , AvailableStatus buy , AvailableStatus rent , 
			long buyAmount , long rentAmount, LocalDateTime creationTime, LocalDateTime lastModificationTime){
		super(id , creationTime , lastModificationTime);
		if(uid==null){
			this.uid = UUID.randomUUID().toString();
		}
		else{
			this.uid = uid;
		}
		this.title = title;
		this.author = author;
		this.category = category;
		this.subcategory = subcategory;
		this.pages = pages;
		this.ownerId = ownerId;
		//this.description = description;
		this.imgSrc = imgSrc;
		this.available = available;
		this.buy = buy;
		this.rent = rent;
		this.buyAmount = buyAmount;
		this.rentAmount = rentAmount;
	}
	
	
	@Override
	public int compareTo(Book book) {
		return title.compareTo(book.title());
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String serializeAsJson() throws JsonSerializationException {
		try{
			JSONObject jo = new JSONObject();
			jo.put("id", id());
			jo.put("uid", uid());
			jo.put("title", title());
			jo.put("author", author());
			jo.put("category", category());
			jo.put("subcategory", subcategory());
			jo.put("pages", pages());
			jo.put("ownerId" , ownerId);
			//jo.put("description", description.serializeAsJson());
			jo.put("imgSrc", imgSrc);
			jo.put("available" , available.id());
			jo.put("buy", buy.id());
			jo.put("rent" , rent.id());
			jo.put("buyAmount" , buyAmount);
			jo.put("rentAmount" , rentAmount);
			jo.put("creationTime", creationTime.toString());
			jo.put("lastModificationTime", lastModificationTime.toString());
			return jo.toString();
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}
	
	
	//toString method
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("id:" + id + "\n");
		builder.append("title:" + title + "\n");
		return builder.toString();
	}
	
	
	//will return a map representation of the book object
	//will be mostly used when inserting new book or updating existing book into database
	public Map<String,Object> map(){
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		map.put("uid",uid);
		map.put("title",title);
		map.put("author",author);
		map.put("category",category);
		map.put("subcategory",subcategory);
		map.put("pages",pages);
		map.put("ownerId",ownerId);
		map.put("imgSrc",imgSrc);
		map.put("available",available.id());
		map.put("buy",buy);
		map.put("rent",rent);
		map.put("buyAmount",buyAmount);
		map.put("rentAmount",rentAmount);
		map.put("creationTime",creationTime);
		map.put("lastModificationTime",lastModificationTime);
		
		return map;
	}
	
	
	
	//getter methods
	
	public String uid(){
		return uid;
	}
	
	public String title(){
		return title;
	}
	
	public String author(){
		return author;
	}
	
	public String category(){
		return category;
	}
	
	public String subcategory(){
		return subcategory;
	}
	
	public int pages(){
		return pages;
	}
	
	public long ownerId(){
		return ownerId;
	}
	
	public String imgSrc(){
		return imgSrc;
	}
	
	public AvailableStatus available(){
		return available;
	}
	
	public AvailableStatus buy(){
		return buy;
	}
	
	public AvailableStatus rent(){
		return rent;
	}
	
	public long buyAmount(){
		return buyAmount;
	}
	
	public long rentAmount(){
		return rentAmount;
	}
}
