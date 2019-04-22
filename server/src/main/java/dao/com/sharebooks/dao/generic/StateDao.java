package com.sharebooks.dao.generic;

import java.sql.SQLException;
import java.util.List;
import com.sharebooks.helperEntities.State;

public interface StateDao extends Dao {
	
	public List<State> getStates() throws SQLException,Exception;
}
