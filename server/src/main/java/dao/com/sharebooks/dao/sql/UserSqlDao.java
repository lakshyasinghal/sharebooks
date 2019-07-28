package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.UserDao;
import com.sharebooks.dao.util.EntityConverterUtility;
import com.sharebooks.database.enums.Database;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.customQueries.UserQueries;
import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.coreEntities.enums.EntityType;

public class UserSqlDao extends AbstractSqlDao implements UserDao {
	private static final Logger LOGGER = Logger.getLogger(UserSqlDao.class.getName());
	@SuppressWarnings("unused")
	// private EntityFactory<User> factory;
	private final Database database = Database.USER_ACCOUNTS;
	private final Table table = Table.USERS;
	private final UserQueries userQueries = UserQueries.instance();

	public UserSqlDao() {
		// this.factory = factory;
	}

	@Override
	public User getByUsername(String username) throws SQLException, Exception {
		LOGGER.trace("Entering getUserByUsername");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		User user = null;
		try {
			user = (User) super.getFirst(map, database, table, EntityType.USER);
		} catch (Exception ex) {
		}
		LOGGER.trace("Leaving getUserByUsernameAndPassword");
		return user;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) throws SQLException, Exception {
		LOGGER.trace("Entering getUserByUsernameAndPassword");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("username", username);
		map.put("password", password);
		User user = null;
		try {
			user = (User) super.getFirst(map, database, table, EntityType.USER);
		} catch (Exception ex) {
		}
		LOGGER.trace("Leaving getUserByUsernameAndPassword");
		return user;
	}

	// this query is used purposely
	@Override
	public User getUserByUidAndContact(String uid, String contactNo) throws SQLException, Exception {
		LOGGER.trace("Entering getUserByUidAndContact");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("contactNo", contactNo);
		User user = null;
		try {
			user = (User) super.getFirst(map, database, table, EntityType.USER);
		} catch (Exception ex) {
		}
		LOGGER.trace("Leaving getUserByUsernameAndPassword");
		return user;
	}

	@Override
	public List<User> getUsers(Map<String, Object> map) throws SQLException, Exception {
		LOGGER.trace("Entering getUsers");
		List<User> users = EntityConverterUtility.convertIntoUserList(super.get(map, database, table, EntityType.USER));
		LOGGER.trace("Leaving getUsers");
		return users;
	}

	public List<User> getUsers(List<String> uidList) throws SQLException, Exception {
		LOGGER.trace("Entered getUsers by uid list");
		String queryStr = userQueries.queryForGetUsersByUids(uidList);
		List<User> users = EntityConverterUtility
				.convertIntoUserList(super.getUsingQuery(queryStr, database, table, EntityType.USER));
		LOGGER.trace("Leaving getUsers by uid list");
		return users;
	}

	@Override
	public List<User> getAllUsers() throws SQLException, Exception {
		LOGGER.trace("Entering getAllUsers");
		return EntityConverterUtility.convertIntoUserList(super.getAll(database, table, EntityType.USER));
	}

	@Override
	public User getUser(String uid) throws SQLException, Exception {
		LOGGER.trace("Entering GetUsersById");
		User user = null;
		try {
			user = (User) super.getByUid(uid, database, table, EntityType.USER);
		} catch (Exception ex) {
		}
		return user;
	}

	@Override
	public boolean createUser(User user) throws SQLException, Exception {
		return super.create(user, database, table);
	}

	@Override
	public boolean updateProfile(String uid, String name, String username, String contactNo, String password)
			throws SQLException, Exception {
		LOGGER.trace("Entering updateProfile");
		// get sql read query
		String queryStr = userQueries.queryForUpdateProfile(uid, name, username, contactNo, password);
		return super.updateUsingQuery(queryStr, database, table);
	}

	@Override
	public boolean updatePassword(String uid, String newPassword) throws SQLException, Exception {
		LOGGER.trace("Entering updatePassword");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("password", newPassword);
		return super.update(map, database, table);
	}

	public boolean updateUser(User user) throws SQLException, Exception {
		LOGGER.trace("Entered updateUser");
		return super.update(user, database, table);
	}

	@Override
	// preferences will be of type json array
	public boolean savePreferences(String uid, String preferences) throws SQLException, Exception {
		LOGGER.trace("Entering savePreferences");
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("uid", uid);
		map.put("preferences", preferences);
		return super.update(map, database, table);
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
	public boolean updateSubscriptionStatusByEmail(String email, int subscriptionStatus)
			throws SQLException, Exception {
		LOGGER.trace("Entering updateSubscriptionStatusByEmail");
		String queryStr = userQueries.updateSubscriptionStatusByEmail(email, subscriptionStatus);
		return super.updateUsingQuery(queryStr, database, table);
	}

	@Override
	public boolean updateRegistrationStatusByEmail(String email, int isRegistered) throws SQLException, Exception {
		LOGGER.trace("Entering updateRegistrationStatusByEmail");
		String queryStr = userQueries.updateRegistrationStatusByEmail(email, isRegistered);
		return super.updateUsingQuery(queryStr, database, table);
	}

}
