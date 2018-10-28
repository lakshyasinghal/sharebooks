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
			String referenceNo = rs.getString("referenceNo");
			BookRequestType type = BookRequestType.valueOf(rs.getInt("type"));
			RequestStatus status = RequestStatus.valueOf(rs.getInt("status"));
			long bookId = rs.getLong("bookId");
			long bookOwnerId = rs.getLong("bookOwnerId");
			long requesterId = rs.getLong("requesterId");
			int requiredPeriod = rs.getInt("requiredPeriod");
			String comments = rs.getString("comments");
			
			BookRequest book = new BookRequest(id , referenceNo , type , status , bookId , bookOwnerId
					, requesterId , requiredPeriod , comments);
			return book;
		}
		catch(Exception ex){
			throw new FactoryException();
		}
	}

	@Override
	public BookRequest createFromJson(String json) throws Exception {
		try{
			BookRequest bookRequest = null;
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			JSONObject jo = (JSONObject)obj;
			bookRequest = BookRequest.deserializeFromJson(jo);
			return bookRequest;
		}
		catch(ParseException ex){
			throw ex;
		}
	}

}
