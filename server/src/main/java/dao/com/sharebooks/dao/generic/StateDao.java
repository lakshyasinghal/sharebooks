package com.sharebooks.dao.generic;

import java.util.List;
import com.sharebooks.helperEntities.State;

public interface StateDao extends Dao {
	
	public List<State> getAllStates();
}
