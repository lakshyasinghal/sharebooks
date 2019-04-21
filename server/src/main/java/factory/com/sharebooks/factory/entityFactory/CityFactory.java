package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;

import com.sharebooks.helperEntities.City;

public class CityFactory implements HelperEntityFactory<City>{

	@Override
	public City createFromResultSet(ResultSet rs) throws Exception {
		
		int id = rs.getInt("id");
		String name = rs.getString("name");
		int stateId = rs.getInt("stateId");
		
		return new City(id,name,stateId);
	}
	
}
