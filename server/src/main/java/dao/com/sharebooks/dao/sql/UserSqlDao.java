package com.sharebooks.dao.sql;

import java.sql.SQLException;
import java.util.*;
import org.apache.log4j.Logger;
import com.sharebooks.coreEntities.User;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.dao.generic.AbstractUserDao;
import com.sharebooks.database.sql.*;
import com.sharebooks.database.sql.query.SqlInsertQuery;
import com.sharebooks.database.sql.query.SqlQuery;
import com.sharebooks.database.sql.query.SqlReadQuery;
import com.sharebooks.database.sql.query.SqlUpdateQuery;
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
	
	@SuppressWarnings("unchecked")
	@Override
	public User getByUsername(String username) throws SQLException, Exception {
		LOGGER.trace("Entering getUserByUsername");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		User user = null;
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		//get sql read query
		SqlQuery query = new SqlReadQuery(table.desc() , map);
		query.build();
		LOGGER.debug(query.toString());
		List<Entity> entityList = (List<Entity>)queryProcessor.processReadQuery(database.desc() , query.toString(), EntityType.USER);
		
		List<User> users = convertIntoUserList(entityList);
		if(users.size()!=0){
			user = users.get(0);
		}
		LOGGER.trace("Leaving getUserByUsernameAndPassword");
		return user;
	}

	@SuppressWarnings("unchecked")
	@Override
	public User getUserByUsernameAndPassword(String username, String password) throws SQLException, Exception {
		LOGGER.trace("Entering getUserByUsernameAndPassword");
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("username", username);
		map.put("password",password);
		User user = null;
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		//get sql read query
		SqlQuery query = new SqlReadQuery(table.desc() , map);
		query.build();
		LOGGER.debug(query.toString());
		List<Entity> entityList = (List<Entity>)queryProcessor.processReadQuery(database.desc() , query.toString(), EntityType.USER);
		
		List<User> users = convertIntoUserList(entityList);
		if(users.size()!=0){
			user = users.get(0);
		}
		LOGGER.trace("Leaving getUserByUsernameAndPassword");
		return user;
	}
	
	

	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers(Map<String,Object> map) throws SQLException,Exception{
		LOGGER.trace("Entering getUsers");
		List<User> users = null;
		AbstractSqlQueryProcessor queryProcessor = SqlReadQueryProcessor.getInstance();
		//get sql read query
		SqlQuery query = new SqlReadQuery(table.desc() , map);
		query.build();
		LOGGER.info(query.toString());
		List<Entity> entityList = (List<Entity>)queryProcessor.processReadQuery(database.desc() , query.toString(), EntityType.USER);
		users = convertIntoUserList(entityList);
		LOGGER.trace("Leaving getUsers");
		return users;
	}

	@Override
	public List<User> getAllUsers() throws SQLException, Exception {
		LOGGER.trace("Entering getAllUsers");
		List<User> users = getUsers(null);
		LOGGER.trace("Leaving getAllUsers");
		return users;
	}


	@Override
	public User getUser(String uid) throws SQLException,Exception {
		LOGGER.trace("Entering GetUsersById");
		LOGGER.trace("uid:"+uid);;
		User user = null;
		Map<String , Object> map = new HashMap<String , Object>();
		map.put("uid", uid);
		List<User> users = getUsers(map);
		if(users!=null && users.size()>0){
			user = users.get(0);
		}
		LOGGER.trace("Leaving GetUsersById");
		return user;
	}


	@Override
	public boolean createUser(User user) throws SQLException,Exception{
		Map<String,Object> userMap = user.map();
		//remove id field and id value from map as it won't be required in insert query
		userMap.remove("id");
		SqlQuery query = new SqlInsertQuery(table.desc(), userMap);
		query.build();
		LOGGER.info(query.toString());
		AbstractSqlQueryProcessor queryProcessor = SqlInsertQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processInsertQuery(database.desc(), query.toString(), false);
		LOGGER.info("Rows Affected:"+rowsAffected);
		//LOGGER.exiting("UserSqlDao", "insertUser");
		return rowsAffected>0?true:false;
	}

	
	public boolean updateUser(User user) throws SQLException,Exception{
		LOGGER.trace("Entering updateUser");
		Map<String,Object> userMap = user.map();
		SqlQuery query = new SqlUpdateQuery(table.desc(), userMap);
		query.build();
		LOGGER.debug(query.toString());
		AbstractSqlQueryProcessor queryProcessor = SqlUpdateQueryProcessor.getInstance();
		int rowsAffected = queryProcessor.processUpdateQuery(database.desc(), query.toString());
		LOGGER.trace("Leaving updateUser");
		return rowsAffected>0?true:false;
	}

	@Override
	public boolean deleteUser(String uid) {
	
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
