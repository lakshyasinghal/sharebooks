package com.sharebooks.dao.generic;


import java.util.ArrayList;
import java.util.List;
import com.sharebooks.coreEntities.User;
import com.sharebooks.entity.Entity;


public abstract class AbstractUserDao implements UserDao{
	
	//function for converting entity list into user list
		protected List<User> convertIntoUserList(List<Entity> list) throws Exception{
			try{
				List<User> userList = new ArrayList<User>();
				for(Entity entity: list){
					if(entity instanceof User){
						userList.add((User)entity);
					}
					else{
						throw new Exception("User list containing some other entity");
					}
				}
				
				return userList;
			}
			catch(Exception ex){
				throw ex;
			}
		}
}
