package com.sharebooks.tasks;

public abstract class Task implements Runnable{
	protected float interval;
	
	protected Task(){
		
	}
	
	protected Task(float interval){
		this.interval = interval;
	}
	
	
}
