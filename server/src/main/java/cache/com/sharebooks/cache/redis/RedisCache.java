package com.sharebooks.cache.redis;

import com.sharebooks.cache.DynamicCache;
import com.sharebooks.entity.Entity;


public class RedisCache<T extends Entity> implements DynamicCache<T> {

	@Override
	public void init(int capacity) throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public T get(Object key) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(Object key, T t) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete() throws Exception {
		// TODO Auto-generated method stub
		
	}


	@Override
	public int size() {
		return 0;
	}
	
	public void display(){
		
	}

}
