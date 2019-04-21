package com.sharebooks.cache;

import com.sharebooks.entity.Entity;

public interface Cache<T extends Entity> {

	public T get(Object key) throws Exception;

	public void insert(Object key , T obj) throws Exception;

	public void delete() throws Exception;
	
	public int size();
	
	public void display();
}
