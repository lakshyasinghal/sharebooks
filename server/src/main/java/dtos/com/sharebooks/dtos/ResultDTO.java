package com.sharebooks.dtos;

import org.json.simple.JSONObject;

import com.sharebooks.coreEntities.Book;
import com.sharebooks.coreEntities.User;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.util.JsonUtility;

public class ResultDTO extends DTO{
	private ResultUser user;
	private Book book;
	
	public ResultDTO(){
		
	}
	
	public ResultDTO(User user, Book book){
		this.user = new ResultUser(user);
		this.book = book;
	}


	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		jo.put("user", JsonUtility.getJSONObjFromObj(user));
		jo.put("book", JsonUtility.getJSONObjFromObj(book));
	}
	
	
}
