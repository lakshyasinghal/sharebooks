package com.sharebooks.dao.generic;

import java.util.List;
import com.sharebooks.coreEntities.BookRequest;

public interface BookRequestDao extends Dao{
	
	public List<BookRequest> getAllBookRequests();
	
	public BookRequest getBookRequestById(int id);
	
	public List<BookRequest> getBookRequestByBookOwnerId();
	
	public List<BookRequest> getBookRequestByRequestorId();
	
	public boolean createBookRequest(BookRequest bookRequest);
	
	public boolean updateBookRequest(BookRequest bookRequest);
	
	public boolean updateBookRequestStatus(String referenceNo , int status);
}
