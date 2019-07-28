package com.sharebooks.entities.coreEntities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.simple.JSONObject;

import com.sharebooks.entities.coreEntities.enums.AccountType;
import com.sharebooks.entities.coreEntities.enums.Active;
import com.sharebooks.entities.coreEntities.enums.SubscriptionStatus;
import com.sharebooks.entities.entity.CoreEntity;
import com.sharebooks.entities.helperEntities.Preference;
import com.sharebooks.exception.JsonSerializationException;
import com.sharebooks.serialization.json.JsonSerializable;
import com.sharebooks.util.JsonUtility;
import com.sharebooks.util.dateTime.LocalDateTime;

public final class User extends CoreEntity implements JsonSerializable, Comparable<User> {

	private String username;
	private String password;
	private String name;
	private String dob;
	private int age;
	private String address;
	private String city;
	private String state;
	private String pincode;
	private String contactNo;
	private List<Preference> preferences;
	private AccountType accountType;
	private int isRegistered;
	private SubscriptionStatus subscriptionStatus;
	private Active active;

	public User() {

	}

	public User(int id, String uid, String username, String password, String name, String dob, int age, String address,
			String city, String state, String pincode, String contactNo, List<Preference> preferences,
			AccountType accountType, int isRegistered, SubscriptionStatus subscriptionStatus, Active active,
			LocalDateTime creationTime, LocalDateTime lastModificationTime) {
		super(id, uid, creationTime, lastModificationTime);
		this.username = username;
		this.password = password;
		this.name = name;
		this.dob = dob;
		this.age = age;
		this.address = address;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.contactNo = contactNo;
		this.preferences = new ArrayList<Preference>();
		if (preferences != null) {
			this.preferences.addAll(preferences); // to make it immutable
		}
		this.accountType = accountType != null ? accountType : AccountType.UNREGISTERED;
		this.isRegistered = isRegistered;
		this.subscriptionStatus = subscriptionStatus != null ? subscriptionStatus : SubscriptionStatus.PENDING;
		this.active = active;
	}

	public int compareTo(User user) {
		return uid.compareTo(user.uid);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void serializeAsJson(JSONObject jo) throws JsonSerializationException {
		try {
			super.serializeAsJson(jo);
			jo.put("username", username);
			jo.put("password", password);
			jo.put("name", name);
			jo.put("dob", dob);
			jo.put("age", age);
			jo.put("address", address);
			jo.put("city", city);
			jo.put("state", state);
			jo.put("pincode", pincode);
			jo.put("contactNo", contactNo);
			jo.put("preferences", JsonUtility.getJsonArrayFromList(preferences));
			jo.put("accountType", accountType.id());
			jo.put("isRegistered", isRegistered);
			jo.put("subscriptionStatus", subscriptionStatus.id());
			jo.put("active", active.id());
		} catch (Exception ex) {
			throw new JsonSerializationException(ex.getMessage());
		}
	}

	public User cloneWithNewPassword(String password) {
		return new User(this.id, this.uid, this.username, password, this.name, this.dob, this.age, this.address,
				this.city, this.state, this.pincode, this.contactNo, this.preferences, this.accountType,
				this.isRegistered, this.subscriptionStatus, this.active, this.creationTime, this.lastModificationTime);
	}

	// toString method
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("id:" + id + "\n");
		builder.append("uid:" + uid + "\n");
		builder.append("username:" + username + "\n");

		return builder.toString();
	}

	// will return a map representing the user
	// will be mostly used when inserting new user or updating existing one into
	// database
	public Map<String, Object> map() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		// putting super object fields into map
		Map<String, Object> superMap = super.map();
		map.putAll(superMap);

		map.put("username", username);
		map.put("password", password);
		map.put("name", name);
		map.put("dob", dob);
		map.put("age", age);
		map.put("address", address);
		map.put("city", city);
		map.put("state", state);
		map.put("pincode", pincode);
		map.put("contactNo", contactNo);
		map.put("preferences", JsonUtility.getJsonArrayFromList(preferences).toString());
		map.put("accountType", accountType.id());
		map.put("isRegistered", isRegistered);
		map.put("subscriptionStatus", subscriptionStatus.id());
		map.put("active", active.id());
		return map;
	}

	public String uid() {
		return uid;
	}

	public String username() {
		return username;
	}

	public String password() {
		return password;
	}

	public String name() {
		return name;
	}

	public String dob() {
		return dob;
	}

	public int age() {
		return age;
	}

	public String address() {
		return address;
	}

	public String city() {
		return city;
	}

	public String state() {
		return state;
	}

	public String pincode() {
		return pincode;
	}

	public String contactNo() {
		return contactNo;
	}

	public List<Preference> preferences() {
		List<Preference> preferences = new ArrayList<Preference>();
		preferences.addAll(this.preferences);
		return preferences;
	}

	public AccountType accountType() {
		return accountType;
	}

	public int isRegistered() {
		return isRegistered;
	}

	public SubscriptionStatus subscriptionStatus() {
		return subscriptionStatus;
	}

	public Active active() {
		return active;
	}
}
