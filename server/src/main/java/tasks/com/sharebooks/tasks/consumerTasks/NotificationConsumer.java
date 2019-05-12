package com.sharebooks.tasks.consumerTasks;

import java.util.Queue;

import com.sharebooks.tasks.Task;
import com.sharebooks.tasks.processors.Processor;

public class NotificationConsumer extends Task{
	private Processor processor;
	private Queue queue;
	
	public NotificationConsumer(Processor processor, Queue queue){
		this.processor = processor;
		this.queue = queue;
	}

	@Override
	public void run() {
		
	}

}
