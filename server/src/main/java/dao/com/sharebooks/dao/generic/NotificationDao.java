package com.sharebooks.dao.generic;

import java.util.List;
import com.sharebooks.coreEntities.Notification;
public interface NotificationDao extends Dao {

public List<Notification> getAllNotifications();
	
	public List<Notification> getNotificationsByTargetUserId(int targetUserId);
	
	public boolean createNotification(Notification notification);
}
