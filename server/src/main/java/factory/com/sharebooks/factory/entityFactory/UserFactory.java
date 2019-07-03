package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.coreEntities.enums.AccountType;
import com.sharebooks.entities.coreEntities.enums.Active;
import com.sharebooks.entities.helperEntities.Preference;
import com.sharebooks.exception.FactoryException;
import com.sharebooks.util.dateTime.LocalDateTime;

public class UserFactory implements EntityFactory<User> {
	private static UserFactory userFactory;

	private UserFactory() {
		// nothing goes here
	}

	public static UserFactory getInstance() throws Exception {
		try {
			if (userFactory == null) {
				synchronized (UserFactory.class) {
					if (userFactory == null) {
						userFactory = new UserFactory();
					}
				}
			}
			return userFactory;
		} catch (Exception ex) {
			throw ex;
		}
	}

	@Override
	public User createFromHttpRequest(HttpServletRequest req) throws Exception {

		return null;
	}

	@Override
	public User createFromResultSet(ResultSet rs) throws FactoryException, Exception {
		int id = rs.getInt("id");
		String uid = rs.getString("uid");
		String username = rs.getString("username");
		String password = rs.getString("password");
		String name = rs.getString("name");
		String dob = rs.getString("dob");
		int age = rs.getInt("age");
		String address = rs.getString("address");
		String city = rs.getString("city");
		String state = rs.getString("state");
		String pincode = rs.getString("pincode");
		String contactNo = rs.getString("contactNo");
		List<Preference> preferences = getPreferenceListFromJson(rs.getString("preferences"));
		AccountType accountType = AccountType.get(rs.getInt("accountType"));
		Active active = Active.valueOf(rs.getInt("active"));
		String creationTimeStr = (rs.getTimestamp("creationTime")).toString();
		LocalDateTime creationTime = LocalDateTime.buildFromString(creationTimeStr);
		String lastModificationTimeStr = (rs.getTimestamp("lastModificationTime")).toString();
		LocalDateTime lastModificationTime = LocalDateTime.buildFromString(lastModificationTimeStr);

		User user = new User(id, uid, username, password, name, dob, age, address, city, state, pincode, contactNo,
				preferences, accountType, active, creationTime, lastModificationTime);
		return user;
	}

	@Override
	public User createFromJson(String json) throws Exception {
		try {
			JSONParser parser = new JSONParser();
			Object obj = parser.parse(json);
			JSONObject jo = (JSONObject) obj;

			int id = jo.get("id") == null ? -1 : (int) (long) jo.get("id");
			String uid = (String) jo.get("uid");
			String username = (String) jo.get("username");
			String password = (String) jo.get("password");
			String name = (String) jo.get("name");
			String dob = (String) jo.get("dob");
			int age = Integer.parseInt((String) jo.get("age"));
			String address = (String) jo.get("address");
			String city = (String) jo.get("city");
			String state = (String) jo.get("state");
			String pincode = (String) jo.get("pincode");
			String contactNo = (String) jo.get("contactNo");
			// will be null during creation and needs to be handled for modify requests
			List<Preference> preferences = getPreferenceListFromJson((String) jo.get("preferences"));
			AccountType accountType = AccountType.get((int) (long) jo.get("accountType"));
			Active active = jo.get("active") == null ? Active.ACTIVE : Active.valueOf((int) (long) jo.get("active"));
			String creationTimeStr = (String) jo.get("creationTime");
			String lastModificationTimeStr = (String) jo.get("lastModificationTime");

			LocalDateTime creationTime = creationTimeStr == null ? null
					: LocalDateTime.buildFromString(creationTimeStr);
			LocalDateTime lastModificationTime = lastModificationTimeStr == null ? null
					: LocalDateTime.buildFromString(lastModificationTimeStr);

			User user = new User(id, uid, username, password, name, dob, age, address, city, state, pincode, contactNo,
					preferences, accountType, active, creationTime, lastModificationTime);
			return user;
		} catch (ParseException ex) {
			throw ex;
		}
	}

	private List<Preference> getPreferenceListFromJson(String json) throws Exception {
		if (json == null) {
			return null;
		}
		List<Preference> preferences = new ArrayList<Preference>();
		JSONArray jsonArray = (JSONArray) new JSONParser().parse(json);

		for (Object obj : jsonArray) {
			JSONObject jsonObj = (JSONObject) obj;
			String category = (String) jsonObj.get("category");
			preferences.add(new Preference(category));
		}
		return preferences;
	}

	@Override
	public List<User> getListFromJson(String json) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
}
