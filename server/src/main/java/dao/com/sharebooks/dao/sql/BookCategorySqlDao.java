package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.AbstractBookCategoryDao;
import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.SqlReadQueryProcessor;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.database.sql.query.SqlReadQuery;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.entities.helperEntities.BookCategory;

@SuppressWarnings("unchecked")
public class BookCategorySqlDao extends AbstractBookCategoryDao {
	private static final Logger LOGGER = Logger.getLogger(BookCategorySqlDao.class.getName());
	private final Database database = Database.SHAREBOOKS_MASTER;

	@Override
	public List<BookCategory> getBookCategories() throws SQLException, Exception {
		LOGGER.trace("Entered getBookCategories");
		List<BookCategory> bookCategories = null;
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		// get sql read query
		SqlQuery query = new SqlReadQuery(Table.BOOK_CATEGORIES.desc(), null);
		query.build();
		LOGGER.info(query.toString());
		List<Entity> entityList = (List<Entity>) queryProcessor.processReadQuery(database.desc(), query.toString(),
				EntityType.BOOK_CATEGORY);
		bookCategories = convertIntoBookCategoryList(entityList);
		LOGGER.trace("Leaving getBookCategories");
		return bookCategories;
	}

}
