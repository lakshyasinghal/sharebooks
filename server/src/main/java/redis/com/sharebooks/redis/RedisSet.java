package com.sharebooks.redis;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisSet extends AbstractRedis {

	public RedisSet(Jedis jedis, String storeName) {
		super(jedis, storeName);
	}

	public long add(String value) {
		return jedis.sadd(storeName, value);
	}

	public long add(List<String> values) {
		long addCount = 0;
		for (String val : values) {
			addCount += jedis.sadd(storeName, val);
		}
		return addCount;
	}

	public Set<String> getAll() {
		return jedis.smembers(storeName);
	}

	public boolean contains(String value) {
		return jedis.sismember(storeName, value);
	}

}
