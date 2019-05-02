package com.sharebooks.dao.generic;

import java.util.ArrayList;
import java.util.List;
import com.sharebooks.coreEntities.Notification;
import com.sharebooks.entity.Entity;


public abstract class AbstractNotificationDao implements NotificationDao {
	
	//function for converting entity list into Notification list
	protected List<Notification> convertIntoNotificationList(List<Entity> list) throws Exception{
		try{
			List<Notification> notificationList = new ArrayList<Notification>();
			for(Entity entity: list){
				if(entity instanceof Notification){
					notificationList.add((Notification)entity);
				}
				else{
					throw new Exception("Notification list containing some other entity");
				}
			}
			
			return notificationList;
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
