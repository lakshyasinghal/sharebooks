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
		String title = rs.getString("title");
		String authorName = rs.getString("authorName");
		String category = rs.getString("category");
		String subcategory = rs.getString("subcategory");
		int pages = rs.getInt("pages");
		long ownerId = rs.getLong("ownerId");
		String imgSrc = rs.getString("imgSrc");
		AvailableStatus available = AvailableStatus.valueOf(rs.getInt("available"));
		AvailableStatus forBuying = AvailableStatus.valueOf(rs.getInt("forBuying"));
		AvailableStatus forRent = AvailableStatus.valueOf(rs.getInt("forRent"));
		long buyingAmount = rs.getLong("buyingAmount");
		long rentAmount = rs.getLong("rentAmount");
		
		String creationTimeStr = (rs.getTimestamp("creationTime")).toString();
		LocalDateTime creationTime = LocalDateTime.buildFromString(creationTimeStr);
		
		String lastModificationTimeStr = (rs.getTimestamp("lastModificationTime")).toString();
		LocalDateTime lastModificationTime = LocalDateTime.buildFromString(lastModificationTimeStr);
		
		Book book = new Book(id , title , authorName , category , subcategory , pages , ownerId , imgSrc , available , 
				forBuying , forRent , buyingAmount , rentAmount , creationTime , lastModificationTime);
		return book;
	}

	@Override
	public Book createFromJson(String json) throws Exception {
		try{
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			JSONObject jo = (JSONObject)obj;
			long id = -1;
			try{
				id = (long)jo.get("id");
			}
			catch(NullPointerException ex){
				//do nothing
			}
			String title = (String)jo.get("title");
			String authorName = (String)jo.get("authorName");
			String category = (String)jo.get("category");
			String subcategory = (String)jo.get("subcategory");
			int pages = (int)(long)jo.get("pages");
			long ownerId = (long)jo.get("ownerId");
			String imgSrc = (String)jo.get("imgSrc");
			AvailableStatus available = AvailableStatus.valueOf((int)(long)jo.get("available"));
			AvailableStatus forBuying = AvailableStatus.valueOf((int)(long)jo.get("forBuying"));
			AvailableStatus forRent = AvailableStatus.valueOf((int)(long)jo.get("forRent"));
			long buyingAmount = (long)jo.get("buyingAmount");
			long rentAmount = (long)jo.get("rentAmount");
			
			Book book = new Book(id , title , authorName , category , subcategory , pages , ownerId , imgSrc , available ,
					forBuying , forRent , buyingAmount , rentAmount , null , null); //null values are for creationTime and lastModificationTime
			return book;
		}
		catch(ParseException ex){
			throw ex;
		}
	}

}
