package com.sharebooks.factory.dtoFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.sharebooks.coreEntities.Book;
import com.sharebooks.coreEntities.User;
import com.sharebooks.dtos.ResultDTO;

public class ResultDTOFactory implements DTOFactory<ResultDTO>{
	
		
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
	
}
