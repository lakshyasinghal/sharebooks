package com.sharebooks.dao.generic;

import java.sql.SQLException;
import java.util.List;
import com.sharebooks.coreEntities.BookRequest;
import com.sharebooks.coreEntities.Notification;

public interface BookRequestDao extends Dao{
	
	public List<BookRequest> getAllBookRequests() throws SQLException,Exception;
	
	public List<BookRequest> getBookRequestsByBookOwnerUid(String uid) throws SQLException,Exception;
	
	public List<BookRequest> getBookRequestsByRequesterUid(String uid) throws SQLException,Exception;
	
	public boolean createBookRequest(BookRequest bookRequest, String bookUid, Notification notification) throws SQLException,Exception;
	
	public boolean updateBookRequest(BookRequest bookRequest) throws SQLException,Exception;
	
	public boolean acceptBookRequest(String bookRequestUid, Notification notification) throws SQLException,Exception;
	
	public boolean rejectBookRequest(String bookRequestUid, String bookUid, Notification notification) throws SQLException,Exception;
}
