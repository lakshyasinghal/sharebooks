package com.sharebooks.jetty;

import java.util.*;

public enum ServerStatus {
	STARTING(1, "Starting"), STARTED(2, "Running"), STOPPING(3, "Stopping"), STOPPED(4, "Stopped");

	private final int id;
	private final String description;

	private ServerStatus(int id, String description) {
		this.id = id;
		this.description = description;
	}

	private static final Map<Integer, ServerStatus> statuses = new HashMap<Integer, ServerStatus>();

	static {
		for (ServerStatus status : ServerStatus.values()) {
			if (statuses.get(status.id) == null) {
				statuses.put(status.id, status);
			} else {
				throw new RuntimeException("Duplicate id: " + status.id);
			}
		}
	}

	public static ServerStatus valueOf(int id) {
		return statuses.get(id);
	}

	public int id() {
		return id;
	}

	public String description() {
		return description;
	}
}
