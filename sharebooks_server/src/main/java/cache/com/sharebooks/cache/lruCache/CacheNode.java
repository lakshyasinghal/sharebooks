package com.sharebooks.cache.lruCache;

import com.sharebooks.entity.Entity;

class CacheNode<T extends Entity> {
	private CacheNode<T> prev;
	private long key;
	private T value;
	private CacheNode<T> next;


	public CacheNode(Long key , T value){
		this.key = key;
		this.value = value;
	}

	public T value(){
		return value;
	}

	public long key(){
		return key;
	}

	public CacheNode<T> prev(){
		return prev;
	}

	public CacheNode<T> next(){
		return next;
	}



	//set methods

	public void setValue(T val){
		value = val;
	}

	public void setPrev(CacheNode<T> node){
		prev = node;
	}

	public void setNext(CacheNode<T> node){
		next = node;
	}
}
