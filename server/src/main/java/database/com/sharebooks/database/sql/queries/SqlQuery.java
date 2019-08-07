package com.sharebooks.database.sql.queries;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SqlQuery {
	public String toString();
	public void build();
	
	//this method will return a list of all fields contained in the map which will be a representation of an entity object
	default List<String> fieldsFromMap(Map<String,Object> map){
		Set<String> set = map.keySet();
		Iterator<String> itr = set.iterator();
		List<String> list = new ArrayList<String>();
		while(itr.hasNext()){
			list.add(itr.next());
		}
		return list;
	}
	
	//this method will return a list of all values contained in the map which will be a representation of an entity object
	default List<Object> valuesFromMap(Map<String,Object> map){
		Set<String> set = map.keySet();
		Iterator<String> itr = set.iterator();
		List<Object> list = new ArrayList<Object>();
		while(itr.hasNext()){
			list.add(map.get(itr.next()));
		}
		return list;
	}
}
