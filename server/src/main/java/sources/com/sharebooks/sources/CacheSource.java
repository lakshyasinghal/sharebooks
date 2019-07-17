package com.sharebooks.sources;

import java.util.HashMap;
import java.util.Map;

import com.sharebooks.cache.Cache;
import com.sharebooks.cache.DynamicCache;
import com.sharebooks.cache.StaticCache;
import com.sharebooks.cache.enums.CacheType;
import com.sharebooks.cache.lruCache.LRUCache;
import com.sharebooks.cache.staticCache.EntitySetCache;
import com.sharebooks.config.appConfig.AppConfig;
import com.sharebooks.config.properties.CacheProperties;
import com.sharebooks.entities.coreEntities.Book;
import com.sharebooks.entities.coreEntities.User;
import com.sharebooks.entities.coreEntities.enums.EntityType;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.entities.helperEntities.BookCategory;
import com.sharebooks.entities.helperEntities.City;
import com.sharebooks.entities.helperEntities.State;

public class CacheSource {
	private static Map<String, Cache<? extends Entity>> cacheMap = new HashMap<String, Cache<? extends Entity>>();

	public static void init() throws Exception {
		// dynamicCacheMap.put("book", new LRUCache<Book>());
		initBookCache();
		initUserCache();
		initBookCategoryCache();
		initStateCache();
		initCityCache();
	}

	private static void initBookCache() throws Exception {
		DynamicCache<Book> cache = null;
		String cacheTypeAlias = AppConfig.cacheProp(CacheProperties.BOOK_CACHE_TYPE);
		CacheType bookCacheType = CacheType.getCacheType(cacheTypeAlias);
		int cacheCapacity = Integer.parseInt(AppConfig.cacheProp(CacheProperties.BOOK_CACHE_CAPACITY));
		switch (bookCacheType) {
		case LRU:
			cache = new LRUCache<Book>();
			break;
		case MRU:
			throw new Exception();
		case MFU:
			throw new Exception();
		case REDIS:
			throw new Exception();
		default:
			break;
		}

		cache.init(cacheCapacity);
		cacheMap.put(EntityType.BOOK.desc(), cache);
	}

	private static void initUserCache() throws Exception {
		DynamicCache<User> cache = null;
		String cacheTypeAlias = AppConfig.cacheProp(CacheProperties.USER_CACHE_TYPE);
		CacheType bookCacheType = CacheType.getCacheType(cacheTypeAlias);
		int cacheCapacity = Integer.parseInt(AppConfig.cacheProp(CacheProperties.USER_CACHE_CAPACITY));
		switch (bookCacheType) {
		case LRU:
			cache = new LRUCache<User>();
			break;
		case MRU:
			throw new Exception();
		case MFU:
			throw new Exception();
		case REDIS:
			throw new Exception();
		default:
			break;
		}

		cache.init(cacheCapacity);
		cacheMap.put(EntityType.USER.desc(), cache);
	}

	private static void initBookCategoryCache() throws Exception {
		StaticCache<BookCategory> cache = new EntitySetCache<BookCategory>();
		cacheMap.put(EntityType.BOOK_CATEGORY.desc(), cache);
	}

	private static void initStateCache() throws Exception {
		StaticCache<State> cache = new EntitySetCache<State>();
		cacheMap.put(EntityType.STATE.desc(), cache);
	}

	private static void initCityCache() throws Exception {
		StaticCache<City> cache = new EntitySetCache<City>();
		cacheMap.put(EntityType.CITY.desc(), cache);
	}

	public static Cache<? extends Entity> getCache(String cacheName) {
		return cacheMap.get(cacheName);
	}
}
