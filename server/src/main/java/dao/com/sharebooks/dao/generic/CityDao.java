package com.sharebooks.dao.generic;

import java.util.List;
import com.sharebooks.helperEntities.City;

public interface CityDao extends Dao {
	
	public List<City> getAllCities();
	
	public boolean createCity(City city);
}
