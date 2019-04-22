package com.sharebooks.entity;

import com.sharebooks.serialization.json.JsonSerializable;

public abstract class Entity implements JsonSerializable{
	protected int id;
	
	public Entity(){
		id = -1;
	}
	
	public Entity(int id){
		this.id = id;
	}
	
	public int id(){
		return id;
	}
}
