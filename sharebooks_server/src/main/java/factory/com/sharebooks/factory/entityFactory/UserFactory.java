package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.sharebooks.coreEntities.User;
import com.sharebooks.coreEntities.enums.Active;
import com.sharebooks.exception.FactoryException;

public class UserFactory implements EntityFactory<User>{
	private static UserFactory userFactory;
	
	private UserFactory(){
		//nothing goes here
	}
	
	public static UserFactory getInstance() throws Exception{
		try{
			if(userFactory ==  null){
				synchronized(UserFactory.class){
					if(userFactory ==  null){
						userFactory = new UserFactory();
					}
				}
			}
			return userFactory;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	
	
	@Override
	public User createFromHttpRequest(HttpServletRequest req) throws Exception {
	
		return null;
	}

	@Override
	public User createFromResultSet(ResultSet rs) throws Exception {
		try{
			int id = rs.getInt("id");
			String username = rs.getString("username");
			String password = rs.getString("password");
			String name = rs.getString("name");
			String birthDate = rs.getString("birthDate");
			int age = rs.getInt("age");
			String mobileNo = rs.getString("imgSrc");
			Active active = Active.valueOf(rs.getInt("active"));
			
			User user = new User(id , username , password , name , birthDate , age , mobileNo , active);
			return user;
		}
		catch(Exception ex){
			throw new FactoryException();
		}
	}

	@Override
	public User createFromJson(String json) throws Exception {
		try{
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			JSONObject jo = (JSONObject)obj;
			
			long id = (long)jo.get("id");
			String username = (String)jo.get("username");
			String password = (String)jo.get("password");
			String name = (String)jo.get("name");
			String birthDate = (String)jo.get("birthDate");
			int age = (int)(long)jo.get("age");
			String contactNo = (String)jo.get("contactNo");
			Active active = Active.valueOf((int)(long)jo.get("active"));
			
			User user = new User(id , username , password , name , birthDate , age , contactNo , active);
			return user;
		}
		catch(ParseException ex){
			throw ex;
		}
	}

}
