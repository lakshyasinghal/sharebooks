package com.sharebooks.dao.generic;

import java.sql.SQLException;
import java.util.*;
import com.sharebooks.coreEntities.Book;


public interface BookDao extends Dao {
	
	public List<Book> getBooks(Map<String , Object> map) throws SQLException,Exception;

	public List<Book> getAllBooks() throws SQLException,Exception;
	
	public List<Book> getSimilarBooks(Book book) throws SQLException,Exception;
	
	public Book getBook(String uid) throws SQLException,Exception;
	
	public List<Book> getBooksBySearchTerm(String searchTerm) throws SQLException,Exception;
	
	public List<Book> getBooksByCategory(String category) throws SQLException,Exception;
	
	public boolean createBook(Book book) throws SQLException,Exception;
	
	public boolean deleteBooks(Map<String , Object> map) throws SQLException,Exception;
	
	public boolean deleteBook(String uid) throws SQLException,Exception;
	
	public boolean updateBook(Book book) throws SQLException,Exception;
}
