package com.sharebooks.test.cache;

import java.util.List;
import com.sharebooks.cache.*;
import com.sharebooks.cache.lruCache.LRUCache;
import com.sharebooks.coreEntities.*;
import com.sharebooks.test.AbstractTester;
import com.sharebooks.test.util.DummyEntitySource;

public class LRUCacheTester extends AbstractTester {
	
	
	public static void main(String[] args){
		LRUCacheTester tester = new LRUCacheTester();
		tester.test();
	}
	
	
	public void test(){
		//test1();
		test2();
	}
	
	
	
	public void test1(){
		try{
			DynamicCache<Book> dynamicCache = new LRUCache<Book>();
			dynamicCache.init(5);
			Cache<Book> cache = dynamicCache;
			List<Book> books = DummyEntitySource.getBooks();
			
			Book book1 = books.get(0);
			Book book2 = books.get(1);
			Book book3 = books.get(2);
			Book book4 = books.get(3);
			Book book5 = books.get(4);
			Book book6 = books.get(5);
			Book book7 = books.get(6);
			Book book8 = books.get(7);
			Book book9 = books.get(8);
			Book book10 = books.get(9);
			Book book11 = books.get(10);
			
			cache.insert(book1.id(), book1);
			cache.insert(book2.id() , book2);
			cache.insert(book3.id(), book3);
			cache.insert(book4.id() , book4);
			
			cache.display();
			newline();
			
			
			newline();
			cache.display();
			newline();
			
			cache.insert(book5.id() , book5);
			cache.insert(book6.id() , book6);
			
			newline();
			cache.display();
			newline();
			
			newline();
			cache.display();
			newline();
			
			newline();
			cache.display();
			newline();
			
			cache.insert(7, book7);
			cache.insert(8, book8);
			cache.insert(9, book9);
			cache.insert(10, book10);
			cache.insert(11, book11);
			
			newline();
			cache.display();
			newline();
			
			//display(cache.size());
			newline();
			cache.display();
		}
		catch(Exception ex){
			
		}
	}
	
	
	public void test2(){
		try {
			DynamicCache<Book> dynamicCache = new LRUCache<Book>();
			dynamicCache.init(5);
			Cache<Book> cache = dynamicCache;
			
			List<Book> books = DummyEntitySource.getBooks();
			Book book1 = books.get(2);
			
			cache.insert(book1.id(), book1);
			cache.display();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void test3(){
	
	}
	
}
