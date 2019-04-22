package com.sharebooks.services.entityServices;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.log4j.Logger;
import com.sharebooks.coreEntities.BookRequest;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.dao.generic.BookRequestDao;
import com.sharebooks.exception.CacheException;
import com.sharebooks.exception.ExceptionType;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.sources.DaoSource;

public class BookRequestService extends EntityService {
	//instanceCount varaible will help in replicating the singleton 
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(BookRequestService.class.getName());
	private final BookRequestDao dao = (BookRequestDao) DaoSource.getDao(EntityType.BOOK_REQUEST.desc());
	
	public BookRequestService(){
		synchronized(BookRequestService.class){
			if(instanceCount==1){
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}
	
	
	//uid is the user uid which will be used to fetch both received book requests and sent book requests
	public List<List<BookRequest>> getBookRequestsByUid(String uid) throws SQLException,Exception{
		LOGGER.trace("Entering getBookRequestsByUid");
		try{
			//the outer list will contain two lists of book requests
			//at 0 we will have received book requests list and at 1 sent book requests list
			List<List<BookRequest>> bookRequestListList = new ArrayList<List<BookRequest>>();
			List<BookRequest> receivedBookRequests = dao.getBookRequestsByBookOwnerUid(uid);
			List<BookRequest> sentBookRequests = dao.getBookRequestsByRequesterUid(uid);
			bookRequestListList.add(receivedBookRequests);
			bookRequestListList.add(sentBookRequests);
			LOGGER.trace("Leaving getBookRequestsByUid");
			return bookRequestListList;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			LOGGER.debug(ex.getSQLState());
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			throw ex;
		}
	}
	
	
	//create book request method with book request argument
	public boolean createBookRequest(BookRequest bookRequest) throws SQLException,Exception{
		LOGGER.trace("Entering createBookRequest");
		try{
			boolean created = dao.createBookRequest(bookRequest);
			LOGGER.trace("Leaving createBookRequest");
			return created;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			throw ex;
		}
	}
	
	public boolean updateBookRequest(BookRequest bookRequest) throws SQLException,Exception{
		LOGGER.trace("Entering updateBookRequest");
		try{
			boolean updated = dao.updateBookRequest(bookRequest);
			LOGGER.trace("Leaving updateBookRequest");
			return updated;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			throw ex;
		}
	}
	
	public boolean acceptBookRequest(BookRequest bookRequest) throws SQLException,Exception{
		LOGGER.trace("Entering acceptBookRequest");
		try{
			boolean accepted = dao.acceptBookRequest(bookRequest.uid(),bookRequest.bookUid());
			LOGGER.trace("Leaving acceptBookRequest");
			return accepted;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			throw ex;
		}
	}
	
	public boolean rejectBookRequest(BookRequest bookRequest) throws SQLException,Exception{
		LOGGER.trace("Entering acceptBookRequest");
		try{
			boolean rejected = dao.rejectBookRequest(bookRequest.uid(),bookRequest.bookUid());
			LOGGER.trace("Leaving acceptBookRequest");
			return rejected;
		}
		catch(SQLException ex){
			sendExceptionMail(ExceptionType.SQL , ex);
			throw ex;
		}
		catch(Exception ex){
			sendExceptionMail(ExceptionType.UNIDENTIFIED , ex);
			throw ex;
		}
	}
}
