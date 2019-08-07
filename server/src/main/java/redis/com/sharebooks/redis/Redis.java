package com.sharebooks.redis;

public interface Redis {

	public abstract long set(String key, String value) throws Exception;

	public abstract String get(String key) throws Exception;

	public abstract long push(String value) throws Exception;

	public abstract String pop() throws Exception;

	public abstract long add(String value) throws Exception;

	public abstract long isMember(String value) throws Exception;
}
