package com.sharebooks.services.entityServices;

import com.sharebooks.exception.ExceptionType;

public abstract class AbstractService {
	
	protected void handleException(Exception ex){
		
	}
	
	//needs to be implemented
	protected void sendExceptionMail(ExceptionType exType , Exception ex){
		
	}
}
