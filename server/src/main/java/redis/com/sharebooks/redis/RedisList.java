package com.sharebooks.redis;

import java.util.List;

import redis.clients.jedis.Jedis;

public class RedisList extends AbstractRedis {

	public RedisList(Jedis jedis, String storeName) {
		super(jedis, storeName);
	}

	public long push(String value) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	public List<String> get(int start, int end) {
		return jedis.lrange(storeName, start, end);
	}

	// 0 will correspond to left side and 1 will correspond to right side
	public String pop(int side) throws Exception {
		String val = null;
		if (side == 0) {
			val = jedis.lpop(storeName);
		} else {
			val = jedis.rpop(storeName);
		}
		return val;
	}

	public long size() {
		return jedis.llen(storeName);
	}

}
