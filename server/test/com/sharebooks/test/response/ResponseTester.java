package com.sharebooks.test.response;

import java.util.*;
import com.sharebooks.coreEntities.Book;
import com.sharebooks.factory.misc.ResponseFactory;
import com.sharebooks.sources.FactorySource;
import com.sharebooks.test.AbstractTester;
import com.sharebooks.test.util.DummyEntitySource;
import com.sharebooks.response.Response;
import com.sharebooks.response.Status;

public class ResponseTester extends AbstractTester{
	
	
	public static void main(String[] args){
		ResponseTester tester = new ResponseTester();
		tester.test();
	}
	
	public void test(){
		initializeApp();
		JsonResponseTester jsonResponseTester = new JsonResponseTester();
		jsonResponseTester.test();
	}
	
	
	private class JsonResponseTester extends AbstractTester{
		private ResponseFactory factory = FactorySource.getResponseFactory();
		
		public void test(){
			test1();
			test2();
		}
		
		//testing json for entity list
		private void test1(){
			try {
				List<Book> books = DummyEntitySource.getBooks();
				List<Book> tempBooks = new ArrayList<Book>();
				tempBooks.add(books.get(0));tempBooks.add(books.get(1));tempBooks.add(books.get(2));
				display(tempBooks.get(0).serializeAsJson());
				boolean isSuccessful = true;
				int statusCode = Status.FETCH_ENTITIES_SUCCESSFUL.id();
				int errorCode = -1;
				Response response = factory.getJsonResponse(isSuccessful, statusCode, errorCode, tempBooks);
				display(response.process());
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
		//testing for empty list
		private void test2(){
			try{
				List<Book> tempBooks = null;
				boolean isSuccessful = true;
				int statusCode = Status.FETCH_ENTITIES_SUCCESSFUL.id();
				int errorCode = -1;
				Response response = factory.getJsonResponse(isSuccessful, statusCode, errorCode, tempBooks);
				display(response.process());
			}
			catch(Exception e){
				e.printStackTrace();
			}
		}
		
		
	}
}
