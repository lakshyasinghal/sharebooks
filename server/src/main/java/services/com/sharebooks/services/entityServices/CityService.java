package com.sharebooks.services.entityServices;

import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.CityDao;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.helperEntities.City;
import com.sharebooks.exception.EmptyCache;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.sources.DaoSource;

public class CityService extends EntityService {
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(CityService.class.getName());
	private final CityDao dao = (CityDao) DaoSource.getDao(EntityType.CITY.desc());
	private final CityDao redisDao = (CityDao) DaoSource.getRedisDao(EntityType.CITY.desc());

	public CityService() {
		synchronized (CityService.class) {
			if (instanceCount == 1) {
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}

	public boolean putCitiesInCache() throws Exception {
		LOGGER.trace("Entered putCitiesInCache");
		List<City> cities = dao.getCities();
		boolean success = redisDao.insert(cities);
		return success;
	}

	public List<City> getCities() throws Exception {
		LOGGER.trace("Entered getCities");
		List<City> cities = null;
		cities = redisDao.getCities();
		if (cities == null || cities.size() == 0) {
			throw new EmptyCache("City cache.");
		}
		return cities;
	}
}
