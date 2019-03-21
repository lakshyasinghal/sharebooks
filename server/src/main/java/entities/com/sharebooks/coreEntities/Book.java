package com.sharebooks.coreEntities;


import java.util.*;
import org.json.simple.JSONObject;
import com.sharebooks.coreEntities.enums.AvailableStatus;
import com.sharebooks.dateTime.LocalDateTime;
import com.sharebooks.entity.Entity;
import com.sharebooks.exception.*;
//import com.sharebooks.helperEntities.*;

public final class Book extends Entity {
	
	private String title;
	private String authorName;
	private String category;
	private String subcategory;
	private int pages;
	private long ownerId;
	private String imgSrc;
	private AvailableStatus available;
	private AvailableStatus forBuying;
	private AvailableStatus forRent;
	private long buyingAmount;
	private long rentAmount;
	
	
	public Book(){
		//nothing
	}
	
	//constructor
	public Book(long id , String title , String authorName , String category , String subcategory , int pages , long ownerId , String imgSrc , AvailableStatus available , AvailableStatus forBuying , AvailableStatus forRent , 
			long buyingAmount , long rentAmount, LocalDateTime creationTime, LocalDateTime lastModificationTime){
		super(id , creationTime , lastModificationTime);
		this.title = title;
		this.authorName = authorName;
		this.category = category;
		this.subcategory = subcategory;
		this.pages = pages;
		this.ownerId = ownerId;
		//this.description = description;
		this.imgSrc = imgSrc;
		this.available = available;
		this.forBuying = forBuying;
		this.forRent = forRent;
		this.buyingAmount = buyingAmount;
		this.rentAmount = rentAmount;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String serializeAsJson() throws JsonSerializationException {
		try{
			JSONObject jo = new JSONObject();
			jo.put("id", id());
			jo.put("title", title());
			jo.put("authorName", authorName());
			jo.put("category", category());
			jo.put("subcategory", subcategory());
			jo.put("pages", pages());
			jo.put("ownerId" , ownerId);
			//jo.put("description", description.serializeAsJson());
			jo.put("imgSrc", imgSrc);
			jo.put("available" , available.id());
			jo.put("forBuying", forBuying.id());
			jo.put("forRent" , forRent.id());
			jo.put("buyingAmount" , buyingAmount);
			jo.put("rentAmount" , rentAmount);
			jo.put("creationTime", creationTime.toString());
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
		builder.append("authorName:" + authorName + "\n");
		builder.append("category:" + category + "\n");
		builder.append("subcategory:" + subcategory + "\n");
		builder.append("pages:" + pages + "\n");
		builder.append("ownerId:" + ownerId + "\n");
		builder.append("imgSrc:" + imgSrc + "\n");
		builder.append("available:" + available.description() + "\n");
		builder.append("forBuying:" + forBuying.description() + "\n");
		builder.append("forRent:" + forRent.description() + "\n");
		builder.append("buyingAmount:" + buyingAmount + "\n");
		builder.append("rentAmount:" + rentAmount + "\n");
		builder.append("creationTime:" + creationTime.toString() + "\n");
		builder.append("lastModificationTime:" + lastModificationTime.toString() + "\n");
		return builder.toString();
	}
	
	
	//will return a list of all the book property names
	//will be mostly used when inserting new book into database
	public List<String> fields(){
		List<String> fields = new LinkedList<String>();
		fields.add("id");
		fields.add("title");
		fields.add("authorName");
		fields.add("category");
		fields.add("subcategory");
		fields.add("pages");
		fields.add("ownerId");
		fields.add("imgSrc");
		fields.add("available");
		fields.add("forBuying");
		fields.add("forRent");
		fields.add("buyingAmount");
		fields.add("rentAmount");
		fields.add("creationTime");
		fields.add("lastModificationTime");
		
		return fields;
	}
	
	//will return a list of all the book property values
	//will be mostly used when inserting new book into database
	public List<Object> values(){
		List<Object> values = new LinkedList<Object>();
		values.add(id);
		values.add(title);
		values.add(authorName);
		values.add(category);
		values.add(subcategory);
		values.add(pages);
		values.add(ownerId);
		values.add(imgSrc);
		values.add(available.id());
		values.add(forBuying.id());
		values.add(forRent.id());
		values.add(buyingAmount);
		values.add(rentAmount);
		values.add(creationTime.toString());
		values.add(lastModificationTime.toString());
		
		return values;
	}
	
	
	
	//getter methods
	
	public String title(){
		return title;
	}
	
	public String authorName(){
		return authorName;
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
	
	public AvailableStatus forBuying(){
		return forBuying;
	}
	
	public AvailableStatus forRent(){
		return forRent;
	}
	
	public long buyingAmount(){
		return buyingAmount;
	}
	
	public long rentAmount(){
		return rentAmount;
	}
}
