package com.sharebooks.dao.generic;

import java.util.List;
import com.sharebooks.coreEntities.User;

public interface UserDao extends Dao {
	
	public List<User> getAllUsers();
	
	public User getUserById(int id);
	
	public boolean createUser(User user);
	
	public boolean deleteUserById(int id);
	
	public boolean updateUserPassword(int id , String password);
	
	public boolean updateContact(int id , String contactNo);
}
