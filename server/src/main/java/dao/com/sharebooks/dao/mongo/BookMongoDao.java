package com.sharebooks.dao.mongo;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.BookDao;
import com.sharebooks.database.enums.Database;
import com.sharebooks.database.mongo.Collection;
import com.sharebooks.entities.coreEntities.Book;

public class BookMongoDao extends AbstractMongoDao implements BookDao {
	private static final Logger LOGGER = Logger.getLogger(BookMongoDao.class.getName());
	private final Database database = Database.USER_ACCOUNTS;
	private final Collection collection = Collection.BOOKS;

	@Override
	public List<Book> getBooks(Map<String, Object> map) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getAllBooks() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getSimilarBooks(Book book) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Book getBook(String uid) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksBySearchTerm(String searchTerm) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getBooksByCategory(String category) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createBook(Book book) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBooks(Map<String, Object> map) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteBook(String uid) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateBook(Book book) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
