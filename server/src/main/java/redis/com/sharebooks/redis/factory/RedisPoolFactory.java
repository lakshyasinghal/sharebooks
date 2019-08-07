package com.sharebooks.redis.factory;

import com.sharebooks.config.appConfig.AppConfig;
import com.sharebooks.config.properties.RedisProperties;
import com.sharebooks.redis.pool.RedisPool;
import com.sharebooks.redis.pool.RedisPool.RedisPoolBuilder;

public class RedisPoolFactory {
	private static RedisPoolFactory instance;

	private RedisPoolFactory() {

	}

	public static RedisPoolFactory instance() {
		if (instance == null) {
			synchronized (RedisPoolFactory.class) {
				if (instance == null) {
					instance = new RedisPoolFactory();
				}
			}
		}
		return instance;
	}

	public RedisPool create() {
		String host = AppConfig.redisProp(RedisProperties.HOST);
		int port = Integer.parseInt(AppConfig.redisProp(RedisProperties.PORT));
		int timeout = Integer.parseInt(AppConfig.redisProp(RedisProperties.TIMEOUT));
		String password = AppConfig.redisProp(RedisProperties.PASSWORD);

		RedisPoolBuilder b = new RedisPool.RedisPoolBuilder();
		b.host(host).port(port).timeout(timeout).password(password);
		return b.build();
	}

}
