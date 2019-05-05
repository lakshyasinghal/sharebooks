package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.sharebooks.coreEntities.Quote;
import com.sharebooks.coreEntities.enums.QuoteStatus;


public class QuoteFactory implements EntityFactory<Quote>{
	private static QuoteFactory instance;
	
	private QuoteFactory(){
		//nothing goes here
	}
	
	public static QuoteFactory getInstance() throws Exception{
		try{
			if(instance ==  null){
				synchronized(QuoteFactory.class){
					if(instance ==  null){
						instance = new QuoteFactory();
					}
				}
			}
			return instance;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	@Override
	public Quote createFromHttpRequest(HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quote createFromResultSet(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Quote createFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Quote> getListFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public Quote create(String bookUid, String userUid) throws Exception {
		if(bookUid==null || userUid==null){
			throw new NullPointerException("book uid and user uid cannot be null.");
		}
		return new Quote(-1, null, bookUid, userUid, QuoteStatus.INITIAL, null, null);
	}
}
