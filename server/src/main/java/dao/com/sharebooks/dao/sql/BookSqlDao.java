package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.*;
import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.AbstractBookDao;
import com.sharebooks.database.sql.*;
import com.sharebooks.database.sql.customQueries.BookQueries;
import com.sharebooks.database.sql.query.SqlDeleteQuery;
import com.sharebooks.database.sql.query.SqlInsertQuery;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.database.sql.query.SqlReadQuery;
import com.sharebooks.database.sql.query.SqlUpdateQuery;
import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.factory.entityFactory.EntityFactory;


@SuppressWarnings("unused")
public class BookSqlDao extends AbstractBookDao{
	private static final Logger LOGGER = Logger.getLogger(BookSqlDao.class.getName());
	private EntityFactory<Book> factory;
	private final Database database = Database.SHAREBOOKS;
	private final Table table = Table.BOOKS;
	private final BookQueries bookQueries = BookQueries.instance();

	
	public BookSqlDao(EntityFactory<Book> factory) {
		this.factory = factory;
	}

	@SuppressWarnings("unchecked")
	public List<Book> getBooks(Map<String , Object> map) throws SQLException,Exception {
		LOGGER.trace("Entering getBooks");
		List<Book> books = null;
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		//get sql read query
		SqlQuery query = new SqlReadQuery(table.desc() , map);
		query.build();
		LOGGER.info(query.toString());
		List<Entity> entityList = (List<Entity>)queryProcessor.processReadQuery(database.desc() , query.toString(), EntityType.BOOK);
		books = convertIntoBookList(entityList);
		LOGGER.trace("Leaving getBooks");
		return books;
	}
	
	
	public List<Book> getAllBooks() throws SQLException,Exception{
		LOGGER.trace("Entering getAllBooks");
		List<Book> books = getBooks(null);
		LOGGER.trace("Leaving getAllBooks");
		return books;
	}
	
	

	@Override
	public Book getBook(String uid) throws SQLException,Exception {
		LOGGER.trace("Entering getBookById");
		LOGGER.trace("uid:"+uid);
		Book book = null;
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("uid", uid);
		List<Book> books = getBooks(map);
		if(books!=null && books.size()>0){
			book = books.get(0);
		}
		LOGGER.trace("Leaving getBookById");
		return books.get(0);
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Book> getBooksBySearchTerm(String searchTerm) throws Exception{
		LOGGER.trace("Entering getBooksBySearchTerm");
		List<Book> books = null;
		//get sql read query
		String query = bookQueries.getQueryForSearchTerm(searchTerm);
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		List<Entity> entityList = (List<Entity>)queryProcessor.processReadQuery(database.desc() , query, EntityType.BOOK);
		books = convertIntoBookList(entityList);
		LOGGER.trace("Leaving getBooksBySearchTerm");
		return books;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Book> getSimilarBooks(Book book) throws SQLException, Exception {
		LOGGER.trace("Entering getSimilarBooks");
		List<Book> books = null;
		//get sql read query
		String query = bookQueries.getSimilarBooksQuery(book.title());
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		List<Entity> entityList = (List<Entity>)queryProcessor.processReadQuery(database.desc() , query, EntityType.BOOK);
		books = convertIntoBookList(entityList);
		LOGGER.trace("Leaving getSimilarBooks");
		
		return books;
	}
	
	@SuppressWarnings("unchecked")
	public List<Book> getBooksByCategory(String category) throws Exception{
		LOGGER.trace("Entering getBooksByCategory");
		List<Book> books = null;
		//get sql read query
		String query = bookQueries.getByCategoryQuery(category);
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		List<Entity> entityList = (List<Entity>)queryProcessor.processReadQuery(database.desc() , query, EntityType.BOOK);
		books = convertIntoBookList(entityList);
		LOGGER.trace("Leaving getBooksByCategory");
		return books;
	}

	@Override
	public boolean createBook(Book book) throws SQLException,Exception{
		LOGGER.trace("Entering createBook");
		//get book map 
		Map<String,Object> bookMap = book.map();
		//remove id field and id value from map as it won't be required in insert query
		bookMap.remove("id");
		SqlQuery query = new SqlInsertQuery(table.desc(), bookMap);
		query.build();
		LOGGER.info(query.toString());
		AbstractSqlQueryProcessor queryProcessor = SqlInsertQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processInsertQuery(database.desc(), query.toString(), false);
		LOGGER.info("Rows Affected:"+rowsAffected);
		LOGGER.trace("Leaving createBook");
		return rowsAffected>0?true:false;
	}

	
	public boolean deleteBooks(Map<String , Object> map) throws SQLException,Exception{
		LOGGER.trace("Entering deleteBooks");
		AbstractSqlQueryProcessor queryProcessor = SqlDeleteQueryProcessor.getInstance();
		SqlQuery query = new SqlDeleteQuery(table.desc(), map);
		query.build();
		LOGGER.info(query.toString());
		int rowsAffected = queryProcessor.processDeleteQuery(database.desc(), query.toString());
		LOGGER.info("Rows Affected:"+rowsAffected);
		LOGGER.trace("Leaving deleteBooks");
		return rowsAffected>0?true:false;
	}
	
	@Override
	public boolean deleteBook(String uid) throws SQLException,Exception{
		LOGGER.trace("Entering deleteBookById");
		LOGGER.info("uid:"+uid);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uid", uid);
		boolean deleted = deleteBooks(map);
		LOGGER.info("Deleted:"+deleted);
		LOGGER.trace("Leaving deleteBookById");
		return deleted;
	}

	@Override
	public boolean updateBook(Book book) throws SQLException,Exception{
		LOGGER.trace("Entering updateBook");
		Map<String,Object> bookMap = book.map();
		
		SqlQuery query = new SqlUpdateQuery(table.desc(), bookMap);
		query.build();
		LOGGER.debug(query.toString());
		AbstractSqlQueryProcessor queryProcessor = SqlUpdateQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processUpdateQuery(database.desc(), query.toString());
		LOGGER.trace("Leaving updateBook");
		return rowsAffected>0?true:false;
	}

}
