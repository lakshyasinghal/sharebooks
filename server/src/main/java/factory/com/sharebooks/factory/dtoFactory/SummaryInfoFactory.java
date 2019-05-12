package com.sharebooks.factory.dtoFactory;

import com.sharebooks.dtos.SummaryInfo;
import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.Quote;
import com.sharebooks.entities.coreEntities.User;

public class SummaryInfoFactory implements DTOFactory<SummaryInfo>{
private static SummaryInfoFactory instance = null;
	
	private SummaryInfoFactory(){
		
	}
	
	public static SummaryInfoFactory getInstance(){
		synchronized(SummaryInfoFactory.class){
			if(instance==null){
				instance = new SummaryInfoFactory();
			}
		}
		
		return instance;
	}
	
	
	public SummaryInfo get(Book book, User user, Quote quote){
		return new SummaryInfo(book,user,quote);
	}
}
