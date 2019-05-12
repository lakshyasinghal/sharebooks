package com.sharebooks.services.entityServices;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.cache.Cache;
import com.sharebooks.dao.generic.UserDao;
import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.helperEntities.Preference;
import com.sharebooks.exception.ExceptionType;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.exception.UsernameAlreadyExistsException;
import com.sharebooks.sources.CacheSource;
import com.sharebooks.sources.DaoSource;
import com.sharebooks.util.JsonUtility;
import com.sharebooks.util.PasswordUtils;

@SuppressWarnings("unchecked")
public class UserService extends EntityService {
	// instanceCount varaible will help in replicating the singleton
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());
	private final Cache<User> cache = (Cache<User>) CacheSource.getCache(EntityType.USER.desc());
	private final UserDao dao = (UserDao) DaoSource.getDao(EntityType.USER.desc());

	public UserService() {
		synchronized (UserService.class) {
			if (instanceCount == 1) {
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}

	public User login(String username, String password) throws Exception {
		User user = null;
		String encPass = PasswordUtils.encryptPassword(password);
		user = dao.getUserByUsernameAndPassword(username, encPass);
		return user;
	}

	public boolean createUser(User user) throws Exception {
		LOGGER.trace("Entering createUser");
		User existingUser = null;
		boolean created = false;
		existingUser = dao.getByUsername(user.username());
		if (existingUser != null) {
			throw new UsernameAlreadyExistsException();
		} else {
			String password = user.password();
			User userWithEncPass = user.cloneWithNewPassword(PasswordUtils.encryptPassword(password));
			created = dao.createUser(userWithEncPass);
			LOGGER.trace("Leaving createUser");
			return created;
		}
	}

	public boolean updateProfile(String uid, String name, String username, String contactNo, String password)
			throws Exception {
		LOGGER.trace("Entering updateProfile");
		boolean updated = false;
		password = PasswordUtils.encryptPassword(password);
		updated = dao.updateProfile(uid, name, username, contactNo, password);
		LOGGER.trace("Leaving updateProfile");
		return updated;
	}

	public List<User> getAllUsers() throws Exception {
		List<User> users = dao.getAllUsers();
		return users;
	}

	public User getUser(String uid) throws Exception {
		User user = (User) cache.get(uid);
		if (user == null) {
			user = dao.getUser(uid);
			cache.insert(uid, user);
		}
		return user;
	}

	public boolean updateUser(User user) throws Exception {
		LOGGER.trace("Entering updateUser");
		boolean updated = false;
		updated = dao.updateUser(user);
		LOGGER.trace("Leaving updateUser");
		return updated;
	}

	public boolean savePreferences(String uid, List<Preference> preferences) throws Exception {
		LOGGER.trace("Entering savePreferences");
		boolean updated = false;
		updated = dao.savePreferences(uid, JsonUtility.getJsonArrayFromList(preferences).toJSONString());
		LOGGER.trace("Leaving savePreferences");
		return updated;
	}
}
