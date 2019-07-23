package com.sharebooks.redis.pool;

import redis.clients.jedis.Jedis;

public class RedisPoolManager {
	private RedisPool redisPool;

	public Jedis getResource() {
		return redisPool.getResource();
	}

}
