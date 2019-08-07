package com.sharebooks.dao.redis;

import java.util.List;

import com.sharebooks.dao.enums.RedisKey;
import com.sharebooks.dao.generic.CityDao;
import com.sharebooks.entities.helperEntities.City;
import com.sharebooks.factory.entityFactory.CityFactory;
import com.sharebooks.util.JsonUtility;

public class CityRedisDao extends AbstractRedisDao implements CityDao {
	private static final CityRedisDao instance = new CityRedisDao();

	private CityRedisDao() {
	}

	public static CityRedisDao instance() {
		return instance;
	}

	@Override
	public List<City> getCities() throws Exception {
		String json = super.get(RedisKey.CITIES.desc());
		List<City> cities = CityFactory.getInstance().getListFromJson(json);
		return cities;
	}

	@Override
	public boolean insert(City city) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(List<City> cities) throws Exception {
		String citiesStr = JsonUtility.getJsonArrayFromList(cities).toJSONString();
		boolean success = super.insert(RedisKey.CITIES.desc(), citiesStr);
		return success;
	}

}
