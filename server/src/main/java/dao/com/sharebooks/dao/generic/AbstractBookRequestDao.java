package com.sharebooks.dao.generic;

import java.util.ArrayList;
import java.util.List;
import com.sharebooks.coreEntities.BookRequest;
import com.sharebooks.entity.Entity;

public abstract class AbstractBookRequestDao implements BookRequestDao{
	
	
	//function for converting entity list into book request list
	protected List<BookRequest> convertIntoBookRequestList(List<Entity> list) throws Exception{
		try{
			List<BookRequest> bookRequestList = new ArrayList<BookRequest>();
			for(Entity entity: list){
				if(entity instanceof BookRequest){
					bookRequestList.add((BookRequest)entity);
				}
				else{
					throw new Exception("Book Request list containing some other entity");
				}
			}
			
			return bookRequestList;
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
