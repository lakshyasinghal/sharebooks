package com.sharebooks.dao.redis;

import java.util.List;

import com.sharebooks.dao.enums.RedisKey;
import com.sharebooks.dao.generic.BookCategoryDao;
import com.sharebooks.entities.helperEntities.BookCategory;
import com.sharebooks.factory.entityFactory.BookCategoryFactory;
import com.sharebooks.util.JsonUtility;

public class BookCategoryRedisDao extends AbstractRedisDao implements BookCategoryDao {
	private static final BookCategoryRedisDao instance = new BookCategoryRedisDao();

	private BookCategoryRedisDao() {
	}

	public static BookCategoryRedisDao instance() {
		return instance;
	}

	@Override
	public List<BookCategory> getBookCategories() throws Exception {
		String json = super.get(RedisKey.BOOK_CATEGORIES.desc());
		List<BookCategory> bookCategories = BookCategoryFactory.getInstance().getListFromJson(json);
		return bookCategories;
	}

	@Override
	public boolean insert(List<BookCategory> bookCategories) throws Exception {
		String json = JsonUtility.getJsonArrayFromList(bookCategories).toJSONString();
		boolean success = super.insert(RedisKey.BOOK_CATEGORIES.desc(), json);
		return success;
	}

	@Override
	public boolean insert(BookCategory bookCategory) throws Exception {
		return false;
	}
}
