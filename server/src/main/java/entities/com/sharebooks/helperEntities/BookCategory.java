package com.sharebooks.helperEntities;

import org.json.simple.JSONObject;
import com.sharebooks.entity.HelperEntity;
import com.sharebooks.exception.JsonSerializationException;

public final class BookCategory extends HelperEntity implements Comparable<BookCategory>{

	private String category;
	
	public BookCategory(){
		
	}
	
	public BookCategory(int id, String category){
		super(id);
		this.category = category;
	}
	
	@Override
	public int compareTo(BookCategory bookCategory) {
		return category.compareTo(bookCategory.category);
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String serializeAsJson() throws JsonSerializationException {
		try{
			JSONObject jo = new JSONObject();
			jo.put("id", id);
			jo.put("category", category);
			return jo.toString();
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}

	public String category(){
		return category;
	}
}
