package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sharebooks.entities.helperEntities.City;
import com.sharebooks.util.JsonUtility;

public class CityFactory implements EntityFactory<City> {
	private static CityFactory cityFactory;

	private CityFactory() {
		// nothing goes here
	}

	public static CityFactory getInstance() throws Exception {
		try {
			if (cityFactory == null) {
				synchronized (CityFactory.class) {
					if (cityFactory == null) {
						cityFactory = new CityFactory();
					}
				}
			}
			return cityFactory;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public City createFromResultSet(ResultSet rs) throws Exception {

		int id = rs.getInt("id");
		String name = rs.getString("name");
		int stateId = rs.getInt("stateId");

		return new City(id, name, stateId);
	}

	@Override
	public City createFromHttpRequest(HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public City createFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<City> getListFromJson(String json) throws Exception {
		JSONArray array = JsonUtility.getJsonArrayFromJsonString(json);
		List<City> list = new ArrayList<City>();
		for (int i = 0, l = array.size(); i < l; i++) {
			JSONObject jo = (JSONObject) array.get(i);
			list.add(createFromJsonObject(jo));
		}
		return list;
	}

	@Override
	public City createFromMongoDocument(Document doc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	private City createFromJsonObject(JSONObject jo) {
		int id = (int) (long) jo.get("id");
		String name = (String) jo.get("name");
		int stateId = (int) (long) jo.get("stateId");

		return new City(id, name, stateId);
	}

}
