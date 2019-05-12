package com.sharebooks.services.entityServices;

import java.sql.SQLException;
import java.util.List;
import org.apache.log4j.Logger;
import com.sharebooks.cache.StaticCache;
import com.sharebooks.dao.generic.BookCategoryDao;
import com.sharebooks.dao.generic.CityDao;
import com.sharebooks.dao.generic.MiscDao;
import com.sharebooks.dao.generic.StateDao;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.helperEntities.*;
import com.sharebooks.exception.CacheException;
import com.sharebooks.exception.ExceptionType;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.exception.StaticCacheException;
import com.sharebooks.sources.CacheSource;
import com.sharebooks.sources.DaoSource;

/*The MiscService class might be dissolved and separate service classes might be created for state,city,book category etc later on
For the time being using this class to perform fetch operations*/
@SuppressWarnings("unchecked")
public class MiscService extends EntityService {
	// instanceCount varaible will help in replicating the singleton
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(MiscService.class.getName());
	private final StaticCache<State> stateCache = (StaticCache<State>) CacheSource.getCache(EntityType.STATE.desc());
	private final StaticCache<City> cityCache = (StaticCache<City>) CacheSource.getCache(EntityType.CITY.desc());
	private final StaticCache<BookCategory> bookCategoryCache = (StaticCache<BookCategory>) CacheSource
			.getCache(EntityType.BOOK_CATEGORY.desc());
	private final StateDao stateDao = (StateDao) DaoSource.getDao(EntityType.STATE.desc());
	private final CityDao cityDao = (CityDao) DaoSource.getDao(EntityType.CITY.desc());
	private final BookCategoryDao bookCategoryDao = (BookCategoryDao) DaoSource.getDao(EntityType.BOOK_CATEGORY.desc());

	public MiscService() {
		synchronized (MiscService.class) {
			if (instanceCount == 1) {
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}

	public void init() throws Exception {
		initializeStateCache();
		initializeCityCache();
		initializeBookCategoryCache();
	}

	private void initializeStateCache() throws Exception {
		List<State> states = stateDao.getStates();
		stateCache.init(states);
	}

	private void initializeBookCategoryCache() throws Exception {
		List<BookCategory> categories = bookCategoryDao.getBookCategories();
		bookCategoryCache.init(categories);
	}

	private void initializeCityCache() throws Exception {
		List<City> cities = cityDao.getCities();
		cityCache.init(cities);
	}

	public List<State> getStates() throws Exception {
		LOGGER.trace("Entered getStates");
		List<State> states = stateCache.getAll();
		if (states == null) {
			throw new CacheException("State Cache not initialized.");
		}
		return states;
	}

	public List<City> getCities() throws Exception {
		LOGGER.trace("Entered getCities");
		return cityCache.getAll();
	}

	public List<BookCategory> getBookCategories() throws Exception {
		LOGGER.trace("Entered getBookCategories");
		return bookCategoryCache.getAll();
	}
}
