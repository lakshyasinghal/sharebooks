package com.sharebooks.dao.generic;

import java.util.ArrayList;
import java.util.List;

import com.sharebooks.entities.entity.Entity;
import com.sharebooks.entities.helperEntities.State;

public abstract class AbstractStateDao implements StateDao{
	
	
	//function for converting entity list into state list
	protected List<State> convertIntoStateList(List<Entity> list) throws Exception{
		try{
			List<State> stateList = new ArrayList<State>();
			for(Entity entity: list){
				if(entity instanceof State){
					stateList.add((State)entity);
				}
				else{
					throw new Exception("State list containing some other entity");
				}
			}
			
			return stateList;
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
