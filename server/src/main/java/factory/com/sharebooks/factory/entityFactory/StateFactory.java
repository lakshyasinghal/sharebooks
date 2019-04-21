package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.List;

import org.json.simple.JSONArray;

import com.sharebooks.helperEntities.State;

public class StateFactory implements HelperEntityFactory<State>{

	@Override
	public State createFromResultSet(ResultSet rs) throws Exception {
		
		int id = rs.getInt("id");
		String name = rs.getString("name");
		
		return new State(id,name);
	}
	
}
