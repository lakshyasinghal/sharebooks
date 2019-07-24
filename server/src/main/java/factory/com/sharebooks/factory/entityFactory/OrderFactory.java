package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.mongodb.DBObject;
import com.sharebooks.entities.coreEntities.Order;
import com.sharebooks.entities.coreEntities.enums.OrderStatus;
import com.sharebooks.entities.coreEntities.enums.OrderType;
import com.sharebooks.exception.FactoryException;

public class OrderFactory implements EntityFactory<Order> {
	private static OrderFactory orderFactory;

	private OrderFactory() {
		// nothing goes here
	}

	public static OrderFactory getInstance() throws Exception {
		try {
			if (orderFactory == null) {
				synchronized (OrderFactory.class) {
					if (orderFactory == null) {
						orderFactory = new OrderFactory();
					}
				}
			}

			return orderFactory;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Order createFromHttpRequest(HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order createFromResultSet(ResultSet rs) throws Exception {
		try {
			int id = rs.getInt("id");
			String referenceNo = rs.getString("referenceNo");
			String requestRefNo = rs.getString("requestRefNo");
			OrderType type = OrderType.valueOf(rs.getInt("type"));
			OrderStatus status = OrderStatus.valueOf(rs.getInt("status"));
			long buyerId = rs.getLong("buyerId");
			long sellerId = rs.getLong("sellerId");
			double amount = rs.getDouble("amount");

			Order order = new Order(id, null, referenceNo, requestRefNo, type, status, buyerId, sellerId, amount);
			return order;
		} catch (Exception ex) {
			throw new FactoryException();
		}
	}

	@Override
	public Order createFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Order> getListFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order createFromMongoDatabaseObject(DBObject dbObj) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
