package com.sharebooks.dao.generic;

import java.util.List;

import com.sharebooks.entities.helperEntities.City;

public interface CityDao extends Dao {

	public List<City> getCities() throws Exception;

	public boolean insert(City city) throws Exception;

	public boolean insert(List<City> cities) throws Exception;
}
