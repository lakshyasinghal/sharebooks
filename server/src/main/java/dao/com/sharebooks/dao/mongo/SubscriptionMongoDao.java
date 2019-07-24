package com.sharebooks.dao.mongo;

import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.SubscriptionDao;
import com.sharebooks.database.enums.Database;
import com.sharebooks.database.mongo.Collection;
import com.sharebooks.entities.coreEntities.Subscription;
import com.sharebooks.entities.coreEntities.User;

public class SubscriptionMongoDao extends AbstractMongoDao implements SubscriptionDao {
	private static final Logger LOGGER = Logger.getLogger(SubscriptionMongoDao.class.getName());
	private final Database database = Database.USER_ACCOUNTS;
	private final Collection collection = Collection.SUBSCRIPTIONS;

	@Override
	public boolean createSubscription(Subscription subscription) throws SQLException, Exception {
		return super.create(subscription, database, collection);
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
