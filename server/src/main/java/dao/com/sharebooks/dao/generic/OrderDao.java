package com.sharebooks.dao.generic;

import java.util.List;
import com.sharebooks.coreEntities.Order;
import com.sharebooks.coreEntities.Quote;


public interface OrderDao extends Dao {
	
	public boolean createOrder(Order order) throws Exception;
	
	public boolean updateOrderStatus(String referenceNo , int status) throws Exception;

}
