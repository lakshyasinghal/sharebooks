package com.sharebooks.exception;

public class EmptyCache extends Exception {
	private static final long serialVersionUID = 1L;
	private static final String message = "Empty cache.";

	public EmptyCache() {
		this("");
	}

	public EmptyCache(String cacheName) {
		super(EmptyCache.message + "\n" + cacheName);
	}
}
