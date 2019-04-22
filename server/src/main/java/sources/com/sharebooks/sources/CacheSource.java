package com.sharebooks.sources;

import java.util.HashMap;
import java.util.Map;
import com.sharebooks.cache.Cache;
import com.sharebooks.cache.DynamicCache;
import com.sharebooks.cache.StaticCache;
import com.sharebooks.cache.lruCache.LRUCache;
import com.sharebooks.cache.staticCache.EntitySetCache;
import com.sharebooks.coreEntities.*;
import com.sharebooks.coreEntities.enums.EntityType;
import com.sharebooks.entity.Entity;
import com.sharebooks.helperEntities.BookCategory;
import com.sharebooks.helperEntities.City;
import com.sharebooks.helperEntities.State;


public class CacheSource {
	private static Map<String , Cache<? extends Entity>> cacheMap = new HashMap<String , Cache<? extends Entity>>();
	
	
	public static void init() throws Exception{
		//dynamicCacheMap.put("book", new LRUCache<Book>());
		initBookCache();
		initUserCache();
		initBookCategoryCache();
		initStateCache();
		initCityCache();
	}
	
	private static void initBookCache() throws Exception{
		DynamicCache<Book> cache = null;
		String cacheType = PropertySource.getCacheProperty("BOOK_CACHE_TYPE");
		int cacheCapacity = Integer.parseInt(PropertySource.getCacheProperty("BOOK_CACHE_CAPACITY"));
		switch(cacheType){
			case "LRU":
				cache = new LRUCache<Book>();
				break;
			case "MRU":
				break;
			case "MFU":
				break;
			default:
				break;
		}

		cache.init(cacheCapacity);
		cacheMap.put(EntityType.BOOK.desc(), cache);
	}
	
	private static void initUserCache() throws Exception{
		DynamicCache<User> cache = null;
		String cacheType = PropertySource.getCacheProperty("USER_CACHE_TYPE");
		int cacheCapacity = Integer.parseInt(PropertySource.getCacheProperty("USER_CACHE_CAPACITY"));
		switch(cacheType){
			case "LRU":
				cache = new LRUCache<User>();
				break;
			case "MRU":
				break;
			case "MFU":
				break;
			default:
				break;
		}

		cache.init(cacheCapacity);
		cacheMap.put(EntityType.USER.desc(), cache);
	}
	
	private static void initBookCategoryCache() throws Exception{
		StaticCache<BookCategory> cache = new EntitySetCache<BookCategory>();
		cacheMap.put(EntityType.BOOK_CATEGORY.desc() , cache);
	}
	
	private static void initStateCache() throws Exception{
		StaticCache<State> cache = new EntitySetCache<State>();
		cacheMap.put(EntityType.STATE.desc() , cache);
	}
	
	private static void initCityCache() throws Exception{
		StaticCache<City> cache = new EntitySetCache<City>();
		cacheMap.put(EntityType.CITY.desc() , cache);
	}
	
	public static Cache<? extends Entity> getCache(String cacheName){
		return cacheMap.get(cacheName);
	}
}
