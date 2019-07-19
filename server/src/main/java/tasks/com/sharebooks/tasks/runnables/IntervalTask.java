package com.sharebooks.tasks.runnables;

public abstract class IntervalTask implements Task {

	private volatile long interval; // value will be in milli seconds
	private volatile boolean keepRunning = true;

	protected IntervalTask(long interval) {
		this.interval = interval;
	}

	public void run() {
		while (keepRunning) {
			doWork();
			try {
				Thread.sleep(interval);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	protected void keepRunning(boolean keepRunning) {
		this.keepRunning = keepRunning;
	}
}
