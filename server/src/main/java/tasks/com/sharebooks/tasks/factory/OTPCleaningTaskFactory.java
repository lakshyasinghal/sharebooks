package com.sharebooks.tasks.factory;

import com.sharebooks.config.appConfig.AppConfig;
import com.sharebooks.config.properties.TasksProperties;
import com.sharebooks.tasks.executableTasks.OTPCleaningTask;
import com.sharebooks.tasks.runnables.Task;

public class OTPCleaningTaskFactory extends TaskFactory {
	private static OTPCleaningTaskFactory instance;

	private OTPCleaningTaskFactory() {
	}

	public static OTPCleaningTaskFactory instance() {
		if (instance == null) {
			synchronized (OTPCleaningTaskFactory.class) {
				if (instance == null) {
					instance = new OTPCleaningTaskFactory();
				}
			}
		}
		return instance;
	}

	public Task create() {
		long interval = Long.parseUnsignedLong(AppConfig.tasksProp(TasksProperties.OTP_CLEANING_TASK_INTERVAL));
		return new OTPCleaningTask(interval);
	}
}
