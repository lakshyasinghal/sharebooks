package com.sharebooks.dao.generic;

import java.util.ArrayList;
import java.util.List;
import com.sharebooks.entity.Entity;
import com.sharebooks.helperEntities.City;

public abstract class AbstractCityDao implements CityDao{
	
	//function for converting entity list into city list
	protected List<City> convertIntoCityList(List<Entity> list) throws Exception{
		try{
			List<City> cityList = new ArrayList<City>();
			for(Entity entity: list){
				if(entity instanceof City){
					cityList.add((City)entity);
				}
				else{
					throw new Exception("City list containing some other entity");
				}
			}
			
			return cityList;
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
