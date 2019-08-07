package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sharebooks.entities.helperEntities.State;
import com.sharebooks.util.JsonUtility;

public class StateFactory implements EntityFactory<State> {
	private static StateFactory stateFactory;

	private StateFactory() {
		// nothing goes here
	}

	public static StateFactory getInstance() throws Exception {
		try {
			if (stateFactory == null) {
				synchronized (StateFactory.class) {
					if (stateFactory == null) {
						stateFactory = new StateFactory();
					}
				}
			}
			return stateFactory;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public State createFromResultSet(ResultSet rs) throws Exception {
		int id = rs.getInt("id");
		String name = rs.getString("name");

		return new State(id, name);
	}

	@Override
	public State createFromHttpRequest(HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public State createFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<State> getListFromJson(String json) throws Exception {
		JSONArray array = JsonUtility.getJsonArrayFromJsonString(json);
		List<State> list = new ArrayList<State>();
		for (int i = 0, l = array.size(); i < l; i++) {
			JSONObject jo = (JSONObject) array.get(i);
			list.add(createFromJsonObject(jo));
		}
		return list;
	}

	@Override
	public State createFromMongoDocument(Document doc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private State createFromJsonObject(JSONObject jo) {
		int id = (int) (long) jo.get("id");
		String name = (String) jo.get("name");

		return new State(id, name);
	}

}
