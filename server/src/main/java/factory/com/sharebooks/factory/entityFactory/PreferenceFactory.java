//package com.sharebooks.factory.entityFactory;
//
//import java.sql.ResultSet;
//import java.util.ArrayList;
//import java.util.List;
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import com.sharebooks.helperEntities.Preference;
//
//
//public class PreferenceFactory implements HelperEntityFactory<Preference>{
//
//	@Override
//	public Preference createFromResultSet(ResultSet rs) throws Exception {
//		// TODO Auto-generated method stub
//		return null;
//	}
//
//	@Override
//	public List<Preference> getListFromJson(String json) throws Exception {
//		List<Preference> preferences = new ArrayList<Preference>();
//		JSONArray jsonArray = (JSONArray) new JSONParser().parse(json);
//        
//		for(Object obj: jsonArray){
//			JSONObject jsonObj = (JSONObject)obj;
//			String category = (String)jsonObj.get("category");
//			preferences.add(new Preference(category));
//		}
//		return preferences;
//	}
//
//}
