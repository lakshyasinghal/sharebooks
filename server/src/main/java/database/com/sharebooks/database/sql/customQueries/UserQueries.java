package com.sharebooks.database.sql.customQueries;

import org.apache.log4j.Logger;
import com.sharebooks.database.sql.Table;


public class UserQueries {
	private static final Logger LOGGER = Logger.getLogger(UserQueries.class.getName());
	private static final UserQueries instance = new UserQueries();
	
	private UserQueries(){
		
	}
	
	public static UserQueries instance(){
		return instance;
	}
	
	
	public String queryForUpdateProfile(String uid, String name, String username, String contactNo, String password){
		StringBuilder query = new StringBuilder();
		query.append("UPDATE ");
		query.append(Table.USERS.desc());
		query.append(" SET ");
		query.append("NAME='"+name+"'");
		query.append(",");
		query.append("USERNAME='"+username+"'");
		query.append(",");
		query.append("CONTACTNO='"+contactNo+"'");
		query.append(",");
		query.append("PASSWORD='"+password+"'");
		query.append(" WHERE ");
		query.append("UID='"+uid+"'");
		query.append(";");
		LOGGER.debug("Query => " + query.toString());
		return query.toString();
	}

}
