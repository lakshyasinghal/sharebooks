package com.sharebooks.test.misc;

import com.sharebooks.dateTime.LocalDateTime;
import com.sharebooks.test.AbstractTester;

public class LocalDateTimeTester extends AbstractTester{
	
	
	public static void main(String[] args){
		LocalDateTimeTester tester = new LocalDateTimeTester();
		tester.test();
	}
	
	public void test(){
		test1();
		test2();
		test3();
		test4();
		test5();
		test6();
	}
	
	private void test1(){
		LocalDateTime localDateTime = new LocalDateTime();
		display(localDateTime);
	}
	
	private void test2(){
		LocalDateTime localDateTime = new LocalDateTime(3 , 10 , 2018 , 22 , 25 , 47);
		display(localDateTime);
	}
	
	private void test3(){
		try{
			LocalDateTime localDateTime = LocalDateTime.buildFromString("2018-10-03 22:25:47");
			display(localDateTime);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void test4(){
		try{
			LocalDateTime localDateTime = LocalDateTime.buildFromString("2018-4-3 2:5:47");
			display(localDateTime);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void test5(){
		try{
			LocalDateTime localDateTime = LocalDateTime.buildFromString("2018-4-3 2:5:47.0");
			display(localDateTime);
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
	
	private void test6(){
		try{
			LocalDateTime localDateTime = LocalDateTime.buildFromString("2018-4-3 2:5:47");
			display(localDateTime.serializeAsJson());
		}
		catch(Exception ex){
			ex.printStackTrace();
		}
	}
}
