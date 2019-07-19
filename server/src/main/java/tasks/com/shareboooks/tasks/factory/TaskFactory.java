package com.shareboooks.tasks.factory;

import com.sharebooks.tasks.enums.TaskType;
import com.sharebooks.tasks.runnables.Task;

public class TaskFactory {
	private static TaskFactory instance;

	protected TaskFactory() {
	}

	public static TaskFactory instance() {
		if (instance == null) {
			synchronized (TaskFactory.class) {
				if (instance == null) {
					instance = new TaskFactory();
				}
			}
		}
		return instance;
	}

	public Task create(TaskType taskType) {
		Task instance = null;
		if (taskType == TaskType.OTP_CLEANING) {
			instance = OTPCleaningTaskFactory.instance().create();
		}
		return instance;
	}
}
