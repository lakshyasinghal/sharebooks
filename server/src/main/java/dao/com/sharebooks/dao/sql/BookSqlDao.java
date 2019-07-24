package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.BookDao;
import com.sharebooks.dao.util.EntityConverterUtility;
import com.sharebooks.database.enums.Database;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.customQueries.BookQueries;
import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.factory.entityFactory.EntityFactory;

@SuppressWarnings("unused")
public class BookSqlDao extends AbstractSqlDao implements BookDao {
	private static final Logger LOGGER = Logger.getLogger(BookSqlDao.class.getName());
	private EntityFactory<Book> factory;
	private final Database database = Database.CORE;
	private final Table table = Table.BOOKS;
	private final BookQueries bookQueries = BookQueries.instance();

	public BookSqlDao(EntityFactory<Book> factory) {
		this.factory = factory;
	}

	@SuppressWarnings("unchecked")
	public List<Book> getBooks(Map<String, Object> map) throws SQLException, Exception {
		return EntityConverterUtility.convertIntoBookList(super.get(map, database, table, EntityType.BOOK));
	}

	public List<Book> getAllBooks() throws SQLException, Exception {
		return EntityConverterUtility.convertIntoBookList(super.getAll(database, table, EntityType.BOOK));
	}

	@Override
	public Book getBook(String uid) throws SQLException, Exception {
		Book book = null;
		try {
			book = (Book) super.getByUid(uid, database, table, EntityType.BOOK);
		} catch (Exception ex) {
			// do nothing
		}
		return book;
	}

	public List<Book> getBooksBySearchTerm(String searchTerm) throws Exception {
		LOGGER.trace("Entering getBooksBySearchTerm");
		String queryStr = bookQueries.getQueryForSearchTerm(searchTerm);
		return EntityConverterUtility
				.convertIntoBookList(super.getUsingQuery(queryStr, database, table, EntityType.BOOK));
	}

	@Override
	public List<Book> getSimilarBooks(Book book) throws SQLException, Exception {
		LOGGER.trace("Entering getSimilarBooks");
		String queryStr = bookQueries.getSimilarBooksQuery(book.title());
		return EntityConverterUtility
				.convertIntoBookList(super.getUsingQuery(queryStr, database, table, EntityType.BOOK));
	}

	public List<Book> getBooksByCategory(String category) throws Exception {
		LOGGER.trace("Entering getBooksByCategory");
		String queryStr = bookQueries.getByCategoryQuery(category);
		return EntityConverterUtility
				.convertIntoBookList(super.getUsingQuery(queryStr, database, table, EntityType.BOOK));
	}

	@Override
	public boolean createBook(Book book) throws SQLException, Exception {
		LOGGER.trace("Entering createBook");
		return super.create(book, database, table);
	}

	public boolean deleteBooks(Map<String, Object> map) throws SQLException, Exception {
		LOGGER.trace("Entering deleteBooks");
		return super.delete(map, database, table);
	}

	@Override
	public boolean deleteBook(String uid) throws SQLException, Exception {
		LOGGER.trace("Entering deleteBookById");
		return super.deleteByUid(uid, database, table);
	}

	@Override
	public boolean updateBook(Book book) throws SQLException, Exception {
		LOGGER.trace("Entering updateBook");
		return super.update(book, database, table);
	}

}
