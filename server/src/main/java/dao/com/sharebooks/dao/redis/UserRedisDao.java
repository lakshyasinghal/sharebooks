package com.sharebooks.dao.redis;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import com.sharebooks.dao.generic.UserDao;
import com.sharebooks.entities.coreEntities.User;


public class UserRedisDao extends GenericRedisDao implements UserDao{

	@Override
	public User getByUsername(String username) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUserByUsernameAndPassword(String username, String password) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getUsers(Map<String, Object> maps) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
	@Override
	public List<User> getUsers(List<String> uidList) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> getAllUsers() throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User getUser(String uid) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean createUser(User user) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUser(User user) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean deleteUser(String uid) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateUserPassword(int id, String password) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateContact(int id, String contactNo) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean savePreferences(String uid, String preferencesJsonArr) throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean updateProfile(String uid, String name, String username, String contactNo, String password)
			throws SQLException, Exception {
		// TODO Auto-generated method stub
		return false;
	}

	

}
