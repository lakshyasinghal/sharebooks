package com.sharebooks.dao.generic;

import java.util.ArrayList;
import java.util.List;

import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.entity.Entity;

public abstract class AbstractBookDao implements BookDao{
	
	
	//function for converting entity list into book list
	protected List<Book> convertIntoBookList(List<Entity> list) throws Exception{
		try{
			List<Book> bookList = new ArrayList<Book>();
			for(Entity entity: list){
				if(entity instanceof Book){
					bookList.add((Book)entity);
				}
				else{
					throw new Exception("Book list containing some other entity");
				}
			}
			
			return bookList;
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
