package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.sharebooks.coreEntities.BookRequest;
import com.sharebooks.coreEntities.enums.BookRequestType;
import com.sharebooks.coreEntities.enums.RequestStatus;
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
			long id = rs.getLong("id");
			String uid = rs.getString("uid");
			BookRequestType type = BookRequestType.valueOf(rs.getInt("type"));
			RequestStatus status = RequestStatus.valueOf(rs.getInt("status"));
			String bookUid = rs.getString("bookUid");
			String bookOwnerUid = rs.getString("bookOwnerUid");
			String requesterUid = rs.getString("requesterUid");
			int requiredPeriod = rs.getInt("requiredPeriod");
			String comments = rs.getString("comments");
			
			BookRequest book = new BookRequest(id , uid , type , status , bookUid , bookOwnerUid
					, requesterUid , requiredPeriod , comments, null, null);
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
			
			long id = (long)jo.get("id");
			String uid = (String)jo.get("uid");
			BookRequestType type = BookRequestType.valueOf((int)(long)jo.get("type"));
			RequestStatus status = RequestStatus.valueOf((int)(long)jo.get("status"));
			String bookUid = (String)jo.get("bookUid");
			String bookOwnerUid = (String)jo.get("bookOwnerUid");
			String requesterUid = (String)jo.get("requesterUid");
			int requiredPeriod = (int)(long)jo.get("requiredPeriod");
			String comments = (String)jo.get("comments");
			BookRequest bookRequest = new BookRequest(id,uid,type,status,bookUid,bookOwnerUid,requesterUid,requiredPeriod,comments,null,null);
			return bookRequest;
		}
		catch(ParseException ex){
			throw ex;
		}
	}

}
