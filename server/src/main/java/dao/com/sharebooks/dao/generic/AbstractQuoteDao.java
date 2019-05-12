package com.sharebooks.dao.generic;

import java.util.ArrayList;
import java.util.List;

import com.sharebooks.entities.coreEntities.Quote;
import com.sharebooks.entities.entity.Entity;

public abstract class AbstractQuoteDao implements QuoteDao{
	
	
	//function for converting entity list into quote list
	protected List<Quote> convertIntoQuoteList(List<Entity> list) throws Exception{
		try{
			List<Quote> quoteList = new ArrayList<Quote>();
			for(Entity entity: list){
				if(entity instanceof Quote){
					quoteList.add((Quote)entity);
				}
				else{
					throw new Exception("Quote list containing some other entity");
				}
			}
			
			return quoteList;
		}
		catch(Exception ex){
			throw ex;
		}
	}
}
