package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Logger;
import com.sharebooks.coreEntities.User;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.dao.generic.AbstractUserDao;
import com.sharebooks.database.sql.*;
import com.sharebooks.database.sql.query.SqlDeleteQuery;
import com.sharebooks.database.sql.query.SqlInsertQuery;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.database.sql.query.SqlReadQuery;
import com.sharebooks.entity.Entity;
import com.sharebooks.factory.entityFactory.EntityFactory;



public class UserSqlDao extends AbstractUserDao{
	private static final Logger LOGGER = Logger.getLogger(UserSqlDao.class.getName());
	@SuppressWarnings("unused")
	private EntityFactory<User> factory;
	private final Database database = Database.SHAREBOOKS;
	private final Table table = Table.USERS;

	
	public UserSqlDao(EntityFactory<User> factory) {
		this.factory = factory;
	}


	@Override
	public List<User> getAllUsers() {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public User getUserById(int id) {
		// TODO Auto-generated method stub
		return null;
	}


	@Override
	public boolean createUser(User user) throws SQLException,Exception{
		// TODO Auto-generated method stub
		LOGGER.entering("UserSqlDao", "createUser");
		LOGGER.finest(user.toString());
		//get book fields and values
		List<String> fields = user.fields();
		List<Object> values = user.values();
		//remove id field and id value from lists as it won't be required in insert query
		fields.remove(0);
		values.remove(0);
		SqlQuery query = new SqlInsertQuery(table.desc(), fields, values);
		query.build();
		LOGGER.info(query.toString());
		AbstractSqlQueryProcessor queryProcessor = SqlInsertQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processInsertQuery(database.desc(), query.toString(), false);
		LOGGER.info("Rows Affected:"+rowsAffected);
		LOGGER.exiting("UserSqlDao", "insertUser");
		return rowsAffected>0?true:false;
	}


	@Override
	public boolean deleteUserById(int id) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean updateUserPassword(int id, String password) {
		// TODO Auto-generated method stub
		return false;
	}


	@Override
	public boolean updateContact(int id, String contactNo) {
		// TODO Auto-generated method stub
		return false;
	}

	

}
