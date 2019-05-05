package com.sharebooks.dao.sql;

import java.util.HashMap;
import java.util.Map;
import org.apache.log4j.Logger;
import com.sharebooks.coreEntities.Quote;
import com.sharebooks.dao.generic.AbstractQuoteDao;
import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.SqlInsertQueryProcessor;
import com.sharebooks.database.sql.SqlUpdateQueryProcessor;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.customQueries.QuoteQueries;
import com.sharebooks.database.sql.query.SqlInsertQuery;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.database.sql.query.SqlUpdateQuery;

public class QuoteSqlDao extends AbstractQuoteDao{
	private static final Logger LOGGER = Logger.getLogger(OrderSqlDao.class.getName());
	private final Database database = Database.SHAREBOOKS;
	private final QuoteQueries quoteQueries = QuoteQueries.instance();
	
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
	public boolean updateQuote(String quoteUid, String bookUid, int status) throws Exception {
		LOGGER.trace("Entering updateQuote");
		Map<String,Object> quoteMap = new HashMap<String,Object>();
		quoteMap.put("uid", quoteUid);
		quoteMap.put("bookUid", bookUid);
		quoteMap.put("status", status);
		
		SqlQuery query = new SqlUpdateQuery(Table.Quotes.desc(), quoteMap);
		query.build();
		LOGGER.debug(query.toString());
		AbstractSqlQueryProcessor queryProcessor = SqlUpdateQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processUpdateQuery(database.desc(), query.toString());
		LOGGER.trace("Leaving updateQuote");
		return rowsAffected>0?true:false;
	}
}
