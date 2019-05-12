package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sharebooks.entities.coreEntities.BookRequest;
import com.sharebooks.entities.coreEntities.enums.BookRequestType;
import com.sharebooks.entities.coreEntities.enums.RequestStatus;
import com.sharebooks.exception.FactoryException;

public class BookRequestFactory implements EntityFactory<BookRequest>{
	private static BookRequestFactory bookRequestFactory;
	
	private BookRequestFactory(){
		//nothing goes here
	}
	
	
	public static BookRequestFactory getInstance() throws Exception{
		try{
			if(bookRequestFactory ==  null){
				synchronized(BookRequestFactory.class){
					if(bookRequestFactory ==  null){
						bookRequestFactory = new BookRequestFactory();
					}
				}
			}
			
			return bookRequestFactory;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	

	@Override
	public BookRequest createFromHttpRequest(HttpServletRequest req) throws Exception{
		
		return null;
	}

	@Override
	public BookRequest createFromResultSet(ResultSet rs) throws Exception{
		try{
			int id = rs.getInt("id");
			String uid = rs.getString("uid");
			BookRequestType type = BookRequestType.valueOf(rs.getInt("type"));
			RequestStatus status = RequestStatus.valueOf(rs.getInt("status"));
			String bookUid = rs.getString("bookUid");
			String bookOwnerUid = rs.getString("bookOwnerUid");
			String requesterUid = rs.getString("requesterUid");
			String quoteUid = rs.getString("quoteUid");
			String comments = rs.getString("comments");
			
			BookRequest book = new BookRequest(id , uid , type , status , bookUid , bookOwnerUid
					, requesterUid , quoteUid , comments, null, null);
			return book;
		}
		catch(Exception ex){
			throw new FactoryException();
		}
	}

	@Override
	public BookRequest createFromJson(String json) throws Exception {
		try{
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			JSONObject jo = (JSONObject)obj;
			
			int id = jo.get("id")==null?-1:(int)(long)jo.get("id");
			String uid = (String)jo.get("uid");
			BookRequestType type = BookRequestType.valueOf((int)(long)jo.get("type"));
			RequestStatus status = RequestStatus.valueOf((int)(long)jo.get("status"));
			String bookUid = (String)jo.get("bookUid");
			String bookOwnerUid = (String)jo.get("bookOwnerUid");
			String requesterUid = (String)jo.get("requesterUid");
			String quoteUid = (String)jo.get("quoteUid");
			String comments = (String)jo.get("comments");
			BookRequest bookRequest = new BookRequest(id,uid,type,status,bookUid,bookOwnerUid,requesterUid,quoteUid,comments,null,null);
			return bookRequest;
		}
		catch(ParseException ex){
			throw ex;
		}
	}


	@Override
	public List<BookRequest> getListFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
