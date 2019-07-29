package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.enums.AvailableStatus;
import com.sharebooks.exception.FactoryException;
import com.sharebooks.util.dateTime.LocalDateTime;

public class BookFactory implements EntityFactory<Book> {
	private static BookFactory bookFactory;

	private BookFactory() {
		// nothing goes here
	}

	public static BookFactory getInstance() throws Exception {
		try {
			if (bookFactory == null) {
				synchronized (BookFactory.class) {
					if (bookFactory == null) {
						bookFactory = new BookFactory();
					}
				}
			}

			return bookFactory;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Book createFromHttpRequest(HttpServletRequest req) throws Exception {

		return null;
	}

	@Override
	public Book createFromResultSet(ResultSet rs) throws FactoryException, Exception {

		int id = rs.getInt("id");
		String uid = rs.getString("uid");
		String title = rs.getString("title");
		String author = rs.getString("author");
		String category = rs.getString("category");
		String subcategory = rs.getString("subcategory");
		int pages = rs.getInt("pages");
		String ownerUid = rs.getString("ownerUid");
		String imgSrc = rs.getString("imgSrc");
		AvailableStatus available = AvailableStatus.valueOf(rs.getInt("available"));
		AvailableStatus buy = AvailableStatus.valueOf(rs.getInt("buy"));
		AvailableStatus rent = AvailableStatus.valueOf(rs.getInt("rent"));
		int buyAmount = rs.getInt("buyAmount");
		int rentAmount = rs.getInt("rentAmount");
		String creationTimeStr = (rs.getTimestamp("creationTime")).toString();
		LocalDateTime creationTime = LocalDateTime.buildFromString(creationTimeStr);
		String lastModificationTimeStr = (rs.getTimestamp("lastModificationTime")).toString();
		LocalDateTime lastModificationTime = LocalDateTime.buildFromString(lastModificationTimeStr);

		Book book = new Book(id, uid, title, author, category, subcategory, pages, ownerUid, imgSrc, available, buy,
				rent, buyAmount, rentAmount, creationTime, lastModificationTime);
		return book;
	}

	@Override
	public Book createFromJson(String json) throws Exception {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			JSONObject jo = (JSONObject) obj;

			int id = jo.get("id") == null ? -1 : (int) (long) jo.get("id");
			String uid = (String) jo.get("uid");
			String title = (String) jo.get("title");
			String author = (String) jo.get("author");
			String category = (String) jo.get("category");
			String subcategory = jo.get("subcategory") == null ? "" : (String) jo.get("subcategory");
			int pages = jo.get("pages") == null ? -1 : (int) (long) jo.get("pages");
			String ownerUid = (String) jo.get("ownerUid");
			String imgSrc = jo.get("imgSrc") == null ? "" : (String) jo.get("imgSrc");
			AvailableStatus available = AvailableStatus.valueOf((int) (long) jo.get("available"));
			AvailableStatus buy = AvailableStatus.valueOf((int) (long) jo.get("buy"));
			AvailableStatus rent = AvailableStatus.valueOf((int) (long) jo.get("rent"));
			int buyAmount = (int) (long) jo.get("buyAmount");
			int rentAmount = (int) (long) jo.get("rentAmount");
			String creationTimeStr = (String) jo.get("creationTime");
			String lastModificationTimeStr = (String) jo.get("lastModificationTime");

			LocalDateTime creationTime = creationTimeStr == null ? null
					: LocalDateTime.buildFromString(creationTimeStr);
			LocalDateTime lastModificationTime = lastModificationTimeStr == null ? null
					: LocalDateTime.buildFromString(lastModificationTimeStr);

			Book book = new Book(id, uid, title, author, category, subcategory, pages, ownerUid, imgSrc, available, buy,
					rent, buyAmount, rentAmount, creationTime, lastModificationTime);
			return book;
		} catch (ParseException ex) {
			throw ex;
		}
	}

	@Override
	public List<Book> getListFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book createFromMongoDocument(Document doc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
