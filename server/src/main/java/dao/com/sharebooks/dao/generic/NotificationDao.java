package com.sharebooks.dao.generic;

import java.sql.SQLException;
import java.util.List;

import com.sharebooks.entities.coreEntities.Notification;
public interface NotificationDao extends Dao {

public List<Notification> getAllNotifications();
	
	public List<Notification> getNotifications(String recepientUid) throws SQLException,Exception;;
	
	public boolean updateNotification(Notification notification) throws SQLException,Exception;
}
