package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.DBObject;
import com.sharebooks.entities.helperEntities.BookCategory;
import com.sharebooks.exception.NonFunctionalMethodException;

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
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public BookCategory createFromMongoDatabaseObject(DBObject dbObj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
