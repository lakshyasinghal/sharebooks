package com.sharebooks.dao.sql;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.AbstractQuoteDao;
import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.SqlInsertQueryProcessor;
import com.sharebooks.database.sql.SqlReadQueryProcessor;
import com.sharebooks.database.sql.SqlUpdateQueryProcessor;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.customQueries.QuoteQueries;
import com.sharebooks.database.sql.query.SqlInsertQuery;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.database.sql.query.SqlReadQuery;
import com.sharebooks.database.sql.query.SqlUpdateQuery;
import com.sharebooks.entities.coreEntities.Quote;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;

public class QuoteSqlDao extends AbstractQuoteDao{
	private static final Logger LOGGER = Logger.getLogger(OrderSqlDao.class.getName());
	private final Database database = Database.SHAREBOOKS;
	private final QuoteQueries quoteQueries = QuoteQueries.instance();
	
	
	@SuppressWarnings("unchecked")
	@Override
	public Quote getQuote(String uid) throws Exception {
		LOGGER.trace("Entering getQuote");
		List<Quote> quotes = null;
		Map<String,Object> filterMap = new HashMap<String,Object>();
		filterMap.put("uid", uid);
		SqlQuery query = new SqlReadQuery(Table.Quotes.desc() , filterMap);
		query.build();
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		List<Entity> entityList = (List<Entity>)queryProcessor.processReadQuery(database.desc() , query.toString(), EntityType.Quote);
		quotes = convertIntoQuoteList(entityList);
		LOGGER.trace("Leaving getQuote");
		if(quotes!=null && quotes.size()>0){
			return quotes.get(0);
		}
		else{
			return null;
		}
	}
	
	@Override
	public boolean createQuote(Quote quote) throws Exception {
		LOGGER.trace("Entering createQuote");
		//get quoteMap map 
		Map<String,Object> quoteMap = quote.map();
		//remove id field and id value from map as it won't be required in insert query
		quoteMap.remove("id");
		SqlQuery query = new SqlInsertQuery(Table.Quotes.desc(), quoteMap);
		query.build();
		AbstractSqlQueryProcessor queryProcessor = SqlInsertQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processInsertQuery(database.desc(), query.toString(), false);
		LOGGER.info("Rows Affected:"+rowsAffected);
		LOGGER.trace("Leaving createQuote");
		return rowsAffected>0?true:false;
	}

	@Override
	public boolean updateQuote(Quote quote) throws Exception {
		LOGGER.trace("Entering updateQuote");
		Map<String,Object> quoteMap = quote.map();
		SqlQuery query = new SqlUpdateQuery(Table.Quotes.desc(), quoteMap);
		query.build();
		AbstractSqlQueryProcessor queryProcessor = SqlUpdateQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processUpdateQuery(database.desc(), query.toString());
		LOGGER.trace("Leaving updateQuote");
		return rowsAffected>0?true:false;
	}

	@Override
	public boolean confirmQuote(String uid, int status) throws Exception {
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("uid",uid);
		map.put("status",status);
		SqlQuery query = new SqlUpdateQuery(Table.Quotes.desc(), map);
		query.build();
		AbstractSqlQueryProcessor queryProcessor = SqlUpdateQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processUpdateQuery(database.desc(), query.toString());
		LOGGER.trace("Leaving updateQuote");
		return rowsAffected>0?true:false;
	}

	
}
