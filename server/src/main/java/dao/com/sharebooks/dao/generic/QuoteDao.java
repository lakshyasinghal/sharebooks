package com.sharebooks.dao.generic;

import com.sharebooks.coreEntities.Quote;

public interface QuoteDao extends Dao{
	
	public boolean createQuote(Quote quote) throws Exception;
	
	public boolean updateQuote(String quoteUid, String bookUid, int status) throws Exception;

}
