package com.sharebooks.dao.generic;

import java.util.ArrayList;
import java.util.List;

import com.sharebooks.entities.entity.Entity;
import com.sharebooks.entities.helperEntities.BookCategory;
import com.sharebooks.entities.helperEntities.City;
import com.sharebooks.entities.helperEntities.State;

public abstract class AbstractMiscDao implements MiscDao{
	
	//function for converting entity list into book category list
	protected List<BookCategory> convertIntoBookCategoryList(List<Entity> list) throws Exception{
		try{
			List<BookCategory> bookCategoryList = new ArrayList<BookCategory>();
			for(Entity entity: list){
				if(entity instanceof BookCategory){
					bookCategoryList.add((BookCategory)entity);
				}
				else{
					throw new Exception("BookCategory list containing some other entity");
				}
			}
			
			return bookCategoryList;
		}
		catch(Exception ex){
			throw ex;
		}
	}
	
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
	
	//function for converting entity list into city list
	protected List<City> convertIntoCityList(List<Entity> list) throws Exception{
		try{
			List<City> cityList = new ArrayList<City>();
			for(Entity entity: list){
				if(entity instanceof City){
					cityList.add((City)entity);
				}
				else{
					throw new Exception("City list containing some other entity");
				}
			}
			
			return cityList;
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
