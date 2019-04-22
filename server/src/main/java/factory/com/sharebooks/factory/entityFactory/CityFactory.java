package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;

import javax.servlet.http.HttpServletRequest;

import com.sharebooks.helperEntities.City;

public class CityFactory implements EntityFactory<City>{
	private static CityFactory cityFactory;
	
	private CityFactory(){
		//nothing goes here
	}
	
	
	public static CityFactory getInstance() throws Exception{
		try{
			if(cityFactory ==  null){
				synchronized(CityFactory.class){
					if(cityFactory ==  null){
						cityFactory = new CityFactory();
					}
				}
			}
			return cityFactory;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
	
	@Override
	public City createFromResultSet(ResultSet rs) throws Exception {
		
		int id = rs.getInt("id");
		String name = rs.getString("name");
		int stateId = rs.getInt("stateId");
		
		return new City(id,name,stateId);
	}

	@Override
	public City createFromHttpRequest(HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City createFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	
}
