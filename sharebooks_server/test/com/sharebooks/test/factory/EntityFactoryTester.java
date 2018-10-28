package com.sharebooks.test.factory;

import com.sharebooks.coreEntities.*;
import com.sharebooks.factory.entityFactory.*;
import com.sharebooks.test.AbstractTester;

public class EntityFactoryTester extends AbstractTester {
	
	
	public static void main(String[] args){
		EntityFactoryTester factorytester = new EntityFactoryTester();
		factorytester.test();
	}
	
	public void test(){
		try {
			BookFactoryTester bookFactoryTester = new BookFactoryTester(BookFactory.getInstance());
			bookFactoryTester.test();
			
			UserFactoryTester userFactoryTester = new UserFactoryTester(UserFactory.getInstance());
			userFactoryTester.test();
		} catch (Exception ex) {
			display(ex.getMessage());
		}
	}
	
	
	
	//FactoryTester inner classes for testing different entity factories
	
	//BookFactoryTester
	private class BookFactoryTester extends AbstractTester{
		private EntityFactory<Book> factory;
		
		private BookFactoryTester(){
			//nothing
		}
		
		private BookFactoryTester(EntityFactory<Book> factory){
			this.factory = factory;
		}

		public void test() {
			test1();
			test2();
		}
		
		//test for getting object from json
		private void test1(){
			display("Test1 in BookFactoryTester");
			try{
				String json = "{\"id\":2,\"title\":\"Physics\",\"authorName\":\"H.C Verma\",\"category\":\"Science\",\"subcategory\":"
						+ "\"Physics\",\"pages\":235,\"ownerId\":56,\"imgSrc\":\"com.sharebooks.static-server.images.physics.jpg\","
						+ "\"available\":1,\"forBuying\":0,\"forRent\":1,\"buyingAmount\":350,\"rentAmount\":5}";
				Book book = factory.createFromJson(json);
				display(book.toString());
			}
			catch(Exception ex){
				display("Test1 failed");
				display(ex);
			}
		}
		
		private void test2(){
			
		}
	}
	
	//UserFactoryTester
	class UserFactoryTester extends AbstractTester{
		private EntityFactory<User> factory;
		
		private UserFactoryTester(EntityFactory<User> factory){
			this.factory = factory;
		}
		
		public void test() {
			test1();
			test2();
		}
		
		private void test1(){
			display("Test1 in UserFactoryTester");
			try{
				String json = "{\"id\":1,\"username\":\"lakshyasinghal333@gmail.com\",\"password\":\"champion1!\","
						+ "\"name\":\"Lakshya Singhal\",\"birthday\":\"02/03/1990\",\"age\":28,\"contactNo\":\"8448054935\","
						+ "\"active\":1}";
				User user = factory.createFromJson(json);
				display(user.toString());
			}
			catch(Exception ex){
				display("Test1 in UserFactoryTester failed");
				//display(ex);
				ex.printStackTrace();
			}
		}
		
		private void test2(){
			
		}
	}
}
