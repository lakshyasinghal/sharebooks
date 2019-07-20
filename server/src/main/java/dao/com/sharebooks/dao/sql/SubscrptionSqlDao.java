package com.sharebooks.dao.sql;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.SubscriptionDao;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.Table;
import com.sharebooks.entities.coreEntities.Subscription;
import com.sharebooks.entities.coreEntities.User;

public class SubscrptionSqlDao extends AbstractSqlDao implements SubscriptionDao {
	private static final Logger LOGGER = Logger.getLogger(SubscrptionSqlDao.class.getName());
	private final Database database = Database.SHAREBOOKS_USER_ACCOUNTS;
	private final Table table = Table.SUBSCRIPTIONS;
	// private final SubscriptionQueries userQueries =
	// SubscriptionQueries.instance();

	@Override
	public boolean createSubscription(Subscription subscription) throws SQLException, Exception {
		return super.create(subscription, database, table);
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
