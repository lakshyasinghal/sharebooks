package com.sharebooks.dao.generic;

import java.sql.SQLException;

import com.sharebooks.entities.coreEntities.Subscription;
import com.sharebooks.entities.coreEntities.User;

public interface SubscriptionDao extends Dao {

	public boolean createSubscription(Subscription subscription) throws SQLException, Exception;

	public boolean updateSubscription(User user) throws SQLException, Exception;

	public boolean deleteSubscription(String uid) throws SQLException, Exception;
}
