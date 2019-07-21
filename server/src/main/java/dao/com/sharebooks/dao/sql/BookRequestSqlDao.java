package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.BookRequestDao;
import com.sharebooks.dao.util.EntityConverterUtility;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.queries.SqlInsertQuery;
import com.sharebooks.database.sql.queries.SqlQuery;
import com.sharebooks.database.sql.queries.SqlUpdateQuery;
import com.sharebooks.entities.coreEntities.BookRequest;
import com.sharebooks.entities.coreEntities.Notification;
import com.sharebooks.entities.coreEntities.enums.AvailableStatus;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.coreEntities.enums.RequestStatus;
import com.sharebooks.factory.entityFactory.EntityFactory;

@SuppressWarnings("unchecked")
public class BookRequestSqlDao extends AbstractSqlDao implements BookRequestDao {
	private static final Logger LOGGER = Logger.getLogger(BookRequestSqlDao.class.getName());
	private EntityFactory<BookRequest> factory;
	private final Database database = Database.SHAREBOOKS_CORE;
	private final Table table = Table.BOOK_REQUESTS;

	public BookRequestSqlDao(EntityFactory<BookRequest> factory) {
		this.factory = factory;
	}

	@Override
	public List<BookRequest> getAllBookRequests() throws SQLException, Exception {
		return null;
	}

	public BookRequest getBookRequest(String uid) throws SQLException, Exception {
		LOGGER.trace("Entering getBookRequest");
		BookRequest bookRequest = null;
		try {
			bookRequest = (BookRequest) super.getByUid(uid, database, table, EntityType.BOOK_REQUEST);
		} catch (Exception ex) {
		}
		return bookRequest;
	}

	@Override
	public List<BookRequest> getBookRequestsByBookOwnerUid(String bookOwnerUid) throws SQLException, Exception {
		LOGGER.trace("Entering getBookRequestByBookOwnerUid");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("bookOwnerUid", bookOwnerUid);
		List<BookRequest> bookRequests = EntityConverterUtility
				.convertIntoBookRequestList(super.get(map, database, table, EntityType.BOOK_REQUEST));
		LOGGER.trace("Leaving getBookRequestByBookOwnerUid");
		return bookRequests;
	}

	@Override
	public List<BookRequest> getBookRequestsByRequesterUid(String requesterUid) throws SQLException, Exception {
		LOGGER.trace("Entering getBookRequestsByRequesterUid");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("requesterUid", requesterUid);
		List<BookRequest> bookRequests = EntityConverterUtility
				.convertIntoBookRequestList(super.get(map, database, table, EntityType.BOOK_REQUEST));
		LOGGER.trace("Leaving getBookRequestsByRequesterUid");
		return bookRequests;
	}

	@Override
	// this request will create transaction for updating book request,updating book
	// status, creating notification
	public boolean createBookRequest(BookRequest bookRequest, String bookUid, Notification notification)
			throws SQLException, Exception {
		LOGGER.trace("Entered createBookRequest");
		List<String> queries = new LinkedList<String>();

		Map<String, Object> bookRequestMap = bookRequest.map();
		bookRequestMap.remove("id");
		SqlQuery brQuery = new SqlInsertQuery(table.desc(), bookRequestMap);
		brQuery.build();

		// getting update query for book in which it will be marked not available
		Map<String, Object> bookMap = new HashMap<String, Object>();
		bookMap.put("uid", bookUid);
		bookMap.put("available", AvailableStatus.NOT_AVAILABLE.id());
		SqlQuery bookQuery = new SqlUpdateQuery(Table.BOOKS.desc(), bookMap);
		bookQuery.build();

		// create notification
		Map<String, Object> notificationMap = notification.map();
		notificationMap.remove("id");
		SqlQuery notificationQuery = new SqlInsertQuery(Table.NOTIFICATIONS.desc(), notificationMap);
		notificationQuery.build();

		// adding queries in list
		queries.add(brQuery.toString());
		queries.add(bookQuery.toString());
		queries.add(notificationQuery.toString());

		return super.processTransaction(queries, database);
	}

	@Override
	public boolean updateBookRequest(BookRequest bookRequest) throws SQLException, Exception {
		LOGGER.trace("Entering updateBookRequest");
		return super.update(bookRequest, database, table);
	}

	@Override
	// this request will create transaction for updating book request, creating
	// notification
	public boolean acceptBookRequest(String bookRequestUid, Notification notification) throws SQLException, Exception {
		LOGGER.trace("Entered acceptBookRequest");
		List<String> queries = new LinkedList<String>();

		// getting update query for book request
		Map<String, Object> bookRequestMap = new HashMap<String, Object>();
		bookRequestMap.put("uid", bookRequestUid);
		bookRequestMap.put("status", RequestStatus.ACCEPTED.id());
		SqlQuery brQuery = new SqlUpdateQuery(Table.BOOK_REQUESTS.desc(), bookRequestMap);
		brQuery.build();

		// create notification
		Map<String, Object> notificationMap = notification.map();
		notificationMap.remove("id");
		SqlQuery notificationQuery = new SqlInsertQuery(Table.NOTIFICATIONS.desc(), notificationMap);
		notificationQuery.build();

		// adding queries in list
		queries.add(brQuery.toString());
		queries.add(notificationQuery.toString());

		return super.processTransaction(queries, database);
	}

	@Override
	// this request will create transaction for updating book request,updating book
	// status, creating notification
	public boolean rejectBookRequest(String bookRequestUid, String bookUid, Notification notification)
			throws SQLException, Exception {
		LOGGER.trace("Entered acceptBookRequest");
		List<String> queries = new LinkedList<String>();

		// getting update query for book request
		Map<String, Object> bookRequestMap = new HashMap<String, Object>();
		bookRequestMap.put("uid", bookRequestUid);
		bookRequestMap.put("status", RequestStatus.REJECTED.id());
		SqlQuery brQuery = new SqlUpdateQuery(Table.BOOK_REQUESTS.desc(), bookRequestMap);
		brQuery.build();

		// getting update query for book in which it will be marked available from not
		// available
		Map<String, Object> bookMap = new HashMap<String, Object>();
		bookMap.put("uid", bookUid);
		bookMap.put("available", AvailableStatus.AVAILABLE.id());
		SqlQuery bookQuery = new SqlUpdateQuery(Table.BOOKS.desc(), bookMap);
		bookQuery.build();

		// create notification
		Map<String, Object> notificationMap = notification.map();
		notificationMap.remove("id");
		SqlQuery notificationQuery = new SqlInsertQuery(Table.NOTIFICATIONS.desc(), notificationMap);
		notificationQuery.build();

		// adding queries in list
		queries.add(brQuery.toString());
		queries.add(bookQuery.toString());
		queries.add(notificationQuery.toString());

		return super.processTransaction(queries, database);
	}

}
