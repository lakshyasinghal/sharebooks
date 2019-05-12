package com.sharebooks.factory.dtoFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sharebooks.dtos.ResultDTO;
import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.User;

public class ResultDTOFactory implements DTOFactory<ResultDTO>{
	private static ResultDTOFactory instance = null;
	
	private ResultDTOFactory(){
		
	}
	
	public static ResultDTOFactory getInstance(){
		synchronized(ResultDTOFactory.class){
			if(instance==null){
				instance = new ResultDTOFactory();
			}
		}
		
		return instance;
	}
		
	public List<ResultDTO> getDTOsFromMap(Map<Book,User> resultMap){
		List<ResultDTO> resultDTOs = new ArrayList<ResultDTO>();
		if(resultMap==null){
			return null;
		}
		for(Book book: resultMap.keySet()){
			User user = resultMap.get(book);
			resultDTOs.add(new ResultDTO(user,book));
		}
		return null;
	}
	
	
	public ResultDTO get(User user, Book book){
		return new ResultDTO(user,book);
	}
}
