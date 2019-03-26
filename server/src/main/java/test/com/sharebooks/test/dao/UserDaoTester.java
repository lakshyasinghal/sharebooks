package com.sharebooks.test.dao;

import java.util.List;
import com.sharebooks.coreEntities.User;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.dao.generic.UserDao;
import com.sharebooks.sources.DaoSource;
import com.sharebooks.test.AbstractTester;
import com.sharebooks.test.util.DummyEntitySource;

public class UserDaoTester extends AbstractTester {
	// private UserDao dao;

	public UserDaoTester() {
		// this.dao = dao;
	}

	public static void main(String[] args) {
		// UserDao dao = (UserDao)DaoSource.getDao("User");
		UserDaoTester tester = new UserDaoTester();
		tester.test();
	}

	public void test() {
		initializeApp();
		// test1();
		// test2();
		test3();
		// test4();
	}

	// test for fetching Users
	public void test1() {
		try {
			UserDao dao = (UserDao) DaoSource.getDao("User");
			List<User> Users = dao.getAllUsers();
			if (Users != null && Users.size() > 0) {
				display("Test1 successful");
				newline();
				for (User User : Users) {
					display(User);
					newline();
				}
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// get User by id
	public void test2() {
		try {
			UserDao dao = (UserDao) DaoSource.getDao("User");
			User User = dao.getUserById(31);
			display(User);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// inserting User
	public void test3() {
		try {
			UserDao dao = (UserDao) DaoSource.getDao(EntityType.USER.desc());
			List<User> Users = DummyEntitySource.getUsers();
			boolean success = false;
			for (User User : Users) {
				success = dao.createUser(User);
			}
			display(success);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// deleting User by id
	public void test4() {
		try {
			UserDao dao = (UserDao) DaoSource.getDao("User");
			boolean success = dao.deleteUserById(34);
			display(success);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
