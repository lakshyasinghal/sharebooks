package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sharebooks.entities.helperEntities.BookCategory;
import com.sharebooks.exception.NonFunctionalMethodException;
import com.sharebooks.util.JsonUtility;

public class BookCategoryFactory implements EntityFactory<BookCategory> {
	private static BookCategoryFactory bookCategoryFactory;

	private BookCategoryFactory() {
		// nothing goes here
	}

	public static BookCategoryFactory getInstance() throws Exception {
		try {
			if (bookCategoryFactory == null) {
				synchronized (UserFactory.class) {
					if (bookCategoryFactory == null) {
						bookCategoryFactory = new BookCategoryFactory();
					}
				}
			}
			return bookCategoryFactory;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public BookCategory createFromResultSet(ResultSet rs) throws Exception {

		int id = rs.getInt("id");
		String category = rs.getString("category");

		return new BookCategory(id, category);
	}

	@Override
	public BookCategory createFromHttpRequest(HttpServletRequest req) throws Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public BookCategory createFromJson(String json) throws Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public List<BookCategory> getListFromJson(String json) throws Exception {
		JSONArray array = JsonUtility.getJsonArrayFromJsonString(json);
		List<BookCategory> list = new ArrayList<BookCategory>();
		for (int i = 0, l = array.size(); i < l; i++) {
			JSONObject jo = (JSONObject) array.get(i);
			list.add(createFromJsonObject(jo));
		}
		return list;
	}

	@Override
	public BookCategory createFromMongoDocument(Document doc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private BookCategory createFromJsonObject(JSONObject jo) {
		int id = (int) (long) jo.get("id");
		String category = (String) jo.get("category");

		return new BookCategory(id, category);
	}
}
