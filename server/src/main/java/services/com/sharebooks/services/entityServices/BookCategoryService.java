package com.sharebooks.services.entityServices;

import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.BookCategoryDao;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.helperEntities.BookCategory;
import com.sharebooks.exception.EmptyCache;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.sources.DaoSource;

public class BookCategoryService extends EntityService {
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(BookCategoryService.class.getName());
	private final BookCategoryDao dao = (BookCategoryDao) DaoSource.getDao(EntityType.BOOK_CATEGORY.desc());
	private final BookCategoryDao redisDao = (BookCategoryDao) DaoSource.getRedisDao(EntityType.BOOK_CATEGORY.desc());

	public BookCategoryService() {
		synchronized (BookCategoryService.class) {
			if (instanceCount == 1) {
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}

	public boolean putBookCategoriesInCache() throws Exception {
		LOGGER.trace("Entered putBookCategoriesInCache");
		List<BookCategory> bookCategories = dao.getBookCategories();
		boolean success = redisDao.insert(bookCategories);
		return success;
	}

	public List<BookCategory> getBookCategories() throws Exception {
		LOGGER.trace("Entered getBookCategories");
		List<BookCategory> bookCategories = null;
		bookCategories = redisDao.getBookCategories();
		if (bookCategories == null || bookCategories.size() == 0) {
			throw new EmptyCache("BookCategory cache.");
		}
		return bookCategories;
	}
}
