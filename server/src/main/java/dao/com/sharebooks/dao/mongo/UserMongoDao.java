package com.sharebooks.dao.mongo;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.UserDao;
import com.sharebooks.dao.util.EntityConverterUtility;
import com.sharebooks.database.enums.Database;
import com.sharebooks.database.mongo.Collection;
import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.exception.NonFunctionalMethodException;

public class UserMongoDao extends AbstractMongoDao implements UserDao {
	private static final Logger LOGGER = Logger.getLogger(UserMongoDao.class.getName());
	private final Database database = Database.USER_ACCOUNTS;
	private final Collection collection = Collection.USERS;

	public UserMongoDao() {
		// do nothing
	}

	@Override
	public User getByUsername(String username) throws Exception {
		LOGGER.trace("Entering getUserByUsername");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		User user = null;
		try {
			user = (User) super.getFirst(map, database, collection, EntityType.USER);
		} catch (Exception ex) {
		}
		LOGGER.trace("Leaving getUserByUsernameAndPassword");
		return user;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) throws Exception {
		LOGGER.trace("Entering getUserByUsernameAndPassword");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		User user = null;
		try {
			user = (User) super.getFirst(map, database, collection, EntityType.USER);
		} catch (Exception ex) {
		}
		LOGGER.trace("Leaving getUserByUsernameAndPassword");
		return user;
	}

	// this query is used purposely
	@Override
	public User getUserByUidAndContact(String uid, String contactNo) throws Exception {
		LOGGER.trace("Entering getUserByUidAndContact");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("contactNo", contactNo);
		User user = null;
		try {
			user = (User) super.getFirst(map, database, collection, EntityType.USER);
		} catch (Exception ex) {
		}
		LOGGER.trace("Leaving getUserByUsernameAndPassword");
		return user;
	}

	@Override
	public List<User> getUsers(Map<String, Object> map) throws Exception {
		LOGGER.trace("Entering getUsers");
		List<User> users = EntityConverterUtility
				.convertIntoUserList(super.get(map, database, collection, EntityType.USER));
		LOGGER.trace("Leaving getUsers");
		return users;
	}

	public List<User> getUsers(List<String> uidList) throws Exception {
		throw new NonFunctionalMethodException();
	}

	@Override
	public List<User> getAllUsers() throws Exception {
		LOGGER.trace("Entering getAllUsers");
		return EntityConverterUtility.convertIntoUserList(super.getAll(database, collection, EntityType.USER));
	}

	@Override
	public User getUser(String uid) throws Exception {
		LOGGER.trace("Entering GetUsersById");
		User user = null;
		try {
			user = (User) super.getByUid(uid, database, collection, EntityType.USER);
		} catch (Exception ex) {
		}
		return user;
	}

	@Override
	public boolean createUser(User user) throws Exception {
		return super.create(user, database, collection);
	}

	@Override
	public boolean updateProfile(String uid, String name, String username, String contactNo, String password)
			throws Exception {
		LOGGER.trace("Entering updateProfile");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("name", name);
		map.put("username", username);
		map.put("contactNo", contactNo);
		map.put("password", password);
		return super.updateByUid(uid, map, database, collection);
	}

	@Override
	public boolean updatePassword(String uid, String newPassword) throws Exception {
		LOGGER.trace("Entering updatePassword");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("password", newPassword);
		return super.updateByUid(uid, map, database, collection);
	}

	public boolean updateUser(User user) throws Exception {
		LOGGER.trace("Entered updateUser");
		return super.update(user, database, collection);
	}

	@Override
	// preferences will be of type json array
	public boolean savePreferences(String uid, String preferences) throws Exception {
		LOGGER.trace("Entering savePreferences");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("preferences", preferences);
		return super.updateByUid(uid, map, database, collection);
	}

	@Override
	public boolean deleteUser(String uid) {
		return false;
	}

	@Override
	public boolean updateContact(int id, String contactNo) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateSubscriptionStatusByEmail(String email, int subscriptionStatus) throws Exception {
		LOGGER.trace("Entering updateSubscriptionStatusByEmail");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Map<String, Object> updateMap = new HashMap<String, Object>();
		queryMap.put("email", email);
		updateMap.put("subscriptionStatus", subscriptionStatus);
		return super.update(queryMap, updateMap, database, collection);
	}

	@Override
	public boolean updateRegistrationStatusByEmail(String email, int isRegistered) throws Exception {
		LOGGER.trace("Entering updateRegistrationStatusByEmail");
		Map<String, Object> queryMap = new HashMap<String, Object>();
		Map<String, Object> updateMap = new HashMap<String, Object>();
		queryMap.put("email", email);
		updateMap.put("isRegistered", isRegistered);
		return super.update(queryMap, updateMap, database, collection);
	}
}
