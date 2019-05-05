package com.sharebooks.dao.sql;

import java.util.Map;

import org.apache.log4j.Logger;
import com.sharebooks.coreEntities.Order;
import com.sharebooks.coreEntities.Quote;
import com.sharebooks.dao.generic.AbstractOrderDao;
import com.sharebooks.database.sql.AbstractSqlQueryProcessor;
import com.sharebooks.database.sql.Database;
import com.sharebooks.database.sql.SqlInsertQueryProcessor;
import com.sharebooks.database.sql.Table;
import com.sharebooks.database.sql.customQueries.OrderQueries;
import com.sharebooks.database.sql.query.SqlInsertQuery;
import com.sharebooks.database.sql.query.SqlQuery;


public class OrderSqlDao extends AbstractOrderDao{
	private static final Logger LOGGER = Logger.getLogger(OrderSqlDao.class.getName());
	private final Database database = Database.SHAREBOOKS;
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
