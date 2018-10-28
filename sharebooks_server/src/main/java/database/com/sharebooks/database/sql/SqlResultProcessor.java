package com.sharebooks.database.sql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
//import com.sharebooks.coreEntities.*;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.entity.Entity;
import com.sharebooks.factory.entityFactory.EntityFactory;
import com.sharebooks.sources.FactorySource;


public class SqlResultProcessor {
	private static SqlResultProcessor resultProcessor = new SqlResultProcessor();
	
	private SqlResultProcessor(){
		
	}
	
	public static SqlResultProcessor getInstance(){
		return resultProcessor;
	}
	
	public List<? extends Entity> process(ResultSet rs , EntityType entityType) throws SQLException , Exception{
		try{
			List<Entity> entityList = new ArrayList<Entity>();
			EntityFactory<? extends Entity> factory = FactorySource.getEntityFactory(entityType.desc());
			
			while(rs.next()){
				entityList.add(factory.createFromResultSet(rs));
			}
			return entityList;
		}
		catch(SQLException ex){
			throw ex;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
//	private List<? extends Entity> getArrayListObject(EntityType entityType){
//		List<? extends Entity> list = null;
//		if(EntityType.BOOK.equals(entityType)){
//			list = new ArrayList<Book>();
//		}
//		else if(EntityType.USER.equals(entityType)){
//			list = new ArrayList<User>();
//		}
//		return list;
//	}
}
