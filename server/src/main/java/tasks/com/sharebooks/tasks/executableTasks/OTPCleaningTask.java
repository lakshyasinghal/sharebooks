package com.sharebooks.tasks.executableTasks;

import com.sharebooks.services.entityServices.OTPService;
import com.sharebooks.tasks.runnables.IntervalTask;

public class OTPCleaningTask extends IntervalTask {

	public OTPCleaningTask(long interval) {
		super(interval);
	}

	public void doWork() {
		OTPService.instance().removeStaleOtps();
	}

}
