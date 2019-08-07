package com.sharebooks.dao.redis;

import java.util.List;

import com.sharebooks.dao.enums.RedisKey;
import com.sharebooks.dao.generic.StateDao;
import com.sharebooks.entities.helperEntities.State;
import com.sharebooks.factory.entityFactory.StateFactory;
import com.sharebooks.util.JsonUtility;

public class StateRedisDao extends AbstractRedisDao implements StateDao {
	private static final StateRedisDao instance = new StateRedisDao();

	private StateRedisDao() {
	}

	public static StateRedisDao instance() {
		return instance;
	}

	@Override
	public List<State> getStates() throws Exception {
		String json = super.get(RedisKey.STATES.desc());
		List<State> states = StateFactory.getInstance().getListFromJson(json);
		return states;
	}

	@Override
	public boolean insert(State state) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean insert(List<State> states) throws Exception {
		String json = JsonUtility.getJsonArrayFromList(states).toJSONString();
		boolean success = super.insert(RedisKey.STATES.desc(), json);
		return success;
	}
}
