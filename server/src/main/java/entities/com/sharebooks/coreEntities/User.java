package com.sharebooks.coreEntities;

import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.json.simple.JSONObject;

import com.sharebooks.coreEntities.enums.Active;
import com.sharebooks.dateTime.LocalDateTime;
import com.sharebooks.entity.Entity;
import com.sharebooks.exception.JsonSerializationException;
//import com.sharebooks.helperEntities.Address;

public final class User extends Entity {
	
	private String uid;  //Will be generated using UUID class and will be used as primary key
	private String username;
	private String password;
	private String name;
	private String dob;
	private int age;
	//private Address address;
	private String contactNo;
	private Active active;
	
	public User(){
		
	}
	
	public User(long id , String username , String password , String name , String dob , int age , String contactNo , Active active){
		super(id);
		this.uid = UUID.randomUUID().toString();
		this.username = username;
		this.password = password;
		this.name = name;
		this.dob = dob;
		this.age = age;
		this.contactNo = contactNo;
		this.active = active;
	}
	
	public User(long id ,String uid, String username , String password , String name , String dob , int age , String contactNo , Active active){
		super(id);
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.name = name;
		this.dob = dob;
		this.age = age;
		this.contactNo = contactNo;
		this.active = active;
	}
	
	
	@SuppressWarnings("unchecked")
	@Override
	public String serializeAsJson() throws JsonSerializationException {
		try{
			JSONObject jo = new JSONObject();
			jo.put("username", username);
			jo.put("password", password);
			jo.put("name", name);
			jo.put("dob", dob);
			jo.put("age", age);
			//jo.put("address", address.serializeAsJson());
			jo.put("contactNo", contactNo);
			jo.put("active", active);
			
			return jo.toString();
		}
		catch(Exception ex){
			throw new JsonSerializationException(ex.getMessage());
		}
	}
	
	public User cloneWithNewPassword(String password){
		return new User(this.id, this.username, password, this.name, this.dob, this.age, this.contactNo, this.active);
	}
	
	
	//toString method
	public String toString(){
		StringBuilder builder = new StringBuilder();
		builder.append("id:" + id + "\n");
		builder.append("uid:" + uid + "\n");
		builder.append("username:" + username + "\n");
		
		return builder.toString();
	}
	
	
	//will return a list of all the book property names
	//will be mostly used when inserting new user into database
	public List<String> fields(){
		List<String> fields = new LinkedList<String>();
		fields.add("id");
		fields.add("uid");
		fields.add("username");
		fields.add("password");
		fields.add("name");
		fields.add("dob");
		fields.add("age");
		fields.add("contactNo");
		fields.add("active");
		fields.add("creationTime");
		fields.add("lastModificationTime");
		
		return fields;
	}
	
	//will return a list of all the book property values
	//will be mostly used when inserting new book into database
	public List<Object> values(){
		List<Object> values = new LinkedList<Object>();
		values.add(id);
		values.add(uid);
		values.add(username);
		values.add(password);
		values.add(name);
		values.add(dob);
		values.add(age);
		values.add(contactNo);
		values.add(active.id());
		values.add(creationTime.toString());
		values.add(lastModificationTime.toString());
		
		return values;
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
	
	public String contactNo(){
		return contactNo;
	}
	
	public Active active(){
		return active;
	}
}
