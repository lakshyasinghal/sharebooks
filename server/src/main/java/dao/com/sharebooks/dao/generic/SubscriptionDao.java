package com.sharebooks.dao.generic;

import com.sharebooks.entities.coreEntities.Subscription;
import com.sharebooks.entities.coreEntities.User;

public interface SubscriptionDao extends Dao {

	public boolean createSubscription(Subscription subscription) throws Exception;

	public Subscription getByUserUid(String userUid) throws Exception;

	public boolean updateSubscription(User user) throws Exception;

	public boolean deleteSubscription(String uid) throws Exception;
}
