package com.sharebooks.dao.redis;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sharebooks.dao.generic.BookDao;
import com.sharebooks.entities.coreEntities.Book;

public class BookRedisDao extends GenericRedisDao implements BookDao{

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

	@Override
	public List<Book> getBooksByCategory(String category) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Book> getSimilarBooks(Book book) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
