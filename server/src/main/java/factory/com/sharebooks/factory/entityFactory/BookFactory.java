package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import com.sharebooks.coreEntities.Book;
import com.sharebooks.coreEntities.enums.AvailableStatus;
import com.sharebooks.dateTime.LocalDateTime;
import com.sharebooks.exception.FactoryException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class BookFactory implements EntityFactory<Book>{
	private static BookFactory bookFactory;
	
	private BookFactory(){
		//nothing goes here
	}
	
	public static BookFactory getInstance() throws Exception{
		try{
			if(bookFactory ==  null){
				synchronized(BookFactory.class){
					if(bookFactory ==  null){
						bookFactory = new BookFactory();
					}
				}
			}
			
			return bookFactory;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	
	@Override
	public Book createFromHttpRequest(HttpServletRequest req) throws Exception{
		
		return null;
	}

	@Override
	public Book createFromResultSet(ResultSet rs) throws FactoryException,Exception{
		
		long id = rs.getLong("id");
		String uid = rs.getString("uid");
		String title = rs.getString("title");
		String author = rs.getString("author");
		String category = rs.getString("category");
		String subcategory = rs.getString("subcategory");
		int pages = rs.getInt("pages");
		long ownerId = rs.getLong("ownerId");
		String imgSrc = rs.getString("imgSrc");
		AvailableStatus available = AvailableStatus.valueOf(rs.getInt("available"));
		AvailableStatus buy = AvailableStatus.valueOf(rs.getInt("buy"));
		AvailableStatus rent = AvailableStatus.valueOf(rs.getInt("rent"));
		long buyAmount = rs.getLong("buyAmount");
		long rentAmount = rs.getLong("rentAmount");
		String creationTimeStr = (rs.getTimestamp("creationTime")).toString();
		LocalDateTime creationTime = LocalDateTime.buildFromString(creationTimeStr);
		String lastModificationTimeStr = (rs.getTimestamp("lastModificationTime")).toString();
		LocalDateTime lastModificationTime = LocalDateTime.buildFromString(lastModificationTimeStr);
		
		Book book = new Book(id ,uid, title , author , category , subcategory , pages , ownerId , imgSrc , available , 
				buy , rent , buyAmount , rentAmount , creationTime , lastModificationTime);
		return book;
	}

	@Override
	public Book createFromJson(String json) throws Exception {
		try{
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			JSONObject jo = (JSONObject)obj;
			
			long id = (long)jo.get("id");
			String uid = (String)jo.get("uid");
			String title = (String)jo.get("title");
			String author = (String)jo.get("author");
			String category = (String)jo.get("category");
			String subcategory = (String)jo.get("subcategory");
			int pages = (int)(long)jo.get("pages");
			long ownerId = (long)jo.get("ownerId");
			String imgSrc = (String)jo.get("imgSrc");
			AvailableStatus available = AvailableStatus.valueOf((int)(long)jo.get("available"));
			AvailableStatus buy = AvailableStatus.valueOf((int)(long)jo.get("buy"));
			AvailableStatus rent = AvailableStatus.valueOf((int)(long)jo.get("rent"));
			long buyAmount = (long)jo.get("buyAmount");
			long rentAmount = (long)jo.get("rentAmount");
			String creationTimeStr = (String)jo.get("creationTime");
			String lastModificationTimeStr = (String)jo.get("lastModificationTime");
			
			LocalDateTime creationTime = LocalDateTime.buildFromString(creationTimeStr);
			LocalDateTime lastModificationTime = LocalDateTime.buildFromString(lastModificationTimeStr);
			
			Book book = new Book(id, uid, title, author, category, subcategory, pages, ownerId, imgSrc, available,
					buy, rent, buyAmount, rentAmount, creationTime, lastModificationTime); 
			return book;
		}
		catch(ParseException ex){
			throw ex;
		}
	}

}
