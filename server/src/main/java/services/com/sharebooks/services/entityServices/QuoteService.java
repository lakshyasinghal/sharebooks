package com.sharebooks.services.entityServices;

import org.apache.log4j.Logger;

import com.sharebooks.coreEntities.Quote;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.coreEntities.enums.QuoteStatus;
import com.sharebooks.dao.generic.QuoteDao;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.sources.DaoSource;

public class QuoteService {
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(QuoteService.class.getName());
	private final QuoteDao quoteDao = (QuoteDao) DaoSource.getDao(EntityType.Quote.desc());

	
	public QuoteService(){
		synchronized(QuoteService.class){
			if(instanceCount==1){
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}
	
	
	public boolean createQuote(Quote quote) throws Exception{
		boolean created = quoteDao.createQuote(quote);
		return created;
	}
	
	public boolean updateQuote(String quoteUid, String bookUid) throws Exception{
		boolean created = quoteDao.updateQuote(quoteUid,bookUid,QuoteStatus.FINAL.id());
		return created;
	}
}
