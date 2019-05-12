package com.sharebooks.services.entityServices;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.BookDao;
import com.sharebooks.dao.generic.QuoteDao;
import com.sharebooks.dao.generic.UserDao;
import com.sharebooks.dtos.SummaryInfo;
import com.sharebooks.dtos.enums.DTOType;
import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.Quote;
import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.coreEntities.enums.QuoteStatus;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.factory.dtoFactory.SummaryInfoFactory;
import com.sharebooks.sources.DaoSource;
import com.sharebooks.sources.FactorySource;

public class QuoteService extends AbstractService{
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(QuoteService.class.getName());
	private final QuoteDao quoteDao = (QuoteDao) DaoSource.getDao(EntityType.Quote.desc());
	private final UserDao userDao = (UserDao) DaoSource.getDao(EntityType.USER.desc());
	private final BookDao bookDao = (BookDao) DaoSource.getDao(EntityType.BOOK.desc());
	private final SummaryInfoFactory summaryInfoFactory = (SummaryInfoFactory)FactorySource.getDTOFactory(DTOType.SUMMARY.desc());
	
	public QuoteService(){
		synchronized(QuoteService.class){
			if(instanceCount==1){
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}
	
	public Quote getQuote(String uid) throws Exception{
		Quote quote = quoteDao.getQuote(uid);
		return quote;
	}
	
	public boolean createQuote(Quote quote) throws Exception{
		boolean created = quoteDao.createQuote(quote);
		return created;
	}
	
	public boolean updateQuote(Quote quote) throws Exception{
		boolean created = quoteDao.updateQuote(quote);
		return created;
	}
	
	public SummaryInfo getSummary(String quoteUid) throws Exception{
		Quote quote = null;
		User user = null;
		Book book = null;
		quote = quoteDao.getQuote(quoteUid);
		if(quote!=null){
			user = userDao.getUser(quote.userUid());
			book = bookDao.getBook(quote.bookUid());
		}
		
		if(quote==null || book==null || user==null){
			return null;
		}
		return summaryInfoFactory.get(book, user, quote);
	}
	
	public boolean confirmQuote(String uid) throws Exception{
		boolean updated = quoteDao.confirmQuote(uid,QuoteStatus.CONFIRMED.id());
		return updated;
	}
}
