package com.sharebooks.dao.sql;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.AbstractOrderDao;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.customQueries.OrderQueries;
import com.sharebooks.entities.coreEntities.Order;

public class OrderSqlDao extends AbstractOrderDao {
	private static final Logger LOGGER = Logger.getLogger(OrderSqlDao.class.getName());
	private final Database database = Database.SHAREBOOKS_CORE;
	private final OrderQueries orderQueries = OrderQueries.instance();

	@Override
	public boolean createOrder(Order order) {

		return false;
	}

	@Override
	public boolean updateOrderStatus(String referenceNo, int status) {

		return false;
	}

}
