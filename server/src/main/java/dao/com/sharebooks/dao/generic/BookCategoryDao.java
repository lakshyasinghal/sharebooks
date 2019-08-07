package com.sharebooks.dao.generic;

import java.util.List;

import com.sharebooks.entities.helperEntities.BookCategory;

public interface BookCategoryDao extends Dao {

	public List<BookCategory> getBookCategories() throws Exception;

	public boolean insert(List<BookCategory> bookCategories) throws Exception;

	public boolean insert(BookCategory bookCategory) throws Exception;
}
