package com.sharebooks.coreEntities;


import java.util.*;
import org.json.simple.JSONObject;
import com.sharebooks.coreEntities.enums.AvailableStatus;
import com.sharebooks.dateTime.LocalDateTime;
import com.sharebooks.entity.CoreEntity;
import com.sharebooks.exception.*;
//import com.sharebooks.helperEntities.*;

public final class Book extends CoreEntity implements Comparable<Book> {
	
	private String title;
	private String author;
	private String category;
	private String subcategory;
	private int pages;
	private String ownerUid;
	private String imgSrc;
	private AvailableStatus available;
	private AvailableStatus buy;
	private AvailableStatus rent;
	private int buyAmount;
	private int rentAmount;
	
	
	public Book(){
		//nothing
	}

	
	//constructor
	public Book(int id, String uid, String title , String author , String category , String subcategory , int pages , String ownerUid , String imgSrc , AvailableStatus available , AvailableStatus buy , AvailableStatus rent , 
			int buyAmount , int rentAmount, LocalDateTime creationTime, LocalDateTime lastModificationTime){
		super(id, uid, creationTime , lastModificationTime);
		this.title = title;
		this.author = author;
		this.category = category;
		this.subcategory = subcategory;
		this.pages = pages;
		this.ownerUid = ownerUid;
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
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		try{
			super.serializeAsJson(jo);
			jo.put("title", title());
			jo.put("author", author());
			jo.put("category", category());
			jo.put("subcategory", subcategory());
			jo.put("pages", pages());
			jo.put("ownerUid" , ownerUid);
			jo.put("imgSrc", imgSrc);
			jo.put("available" , available.id());
			jo.put("buy", buy.id());
			jo.put("rent" , rent.id());
			jo.put("buyAmount" , buyAmount);
			jo.put("rentAmount" , rentAmount);
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
		map.put("ownerUid",ownerUid);
		map.put("imgSrc",imgSrc);
		map.put("available",available.id());
		map.put("buy",buy.id());
		map.put("rent",rent.id());
		map.put("buyAmount",buyAmount);
		map.put("rentAmount",rentAmount);
		map.put("creationTime",creationTime.toString());
		map.put("lastModificationTime",lastModificationTime.toString());
		
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
	
	public String ownerUid(){
		return ownerUid;
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
	
	public int buyAmount(){
		return buyAmount;
	}
	
	public int rentAmount(){
		return rentAmount;
	}
}
