package com.sharebooks.dao.generic;

import java.sql.SQLException;
import java.util.List;

import com.sharebooks.entities.helperEntities.City;

public interface CityDao extends Dao {
	
	public List<City> getCities() throws SQLException,Exception;
	
	//public boolean createCity(City city);
}
