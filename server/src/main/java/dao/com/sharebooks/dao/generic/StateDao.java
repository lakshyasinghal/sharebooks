package com.sharebooks.dao.generic;

import java.sql.SQLException;
import java.util.List;

import com.sharebooks.entities.helperEntities.State;

public interface StateDao extends Dao {

	public List<State> getStates() throws SQLException, Exception;

	public boolean insert(State state) throws Exception;

	public boolean insert(List<State> states) throws Exception;
}
