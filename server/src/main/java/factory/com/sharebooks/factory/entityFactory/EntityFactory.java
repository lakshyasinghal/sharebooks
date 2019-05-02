package com.sharebooks.factory.entityFactory;

import java.sql.ResultSet;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import com.sharebooks.entity.Entity;

public interface EntityFactory<T extends Entity> {
	
	public T createFromHttpRequest(HttpServletRequest req) throws Exception;
	
	public T createFromResultSet(ResultSet rs) throws Exception;
	
	public T createFromJson(String json) throws Exception;
	
	public List<T> getListFromJson(String json) throws Exception;
}
