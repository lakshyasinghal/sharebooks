package com.sharebooks.redis.pool;

import com.sharebooks.redis.factory.RedisPoolFactory;

import redis.clients.jedis.Jedis;

public class RedisPoolManager {
	private static RedisPoolManager instance = new RedisPoolManager();
	private RedisPool redisPool;

	private RedisPoolManager() {
		redisPool = RedisPoolFactory.instance().create();
	}

	public static RedisPoolManager instance() {
		return instance;
	}

	public Jedis getResource() {
		return redisPool.getResource();
	}

}
