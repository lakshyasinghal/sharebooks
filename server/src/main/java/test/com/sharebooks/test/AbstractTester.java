package com.sharebooks.test;

import com.sharebooks.init.AppInitializer;
import com.sharebooks.test.util.DummyPropertySource;

public abstract class AbstractTester{
	
	protected abstract void test(); 
	
	protected void initializeApp(){
		AppInitializer appInitializer = new AppInitializer();
		appInitializer.initialize(DummyPropertySource.getPropertyMap());
	}
	
	protected void display(Object obj){
		System.out.println();
		if(obj==null){
			System.out.println("null");
		}
		else{
			System.out.println(obj.toString());
		}
	}
	
	protected void newline(){
		System.out.println();
	}
}