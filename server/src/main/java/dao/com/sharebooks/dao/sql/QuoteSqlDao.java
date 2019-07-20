package com.sharebooks.dao.sql;

import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.QuoteDao;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.customQueries.QuoteQueries;
import com.sharebooks.entities.coreEntities.Quote;
import com.sharebooks.entities.coreEntities.enums.EntityType;

public class QuoteSqlDao extends AbstractSqlDao implements QuoteDao {
	private static final Logger LOGGER = Logger.getLogger(OrderSqlDao.class.getName());
	private final Database database = Database.SHAREBOOKS_CORE;
	private final QuoteQueries quoteQueries = QuoteQueries.instance();

	@Override
	public Quote getQuote(String uid) throws Exception {
		LOGGER.trace("Entering getQuote");
		Quote quote = null;
		try {
			quote = (Quote) super.getByUid(uid, database, Table.QUOTES, EntityType.QUOTE);
		} catch (Exception ex) {

		}
		return quote;
	}

	@Override
	public boolean createQuote(Quote quote) throws Exception {
		LOGGER.trace("Entering createQuote");
		return super.create(quote, database, Table.QUOTES);
	}

	@Override
	public boolean updateQuote(Quote quote) throws Exception {
		LOGGER.trace("Entering updateQuote");
		return super.update(quote, database, Table.QUOTES);
	}

	@Override
	public boolean confirmQuote(String uid, int status) throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("status", status);
		return super.update(map, database, Table.QUOTES);
	}

}
