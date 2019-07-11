package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.SubscrptionDao;
import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.SqlInsertQueryProcessor;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.query.SqlInsertQuery;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.entities.coreEntities.Subscription;
import com.sharebooks.entities.coreEntities.User;

public class SubscrptionSqlDao implements SubscrptionDao {
	private static final Logger LOGGER = Logger.getLogger(SubscrptionSqlDao.class.getName());
	private final Database database = Database.SHAREBOOKS_USER_ACCOUNTS;
	private final Table table = Table.SUBSCRIPTIONS;
	// private final SubscriptionQueries userQueries =
	// SubscriptionQueries.instance();

	@Override
	public boolean createSubscription(Subscription subscription) throws SQLException, Exception {

		Map<String, Object> subscriptionMap = subscription.map();
		// remove id field and id value from map as it won't be required in insert query
		subscriptionMap.remove("id");
		SqlQuery query = new SqlInsertQuery(table.desc(), subscriptionMap);
		query.build();
		LOGGER.info(query.toString());
		AbstractSqlQueryProcessor queryProcessor = SqlInsertQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processInsertQuery(database.desc(), query.toString(), false);
		LOGGER.info("Rows Affected:" + rowsAffected);
		// LOGGER.exiting("UserSqlDao", "insertUser");
		return rowsAffected > 0 ? true : false;
	}

	@Override
	public boolean updateSubscription(User user) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteSubscription(String uid) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

}
