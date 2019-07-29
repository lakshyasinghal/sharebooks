package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.bson.Document;
import org.json.simple.JSONArray;
import org.json.simple.parser.JSONParser;

import com.sharebooks.entities.helperEntities.Preference;

public class PreferenceFactory implements EntityFactory<Preference> {
	private static PreferenceFactory preferenceFactory;

	private PreferenceFactory() {
		// nothing goes here
	}

	public static PreferenceFactory getInstance() throws Exception {
		try {
			if (preferenceFactory == null) {
				synchronized (PreferenceFactory.class) {
					if (preferenceFactory == null) {
						preferenceFactory = new PreferenceFactory();
					}
				}
			}
			return preferenceFactory;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public Preference createFromResultSet(ResultSet rs) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Preference createFromHttpRequest(HttpServletRequest req) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Preference createFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	public List<Preference> getListFromJson(String json) throws Exception {
		if (json == null) {
			return null;
		}
		List<Preference> preferences = new ArrayList<Preference>();
		JSONArray jsonArray = (JSONArray) new JSONParser().parse(json);

		for (Object obj : jsonArray) {
			// JSONObject jsonObj = (JSONObject)obj;
			// String category = (String)jsonObj.get("category");
			String category = (String) obj;
			preferences.add(new Preference(category));
		}
		return preferences;
	}

	@Override
	public Preference createFromMongoDocument(Document doc) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

}
