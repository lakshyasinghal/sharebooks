package com.sharebooks.redis.pool;

import org.apache.log4j.Logger;

import com.sharebooks.exception.JedisConnectionException;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

public class RedisPool {
	private static final Logger LOGGER = Logger.getLogger(RedisPool.class);
	private JedisPool pool;

	private RedisPool(RedisPoolBuilder b) {
		pool = new JedisPool(b.config, b.host, b.port, b.timeout, b.password);
	}

	public static class RedisPoolBuilder {
		private JedisPoolConfig config = new JedisPoolConfig();
		private String host;
		private int port;
		private int timeout;
		private String password;

		public RedisPoolBuilder host(String host) {
			this.host = host;
			return this;
		}

		public RedisPoolBuilder port(int port) {
			this.port = port;
			return this;
		}

		public RedisPoolBuilder timeout(int timeout) {
			this.timeout = timeout;
			return this;
		}

		public RedisPoolBuilder password(String password) {
			this.password = password;
			return this;
		}

		public RedisPool build() {
			return new RedisPool(this);
		}
	}

	public Jedis getResource() {
		Jedis jedis = null;
		try {
			jedis = pool.getResource();
		} catch (JedisConnectionException ex) {
			LOGGER.error(null, ex);
		}
		return jedis;
	}
}
