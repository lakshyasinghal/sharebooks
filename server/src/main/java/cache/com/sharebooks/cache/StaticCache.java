package com.sharebooks.cache;

import java.util.List;
import com.sharebooks.entity.Entity;

public interface StaticCache<T extends Entity> extends Cache<T> {
	
	public void init(List<T> entityList) throws Exception;
	
	public List<T> getAll() throws Exception; 
}
