package com.sharebooks.dao.generic;

import java.sql.SQLException;
import java.util.List;
import com.sharebooks.coreEntities.User;

public interface UserDao extends Dao {
	
	public List<User> getAllUsers() throws SQLException,Exception;
	
	public User getUserById(int id) throws SQLException,Exception;
	
	public boolean createUser(User user) throws SQLException,Exception;
	
	public boolean deleteUserById(int id) throws SQLException,Exception;
	
	public boolean updateUserPassword(int id , String password) throws SQLException,Exception;
	
	public boolean updateContact(int id , String contactNo) throws SQLException,Exception;
}
