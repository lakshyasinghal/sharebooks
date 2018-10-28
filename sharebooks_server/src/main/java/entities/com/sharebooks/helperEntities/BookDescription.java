//package com.sharebooks.helperEntities;
//
//import org.json.simple.JSONObject;
//
//import com.sharebooks.exception.JsonSerializationException;
//import com.sharebooks.serialization.json.JsonSerializable;
//
//public class BookDescription implements JsonSerializable {
//	private String name;
//	private String authorName;
//	private String category;
//	private String subcategory;
//	private long pages;
//	
//	public BookDescription(){
//		//nothing
//	}
//	
//	public BookDescription(String name , String authorName , String category , String subcategory , long pages){
//		this.name = name;
//		this.authorName = authorName;
//		this.category = category;
//		this.subcategory = subcategory;
//		this.pages = pages;
//	}
//	
//	
//	@SuppressWarnings("unchecked")
//	@Override
//	public String serializeAsJson() throws JsonSerializationException {
//		try{
//			JSONObject jo = new JSONObject();
//			jo.put("name" , name);
//			jo.put("authorName" , authorName);
//			jo.put("category" , category);
//			jo.put("subcategory" , subcategory);
//			jo.put("pages" , pages);
//			
//			return jo.toString();
//		}
//		catch(Exception ex){
//			throw new JsonSerializationException(ex.getMessage());
//		}
//	}
//
//	@Override
//	public void deserializeFromJson(JSONObject jo) {
//		try{
//			name = (String)jo.get("name");
//			authorName = (String)jo.get("authorName");
//			category = (String)jo.get("category");
//			subcategory = (String)jo.get("subcategory");
//			pages = (long)jo.get("pages");
//		}
//		catch(ClassCastException ex){
//			System.out.println(ex.getMessage());
//		}
//		catch(NullPointerException ex){
//			System.out.println(ex.getMessage());
//		}
//		catch(Exception ex){
//			System.out.println(ex.getMessage());
//		}
//	}
//	
//	
//	public String name(){
//		return name;
//	}
//	
//	public String authorName(){
//		return authorName;
//	}
//	
//	public String category(){
//		return category;
//	}
//	
//	public String subcategory(){
//		return subcategory;
//	}
//	
//	public long pages(){
//		return pages;
//	}
//}
