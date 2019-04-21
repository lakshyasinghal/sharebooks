package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.sharebooks.coreEntities.Book;
import com.sharebooks.coreEntities.BookRequest;
import com.sharebooks.coreEntities.enums.AvailableStatus;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.coreEntities.enums.RequestStatus;
import com.sharebooks.dao.generic.AbstractBookRequestDao;
import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.SqlInsertQueryProcessor;
import com.sharebooks.database.sql.SqlReadQueryProcessor;
import com.sharebooks.database.sql.SqlTransactionProcessor;
import com.sharebooks.database.sql.SqlUpdateQueryProcessor;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.query.SqlInsertQuery;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.database.sql.query.SqlReadQuery;
import com.sharebooks.database.sql.query.SqlUpdateQuery;
import com.sharebooks.entity.Entity;
import com.sharebooks.factory.entityFactory.EntityFactory;

@SuppressWarnings("unchecked")
public class BookRequestSqlDao extends AbstractBookRequestDao{
	private static final Logger LOGGER = Logger.getLogger(BookRequestSqlDao.class.getName());
	private EntityFactory<BookRequest> factory;
	private final Database database = Database.SHAREBOOKS;
	private final Table table = Table.BOOK_REQUESTS;

	
	public BookRequestSqlDao(EntityFactory<BookRequest> factory) {
		this.factory = factory;
	}
	
	@Override
	public List<BookRequest> getAllBookRequests() throws SQLException, Exception {
		return null;
	}

	
	@Override
	public List<BookRequest> getBookRequestsByBookOwnerUid(String bookOwnerUid) throws SQLException, Exception {
		LOGGER.trace("Entering getBookRequestByBookOwnerUid");
		LOGGER.debug("owner uid:"+bookOwnerUid);
		List<BookRequest> bookRequests = null;
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("bookOwnerUid", bookOwnerUid);
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		SqlQuery query = new SqlReadQuery(table.desc() , map);
		query.build();
		LOGGER.info(query.toString());
		List<Entity> entityList = (List<Entity>)queryProcessor.processReadQuery(database.desc() , query.toString(), EntityType.BOOK_REQUEST);
		bookRequests = convertIntoBookRequestList(entityList);
		LOGGER.trace("Leaving getBookRequestByBookOwnerUid");
		return bookRequests;
	}

	@Override
	public List<BookRequest> getBookRequestsByRequesterUid(String requesterUid) throws SQLException, Exception {
		LOGGER.trace("Entering getBookRequestsByRequesterUid");
		LOGGER.debug("requesterUid:"+requesterUid);
		List<BookRequest> bookRequests = null;
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("requesterUid", requesterUid);
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		SqlQuery query = new SqlReadQuery(table.desc() , map);
		query.build();
		LOGGER.info(query.toString());
		List<Entity> entityList = (List<Entity>)queryProcessor.processReadQuery(database.desc() , query.toString(), EntityType.BOOK_REQUEST);
		bookRequests = convertIntoBookRequestList(entityList);
		LOGGER.trace("bookRequests :"+bookRequests);
		LOGGER.trace("Leaving getBookRequestsByRequesterUid");
		return bookRequests;
	}

	@Override
	public boolean createBookRequest(BookRequest bookRequest) throws SQLException, Exception {
		LOGGER.trace("Entering createBookRequest");
		Map<String,Object> bookRequestMap = bookRequest.map();
		bookRequestMap.remove("id");
		SqlQuery query = new SqlInsertQuery(table.desc(), bookRequestMap);
		query.build();
		LOGGER.debug(query.toString());
		AbstractSqlQueryProcessor queryProcessor = SqlInsertQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processInsertQuery(database.desc(), query.toString(), false);
		LOGGER.trace("Leaving createBookRequest");
		return rowsAffected>0?true:false;
	}

	@Override
	public boolean updateBookRequest(BookRequest bookRequest) throws SQLException, Exception {
		LOGGER.trace("Entering updateBookRequest");
		//getting the map representation of book request which will be used to form the query
		Map<String,Object> bookRequestMap = bookRequest.map();
		SqlQuery query = new SqlUpdateQuery(table.desc(), bookRequestMap);
		query.build();
		LOGGER.debug(query.toString());
		AbstractSqlQueryProcessor queryProcessor = SqlUpdateQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processUpdateQuery(database.desc(), query.toString());
		LOGGER.trace("Leaving updateBookRequest");
		return rowsAffected>0?true:false;
	}

	@Override
	public boolean acceptBookRequest(String bookRequestUid, String bookUid) throws SQLException, Exception {
		LOGGER.trace("Entered acceptBookRequest");
		boolean updated = true;
		List<String> queries = new LinkedList<String>();
		//getting update query for book request
		Map<String,Object> bookRequestMap = new HashMap<String,Object>();
		bookRequestMap.put("uid",bookRequestUid);
		bookRequestMap.put("status",RequestStatus.ACCEPTED.id());
		SqlQuery query1 = new SqlUpdateQuery(Table.BOOK_REQUESTS.desc(), bookRequestMap); 
		query1.build();
		//getting update query for book in which book will be marked not available
		Map<String,Object> bookMap = new HashMap<String,Object>();
		bookMap.put("uid",bookUid);
		bookMap.put("available",AvailableStatus.NOT_AVAILABLE.id());
		SqlQuery query2 = new SqlUpdateQuery(Table.BOOKS.desc(), bookMap); 
		query2.build();
		
		//adding queries in list
		queries.add(query1.toString());
		queries.add(query2.toString());
		
		AbstractSqlQueryProcessor queryProcessor = SqlTransactionProcessor.getInstance();
		List<Integer> rowsAffectedResults = queryProcessor.processTransaction(database.desc(), queries);
		for(Integer rowsAffected: rowsAffectedResults){
			updated = updated && (rowsAffected>0);
		}
		LOGGER.trace("Leaving acceptBookRequest");
		return updated;
	}

	@Override
	public boolean rejectBookRequest(String bookRequestUid, String bookUid) throws SQLException, Exception {
		LOGGER.trace("Entered acceptBookRequest");
		//getting update query for book request
		Map<String,Object> bookRequestMap = new HashMap<String,Object>();
		bookRequestMap.put("uid",bookRequestUid);
		bookRequestMap.put("status",RequestStatus.REJECTED.id());
		SqlQuery query = new SqlUpdateQuery(Table.BOOK_REQUESTS.desc(), bookRequestMap); 
		query.build();
		AbstractSqlQueryProcessor queryProcessor = SqlUpdateQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processUpdateQuery(database.desc(), query.toString());
		LOGGER.trace("Leaving acceptBookRequest");
		return rowsAffected>0?true:false;
	}

}
