package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;


public interface HelperEntityFactory<T extends Object> {
	
	public T createFromResultSet(ResultSet rs) throws Exception;
	
//	public List<T> getListFromJson(String json) throws Exception;
}
