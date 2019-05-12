package com.sharebooks.cache.lruCache;

import java.util.*;
import com.sharebooks.cache.*;
import com.sharebooks.entities.entity.Entity;
import com.sharebooks.exception.CacheException;


//this class will only be accessible inside this package
public class LRUCache<T extends Entity> implements DynamicCache<T> {

	private Map<Object , CacheNode<T>> cacheNodeMap;
	//private CacheList cacheList;
	private CacheNode<T> startNode;
	private CacheNode<T> endNode;
	private int size;
	private int capacity;



	public LRUCache() throws Exception{
		try{
			cacheNodeMap = new HashMap<Object , CacheNode<T>>();
			startNode = null;
			endNode = null;
			//cacheList = new CacheList();
		}
		catch(Exception ex){
			System.out.println("Error in constructor in LRUCache");
			throw ex;
		}
	}

	
	public void init(int capacity) throws CacheException {
		try{
			if(capacity < 1){
				throw new CacheException("Cache size cannot be zero");
			}
			this.capacity = capacity;
		}
		catch(Exception ex){
			System.out.println("Error in init in LRUCache");
			throw ex;
		}
	}


	public T get(Object key) throws CacheException , Exception{
		try{
			T t = null;
			CacheNode<T> node = null;

			if(cacheNodeMap == null){
				throw new CacheException("Cache not initialized");
			}

			if(cacheNodeMap.containsKey(key)){
				node = cacheNodeMap.get(key);
				t = node.value();
				moveToLast(node);
			}

			return t;
		}
		catch(ClassCastException ex){
			throw new Exception();
		}
		catch(CacheException ex){
			System.out.println("Error in get method in LRUCache");
			throw ex;
		}
		catch(Exception ex){
			throw ex;
		}
	}



	public synchronized void insert(Object key , T value) throws Exception {
		try{
			if(cacheNodeMap.containsKey(key)){
				return;
			}

			if(size == capacity){
				delete();
			}

			//CacheNode<T> node = insert(key , value);
			CacheNode<T> node = new CacheNode<T>(key , value);

			if(startNode == null){
				startNode = node;
			}
			else{
				endNode.setNext(node);
				node.setPrev(endNode);
			}
			
			endNode = node;
			size++;

			cacheNodeMap.put(key , node);
		}
		catch(Exception ex){
			System.out.println("Error in insert method in LRUCache");
			throw ex;
		}
	}



	public void delete() throws Exception {
		try{
			//CacheNode<T> startNode = cacheList.getStartNode();
			Object key = startNode.key();
			cacheNodeMap.remove(key);
			
			CacheNode<T> nextNode = startNode.next();

			if(size == 1){
				startNode = null;
				endNode = null;
			}
			else{
				nextNode.setPrev(null);
				startNode = nextNode;
			}
			size--;
		}
		catch(Exception ex){
			System.out.println("Error in delete method in LRUCache");
			throw ex;
		}
	}
	
	
	public void moveToLast(CacheNode<T> node) throws Exception{
		try{
			CacheNode<T> prevNode = node.prev();
			CacheNode<T> nextNode = node.next();

			if(size == 1 || node.equals(endNode)){
				return;
			}
			else{
				if(node.equals(startNode)){
					startNode = nextNode;
				}
				nextNode.setPrev(prevNode);
				if(prevNode != null){
					prevNode.setNext(nextNode);
				}
				endNode.setNext(node);
				node.setPrev(endNode);
				node.setNext(null);
				endNode = node;
			}
		}
		catch(Exception ex){
			System.out.println("Error in moveToLast in CacheList");
			throw ex;
		}
	}

	public int capacity(){
		return capacity;
	}

	public int size(){
		return size;
	}
	
	public void display(){
		CacheNode<T> ptr = startNode;
		while(ptr != null){
			System.out.print(ptr.key());
			System.out.print(" ---> ");
			ptr = ptr.next();
		}
		System.out.print("null");
	}
}

