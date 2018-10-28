package com.sharebooks.dao.generic;

import java.util.List;
import com.sharebooks.coreEntities.Order;

public interface OrderDao extends Dao {
	
	public List<Order> getOrderByReferenceNo(String referenceNo);
	
	public boolean createOrder(Order order);
	
	public boolean updateOrderStatus(String referenceNo , int status);
}
