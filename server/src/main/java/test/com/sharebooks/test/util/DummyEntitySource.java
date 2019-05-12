package com.sharebooks.test.util;

import java.util.ArrayList;
import java.util.List;

import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.coreEntities.enums.AvailableStatus;

public class DummyEntitySource {
	private static List<Book> books = new ArrayList<Book>();
	private static List<User> users = new ArrayList<User>();
	//private static List<BookRequest> bookRequests = new ArrayList<BookRequest>();
	//private static List<Order> orders = new ArrayList<Order>();
	
	static{
		generateDummyBooks();
		generateDummyUsers();
		generateDummyBookRequests();
		generateDummyOrders();
	}
	
	private static void generateDummyBooks(){
//		Book book1 = new Book(1,null , "Physics I by H.C Verma" , "H.C Verma" , "Science" , "Physics" , 228 , 6 , "physics1.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.NOT_AVAILABLE , AvailableStatus.AVAILABLE , 350 , 4 , null , null);
//		Book book2 = new Book(2,null , "Head First Design Patterns" , "Eric Freeman" , "Computer Science" , "Java" , 654 , 3 , "hfdp.jpg" , AvailableStatus.NOT_AVAILABLE , 
//				AvailableStatus.NOT_AVAILABLE , AvailableStatus.AVAILABLE , 300 , 3, null , null);
//		Book book3 = new Book(3,null , "Head First Java" , "Kathy Sierra & Bert Bates" , "Computer Science" , "Java" , 684 , 2 , "hfj.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.AVAILABLE , AvailableStatus.AVAILABLE , 420 , 5, null , null);
//		Book book4 = new Book(4,null , "Head First C" , "David Griffiths & Dawn Griffiths" , "Computer Science" , "C" , 588 , 5 , "hfc.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.NOT_AVAILABLE , AvailableStatus.AVAILABLE , 690 , 5, null , null);
//		Book book5 = new Book(5,null , "Head First Servlets & JSP" , "Bryan Basham" , "Computer Science" , "Java" , 878 , 2 , "hfsj.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.NOT_AVAILABLE , AvailableStatus.AVAILABLE , 560 , 5, null , null);
//		Book book6 = new Book(6,null , "Head First JS Programming" , "Elisabeth Robson" , "Computer Science" , "Javascript" , 661 , 1 , "hfjs.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.AVAILABLE , AvailableStatus.AVAILABLE , 450 , 3, null , null);
//		Book book7 = new Book(7,null , "Cracking the Coding Interview" , "Gayle LaakMann Mcdowell" , "Computer Science" , "Programming" , 541 , 2 , "ctci.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.NOT_AVAILABLE , AvailableStatus.AVAILABLE , 250 , 7, null , null);
//		Book book8 = new Book(8,null , "SSC-CGL" , "GKP" , "Competetive Exam" , "SSC-CGL" , 234 , 3 , "ssc-cgl.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.AVAILABLE , AvailableStatus.AVAILABLE , 100 , 2, null , null);
//		Book book9 = new Book(9,null , "Physics II by H.C Verma" , "H.C Verma" , "Science" , "Physics" , 237 , 5 , "physics2.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.AVAILABLE , AvailableStatus.AVAILABLE , 150 , 2, null , null);
//		Book book10 = new Book(10,null , "Java The Complete Reference" , "Herbert Schildt" , "Computer Science" , "Java" , 1116 , 4 , "jtcr.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.NOT_AVAILABLE , AvailableStatus.AVAILABLE , 550 , 1, null , null);
//		Book book11 = new Book(11,null , "Word Power Made Easy" , "Norman Lewis" , "Literature" , "Vocabulary" , 686 , 4 , "wpme.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.AVAILABLE , AvailableStatus.AVAILABLE , 50 , 2, null , null);
//		Book book12 = new Book(12,null , "Six Weeks To Words Of Power" , "Wilfred Funk" , "Literature" , "Vocabulary" , 278 , 4 , "swtwop.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.NOT_AVAILABLE , AvailableStatus.AVAILABLE , 60 , 2, null , null);
//		Book book13 = new Book(13,null , "Head First HTML5 Programming" , "Eric Freeman" , "Computer Science" , "HTML5" , 573 , 1 , "hfhp.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.NOT_AVAILABLE , AvailableStatus.AVAILABLE , 450 , 10, null , null);
//		Book book14 = new Book(14,null , "Head First Android Development" , "Dawn Griffiths" , "Computer Science" , "Android" , 698 , 1 , "hfad.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.NOT_AVAILABLE , AvailableStatus.AVAILABLE , 500 , 5, null , null);
//		Book book15 = new Book(15,null , "Mathematics I" , "R.D Sharma" , "Maths" , "Maths" , 568 , 7 , "m1.jpg" , AvailableStatus.AVAILABLE , 
//				AvailableStatus.AVAILABLE , AvailableStatus.AVAILABLE , 200 , 1, null , null);
//		
//		books.add(book1);
//		books.add(book2);
//		books.add(book3);
//		books.add(book4);
//		books.add(book5);
//		books.add(book6);
//		books.add(book7);
//		books.add(book8);
//		books.add(book9);
//		books.add(book10);
//		books.add(book11);
//		books.add(book12);
//		books.add(book13);
//		books.add(book14);
//		books.add(book15);
	}
	
	private static void generateDummyUsers(){
//		User user1 = new User(1, "lakshyasinghal333@gmail.com" , "champion" , "Lakshya Singhal" , "1990-03-02" , 28 , "8448054935" , Active.ACTIVE);
//		User user2 = new User(2, "himanshu_singhal@gmail.com" , "himanshu" , "Himanshu Singhal" , "1993-03-05" , 25 , "8826387794" , Active.ACTIVE);
//		User user3 = new User(3, "anil_poonia@yahoo.in" , "punnu" , "Anil Poonia" , "1991-05-04" , 27 , "7827271882" , Active.ACTIVE);
//		User user4 = new User(4, "mahenderbeniwal24@gmail.com" , "mahendra" , "Mahender Beniwal" , "1920-10-08" , 26 , "9893647638" , Active.ACTIVE);
//		User user5 = new User(5, "sher_mohammad@gmail.com" , "shera" , "Sher Mohammad" , "1993-05-23" , 25 , "8637282923" , Active.ACTIVE);
//		User user6 = new User(6, "nikhilbansal20@gmail.com" , "bansi" , "Nikhil Bansal" , "1990-02-27" , 28 , "9484837829" , Active.ACTIVE);
//		User user7 = new User(7, "harish_vishnoi@gmail.com" , "haria" , "Harish Bishnoi" , "1991-04-08" , 27 , "8725637292" , Active.ACTIVE);
//		
//		users.add(user1);
//		users.add(user2);
//		users.add(user3);
//		users.add(user4);
//		users.add(user5);
//		users.add(user6);
//		users.add(user7);
	}
	
	private static void generateDummyBookRequests(){
		
	}

	private static void generateDummyOrders(){
	
	}
	
	
	public static List<Book> getBooks(){
		List<Book> newBooks = new ArrayList<Book>(books);
		return newBooks;
	}
	
	public static List<User> getUsers(){
		List<User> newUsers = new ArrayList<User>(users);
		return newUsers;
	}
}
