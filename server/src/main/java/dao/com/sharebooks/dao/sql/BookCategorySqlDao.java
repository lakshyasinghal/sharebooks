package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.BookCategoryDao;
import com.sharebooks.dao.util.EntityConverterUtility;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.Table;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.helperEntities.BookCategory;

public class BookCategorySqlDao extends AbstractSqlDao implements BookCategoryDao {
	private static final Logger LOGGER = Logger.getLogger(BookCategorySqlDao.class.getName());
	private final Database database = Database.SHAREBOOKS_MASTER;

	@Override
	public List<BookCategory> getBookCategories() throws SQLException, Exception {
		LOGGER.trace("Entered getBookCategories");
		return EntityConverterUtility
				.convertIntoBookCategoryList(super.getAll(database, Table.BOOK_CATEGORIES, EntityType.BOOK_CATEGORY));
	}

}
