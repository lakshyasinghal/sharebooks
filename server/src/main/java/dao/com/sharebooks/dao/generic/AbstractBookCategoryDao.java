package com.sharebooks.dao.generic;

import java.util.ArrayList;
import java.util.List;
import com.sharebooks.entity.Entity;
import com.sharebooks.helperEntities.BookCategory;

public abstract class AbstractBookCategoryDao implements BookCategoryDao{
	
	//function for converting entity list into bookCategory list
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
}
