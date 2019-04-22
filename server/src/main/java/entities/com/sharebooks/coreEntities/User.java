package com.sharebooks.coreEntities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.json.simple.JSONObject;
import com.sharebooks.coreEntities.enums.Active;
import com.sharebooks.dateTime.LocalDateTime;
import com.sharebooks.entity.CoreEntity;
import com.sharebooks.exception.JsonSerializationException;
//import com.sharebooks.helperEntities.Address;
import com.sharebooks.helperEntities.Preference;
import com.sharebooks.util.JsonUtils;

public final class User extends CoreEntity {
	
	private String uid;  //Will be generated using UUID class and will be used as primary key
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
	private Active active;
	
	public User(){
		
	}

	
	public User(int id ,String uid, String username , String password , String name , String dob , int age, String address, String city, 
				String state, String pincode, String contactNo, List<Preference> preferences, Active active, LocalDateTime creationTime, LocalDateTime lastModificationTime){
		super(id,creationTime,lastModificationTime);
		if(uid==null){
			this.uid = UUID.randomUUID().toString();
		}
		else{
			this.uid = uid;
		}
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
		if(preferences!=null){
			this.preferences.addAll(preferences);   //to make it immutable
		}
		this.active = active;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String serializeAsJson() throws JsonSerializationException {
		try{
			JSONObject jo = new JSONObject();
			jo.put("id", id);
			jo.put("uid", uid);
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
			jo.put("preferences", JsonUtils.getSerializedList(preferences));
			jo.put("active", active.id());
			jo.put("creationTime", creationTime.toString());
			jo.put("lastModificationTime", lastModificationTime.toString());
			return jo.toString();
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}
	
	public User cloneWithNewPassword(String password){
		return new User(this.id, this.uid, this.username, password, this.name, this.dob, this.age, this.address, this.city, this.state, this.pincode, this.contactNo, this.preferences, this.active, this.creationTime, this.lastModificationTime);
	}
	
	
	//toString method
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("id:" + id + "\n");
		builder.append("uid:" + uid + "\n");
		builder.append("username:" + username + "\n");
		
		return builder.toString();
	}
	
	
	//will return a map representing the user
	//will be mostly used when inserting new user or updating existing one into database
	public Map<String,Object> map() throws Exception{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("id",id);
		map.put("uid",uid);
		map.put("username",username);
		map.put("password",password);
		map.put("name",name);
		map.put("dob",dob);
		map.put("age",age);
		map.put("address",address);
		map.put("city",city);
		map.put("state",state);
		map.put("pincode",pincode);
		map.put("contactNo",contactNo);
		map.put("preferences", JsonUtils.getSerializedList(preferences));
		map.put("active",active.id());
		map.put("creationTime",creationTime.toString());
		map.put("lastModificationTime",lastModificationTime.toString());
		
		return map;
	}

	
	public String uid(){
		return uid;
	}
	
	public String username(){
		return username;
	}
	
	public String password(){
		return password;
	}
	
	public String name(){
		return name;
	}
	
	public String dob(){
		return dob;
	}
	
	public int age(){
		return age;
	}
	
	public String address(){
		return address;
	}
	
	public String city(){
		return city;
	}
	
	public String state(){
		return state;
	}
	
	public String pincode(){
		return pincode;
	}
	
	public String contactNo(){
		return contactNo;
	}
	
	public List<Preference> preferences(){
		List<Preference> preferences = new ArrayList<Preference>();
		preferences.addAll(this.preferences);
		return preferences;
	}
	
	public Active active(){
		return active;
	}
}
