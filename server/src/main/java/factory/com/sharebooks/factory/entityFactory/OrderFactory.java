package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import javax.servlet.http.HttpServletRequest;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.sharebooks.coreEntities.Order;
import com.sharebooks.coreEntities.enums.*;
import com.sharebooks.exception.FactoryException;

public class OrderFactory implements EntityFactory<Order>{
	private static OrderFactory orderFactory;
	
	private OrderFactory(){
		//nothing goes here
	}
	
	public static OrderFactory getInstance() throws Exception{
		try{
			if(orderFactory ==  null){
				synchronized(OrderFactory.class){
					if(orderFactory ==  null){
						orderFactory = new OrderFactory();
					}
				}
			}
			
			return orderFactory;
		}
		catch(Exception ex){
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
		try{
			int id = rs.getInt("id");
			String referenceNo = rs.getString("referenceNo");
			String requestRefNo = rs.getString("requestRefNo");
			OrderType type = OrderType.valueOf(rs.getInt("type"));
			OrderStatus status = OrderStatus.valueOf(rs.getInt("status"));
			long buyerId = rs.getLong("buyerId");
			long sellerId = rs.getLong("sellerId");
			double amount = rs.getDouble("amount");
			
			Order order = new Order(id , referenceNo , requestRefNo , type , status , buyerId , sellerId , amount);
			return order;
		}
		catch(Exception ex){
			throw new FactoryException();
		}
	}

	@Override
	public Order createFromJson(String json) throws Exception {
		try{
			Order order = null;
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			JSONObject jo = (JSONObject)obj;
			//order = Order.deserializeFromJson(jo);
			return order;
		}
		catch(ParseException ex){
			throw ex;
		}
	}

}
