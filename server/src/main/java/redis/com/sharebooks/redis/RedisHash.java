package com.sharebooks.redis;

import redis.clients.jedis.Jedis;

public class RedisHash extends AbstractRedis {

	public RedisHash(Jedis jedis, String storeName) {
		super(jedis, storeName);
		this.storeName = storeName;
	}

	public long set(String key, String value) {
		return jedis.hset(storeName, key, value);
	}

	public String get(String key) {
		return jedis.hget(storeName, key);
	}

}
