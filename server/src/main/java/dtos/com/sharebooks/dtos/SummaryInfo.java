package com.sharebooks.dtos;

import org.json.simple.JSONObject;

import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.Quote;
import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.util.JsonUtility;

public class SummaryInfo extends DTO{
	private Book bookInfo;
	private UserInfo userInfo;
	private Quote quoteInfo;
	
	
	public SummaryInfo(){
		// nothing goes here
	}
	
	public SummaryInfo(Book book, User user, Quote quote){
		this.bookInfo = book;
		this.userInfo = new UserInfo(user);
		this.quoteInfo = quote;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		// TODO Auto-generated method stub
		jo.put("bookInfo", JsonUtility.getJSONObjFromObj(bookInfo));
		jo.put("userInfo", JsonUtility.getJSONObjFromObj(userInfo));
		jo.put("quoteInfo", JsonUtility.getJSONObjFromObj(quoteInfo));
	}

}
