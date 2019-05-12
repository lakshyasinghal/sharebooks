package com.sharebooks.dao.generic;

import com.sharebooks.entities.coreEntities.Quote;

public interface QuoteDao extends Dao{
	
	public Quote getQuote(String uid) throws Exception;
	
	public boolean createQuote(Quote quote) throws Exception;
	
	public boolean updateQuote(Quote quote) throws Exception;

	public boolean confirmQuote(String uid, int status) throws Exception;
}
