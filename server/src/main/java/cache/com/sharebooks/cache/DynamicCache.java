package com.sharebooks.cache;

import com.sharebooks.entity.Entity;

public interface DynamicCache<T extends Entity> extends Cache<T> {
	
	public void init(int capacity) throws Exception;
}
