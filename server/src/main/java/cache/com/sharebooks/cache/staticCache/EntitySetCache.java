package com.sharebooks.cache.staticCache;

import com.sharebooks.cache.StaticCache;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.exception.CacheException;
import java.util.*;


public class EntitySetCache<T extends Entity> implements StaticCache<T> {
	
	private TreeSet<T> entitySet;
	private List<T> sortedEntityList;

	public EntitySetCache() {
		entitySet = new TreeSet<T>();
	}

	
	public void init(List<T> entityList) throws CacheException {
		try{	
			int size = entityList.size();
			for(int i=0; i<size; i++){
				insert(0 , entityList.get(i));
			}
			
			sortedEntityList = new ArrayList<T>(entitySet);
		}
		catch(Exception ex){
			System.out.println("Error in init in StaticCache");
			throw new CacheException("Error in initializing");
		}
	}


	public List<T> getAll() throws Exception {
		try{
			return sortedEntityList;
		}
		catch(Exception ex){
			throw ex;
		}
	}


	public T get(Object key) throws CacheException{
		try{
			return null;
		}
		catch(Exception ex){
			System.out.println("Error in get method in LRUCache");
			throw new CacheException(ex.getMessage());
		}
	}


	public void insert(Object key , T t) throws CacheException {
		try{
			entitySet.add(t);
		}
		catch(Exception ex){
			System.out.println("Error in insert method in StaticCache");
			throw new CacheException("");
		}
	}


	@Override
	public void delete() throws Exception {
		
	}


	@Override
	public int size() {
		return sortedEntityList.size();
	}
	
	public void display(){
		
	}
}
