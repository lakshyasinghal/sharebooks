package com.sharebooks.dao.redis;

import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

import com.sharebooks.redis.pool.RedisPoolManager;

import redis.clients.jedis.Jedis;

public abstract class AbstractRedisDao {
	private static final Logger LOGGER = Logger.getLogger(AbstractRedisDao.class);
	private RedisPoolManager redisPoolManager = RedisPoolManager.instance();

	// this will insert key and value in redis
	public boolean insert(String key, String value) {
		LOGGER.trace("Enetered insert");
		Jedis jedis = getConnection();
		jedis.set(key, value);
		LOGGER.trace("Leaving insert");
		return false;
	}

	public boolean insertInList(String listName, String value) {
		LOGGER.trace("Enetered insertInList");
		Jedis jedis = getConnection();
		jedis.rpush(listName, value);
		LOGGER.trace("Leaving insertInList");
		return false;
	}

	public boolean insertInSet(String setName, String value) {
		LOGGER.trace("Enetered insertInSet");
		Jedis jedis = getConnection();
		jedis.sadd(setName, value);
		LOGGER.trace("Leaving insertInSet");
		return false;
	}

	public boolean insertInHash(String hashName, String key, String value) {
		LOGGER.trace("Enetered insertInHash");
		Jedis jedis = getConnection();
		jedis.hset(hashName, key, value);
		LOGGER.trace("Leaving insertInHash");
		return false;
	}

	public String get(String key) {
		LOGGER.trace("Enetered get");
		String value = null;
		Jedis jedis = getConnection();
		value = jedis.get(key);
		LOGGER.trace("Leaving get");
		return value;
	}

	// put 0 and -1 indexes to get complete list
	public List<String> getFromList(String listName, long start, long end) {
		LOGGER.trace("Enetered getFromList");
		List<String> values = null;
		Jedis jedis = getConnection();
		values = jedis.lrange(listName, start, end);
		LOGGER.trace("Leaving getFromList");
		return values;
	}

	public Set<String> getFromSet(String setName) {
		LOGGER.trace("Enetered getFromSet");
		Set<String> values = null;
		Jedis jedis = getConnection();
		values = jedis.smembers(setName);
		LOGGER.trace("Leaving getFromSet");
		return values;
	}

	public String getFromHash(String hashName, String key) {
		LOGGER.trace("Enetered getFromHash");
		String value = null;
		Jedis jedis = getConnection();
		value = jedis.hget(hashName, key);
		LOGGER.trace("Leaving getFromHash");
		return value;
	}

	private Jedis getConnection() {
		Jedis jedis = null;
		try {
			jedis = redisPoolManager.getResource();
		} catch (Exception ex) {
			throw ex;
		}
		return jedis;
	}
}
