package com.sharebooks.redis;

import redis.clients.jedis.Jedis;

public abstract class AbstractRedis {
	protected Jedis jedis;
	protected String storeName;

	public AbstractRedis(Jedis jedis, String storeName) {
		this.jedis = jedis;
		this.storeName = storeName;
	}

}
