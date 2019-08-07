package com.sharebooks.services.entityServices;

import java.util.List;

import org.apache.log4j.Logger;

import com.sharebooks.dao.generic.StateDao;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.helperEntities.State;
import com.sharebooks.exception.EmptyCache;
import com.sharebooks.exception.MultipleInstanceException;
import com.sharebooks.sources.DaoSource;

public class StateService {
	private static int instanceCount = 0;
	private static final Logger LOGGER = Logger.getLogger(StateService.class.getName());
	private final StateDao dao = (StateDao) DaoSource.getDao(EntityType.STATE.desc());
	private final StateDao redisDao = (StateDao) DaoSource.getRedisDao(EntityType.STATE.desc());

	public StateService() {
		synchronized (StateService.class) {
			if (instanceCount == 1) {
				throw new MultipleInstanceException();
			}
			instanceCount++;
		}
	}

	public boolean putStatesInCache() throws Exception {
		LOGGER.trace("Entered putStatesInCache");
		List<State> states = dao.getStates();
		boolean success = redisDao.insert(states);
		return success;
	}

	public List<State> getStates() throws Exception {
		LOGGER.trace("Entered getStates");
		List<State> states = null;
		states = redisDao.getStates();
		if (states == null || states.size() == 0) {
			throw new EmptyCache("State cache.");
		}
		return states;
	}
}
