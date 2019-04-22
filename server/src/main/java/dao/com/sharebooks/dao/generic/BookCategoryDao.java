package com.sharebooks.dao.generic;

import java.sql.SQLException;
import java.util.List;
import com.sharebooks.helperEntities.BookCategory;

public interface BookCategoryDao extends Dao{

	public List<BookCategory> getBookCategories() throws SQLException,Exception;
}
