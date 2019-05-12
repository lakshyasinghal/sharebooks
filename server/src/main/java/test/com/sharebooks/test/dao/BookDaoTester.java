package com.sharebooks.test.dao;

import java.util.List;

import com.sharebooks.dao.generic.BookDao;
import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.sources.DaoSource;
import com.sharebooks.test.AbstractTester;
import com.sharebooks.test.util.DummyEntitySource;

public class BookDaoTester extends AbstractTester{
	//private BookDao dao;
	
	public BookDaoTester(){
		//this.dao = dao;
	}
	
	public static void main(String[] args){
		//BookDao dao = (BookDao)DaoSource.getDao("book");
		BookDaoTester tester = new BookDaoTester();
		tester.test();
	}
	
	
	public void test(){
		initializeApp();
		test1();
		//test2();
		//test3();
		//test4();
	}
	

	//test for fetching books
	public void test1(){
		try{
			BookDao dao = (BookDao)DaoSource.getDao("book");
			List<Book> books = dao.getAllBooks();
			if(books != null && books.size()>0){
				display("Test1 successful");
				newline();
				for(Book book:books){
					display(book);
					newline();
				}
			}
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//get book by id
	public void test2(){
		try{
			BookDao dao = (BookDao)DaoSource.getDao("book");
			Book book = dao.getBook("");
			display(book);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	//inserting book
	public void test3(){
		try{
			BookDao dao = (BookDao)DaoSource.getDao("book");
			List<Book> books = DummyEntitySource.getBooks();
			boolean success = false;
			for(Book book:books){
				success = dao.createBook(book);
			}
			display(success);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	
	//deleting book by id
	public void test4(){
		try{
			BookDao dao = (BookDao)DaoSource.getDao("book");
			boolean success = dao.deleteBook("");
			display(success);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
