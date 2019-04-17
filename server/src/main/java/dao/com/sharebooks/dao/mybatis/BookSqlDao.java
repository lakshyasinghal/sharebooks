package com.sharebooks.dao.mybatis;

import java.sql.SQLException;
import java.util.*;
import org.apache.log4j.Logger;

import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import com.sharebooks.coreEntities.Book;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.dao.generic.AbstractBookDao;
import com.sharebooks.database.sql.*;
import com.sharebooks.database.sql.query.SqlDeleteQuery;
import com.sharebooks.database.sql.query.SqlInsertQuery;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.database.sql.query.SqlReadQuery;
import com.sharebooks.entity.Entity;
import com.sharebooks.factory.entityFactory.EntityFactory;



public class BookSqlDao extends AbstractBookDao{
	private static final Logger LOGGER = Logger.getLogger(BookSqlDao.class.getName());
	private SqlSessionFactory SqlSessionFactory;
	@SuppressWarnings("unused")
	private EntityFactory<Book> factory;
	private final Database database = Database.SHAREBOOKS;
	private final Table table = Table.BOOKS;

	
	public BookSqlDao(EntityFactory<Book> factory,SqlSessionFactory sqlSessionFactory) {
		this.factory = factory;
		this.SqlSessionFactory = sqlSessionFactory;
	}

	@SuppressWarnings("unchecked")
	public List<Book> getBooks(Map<String , Object> map) throws SQLException,Exception {
		//LOGGER.entering("BookSqlDao", "getBooks");
		List<Book> books = null;
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		//get sql read query
		SqlQuery query = new SqlReadQuery(table.desc() , map);
		query.build();
		LOGGER.info(query.toString());
		List<Entity> entityList = (List<Entity>)queryProcessor.processReadQuery(database.desc() , query.toString(), EntityType.BOOK);
		books = convertIntoBookList(entityList);
		//LOGGER.exiting("BookSqlDao", "getBooks");
		return books;
	}
	
	
//	public List<Book> getAllBooks() throws SQLException,Exception{
//		//LOGGER.entering("BookSqlDao", "getAllBooks");
//		List<Book> books = getBooks(null);
//		//LOGGER.exiting("BookSqlDao", "getAllBooks");
//		return books;
//	}
	
	public List<Book> getAllBooks() throws SQLException,Exception{
		//LOGGER.entering("BookSqlDao", "getAllBooks");
		SqlSession session = SqlSessionFactory.openSession();
		List<Book> books = session.selectList("Book.getAll");
		session.commit();
		session.close();
		return books;
	}

	@Override
	public Book getBookById(int id) throws SQLException,Exception {
		//LOGGER.entering("BookSqlDao", "getBookById");
		LOGGER.info("id:"+id);
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("id", id);
		List<Book> books = getBooks(map);
		//LOGGER.exiting("BookSqlDao", "getBookById");
		return books.get(0);
	}

	@Override
	public boolean createBook(Book book) throws SQLException,Exception{
		//LOGGER.entering("BookSqlDao", "insertBook");
		//LOGGER.finest(book.toString());
		//get book fields and values
		List<String> fields = book.fields();
		List<Object> values = book.values();
		//remove id field and id value from lists as it won't be required in insert query
		fields.remove(0);
		values.remove(0);
		SqlQuery query = new SqlInsertQuery(table.desc(), fields, values);
		query.build();
		LOGGER.info(query.toString());
		AbstractSqlQueryProcessor queryProcessor = SqlInsertQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processInsertQuery(database.desc(), query.toString(), false);
		LOGGER.info("Rows Affected:"+rowsAffected);
		//LOGGER.exiting("BookSqlDao", "insertBook");
		return rowsAffected>0?true:false;
	}

	
	public boolean deleteBooks(Map<String , Object> map) throws SQLException,Exception{
		//LOGGER.entering("BookSqlDao", "deleteBooks");
		AbstractSqlQueryProcessor queryProcessor = SqlDeleteQueryProcessor.getInstance();
		SqlQuery query = new SqlDeleteQuery(table.desc(), map);
		query.build();
		LOGGER.info(query.toString());
		int rowsAffected = queryProcessor.processDeleteQuery(database.desc(), query.toString());
		LOGGER.info("Rows Affected:"+rowsAffected);
		//LOGGER.exiting("BookSqlDao", "deleteBooks");
		return rowsAffected>0?true:false;
	}
	
	@Override
	public boolean deleteBookById(int id) throws SQLException,Exception{
		//LOGGER.entering("BookSqlDao", "deleteBookById");
		LOGGER.info("id:"+id);
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id", id);
		boolean deleted = deleteBooks(map);
		LOGGER.info("Deleted:"+deleted);
		//LOGGER.exiting("BookSqlDao", "deleteBookById");
		return deleted;
	}

	@Override
	public boolean updateBook(Book book) throws SQLException,Exception{
		
		return false;
	}
}
