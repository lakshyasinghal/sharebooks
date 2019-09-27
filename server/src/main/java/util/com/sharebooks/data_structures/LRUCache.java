package com.sharebooks.data_structures;

import java.util.HashMap;
import java.util.Map;

public class LRUCache {
	int globalTimestamp = 0;
	Map<Integer, HeapNode> map;
	HeapNode[] heap;
	int capacity;
	int size = 0;

	public LRUCache(int capacity) {
		this.capacity = capacity;
		map = new HashMap<Integer, HeapNode>();
		heap = new HeapNode[capacity];
	}

	public static void main(String[] args) {
		// 6 1 S 2 1 S 2 2 G 2 S 1 1 S 4 1 G 2
		LRUCache cache = new LRUCache(1);
		cache.set(2, 1);
		cache.set(2, 2);
		System.out.println(cache.get(2));
		cache.set(1, 1);
		cache.set(4, 2);
		System.out.println(cache.get(2));
	}

	public int get(int key) {
		int val = -1;
		HeapNode node = map.get(key);
		if (node != null) {
			val = node.val;
			node.timestamp = ++globalTimestamp;
			heapify(node.index);
		}
		return val;
	}

	public void set(int key, int value) {
		HeapNode node = map.get(key);
		if (node != null) {
			node.val = value;
			node.timestamp = ++globalTimestamp;
			heapify(node.index);
			return;
		}
		if (size == capacity) {
			remove();
		}

		heap[size] = new HeapNode(key, value, ++globalTimestamp, size);
		map.put(key, heap[size]);
		size++;
	}

	private void remove() {
		int key = heap[0].key;
		map.remove(key);
		heap[0] = heap[size - 1];
		heap[0].index = 0;
		size--;
		heapify(0);
	}

	private void heapify(int ptr) {
		int smaller = ptr;
		int left = ptr * 2 + 1;
		int right = ptr * 2 + 2;

		if (right < size && compare(heap[right], heap[smaller]) < 0) {
			smaller = right;
		}
		if (left < size && compare(heap[left], heap[smaller]) < 0) {
			smaller = left;
		}
		if (smaller != ptr) {
			HeapNode temp = heap[ptr];
			heap[ptr] = heap[smaller];
			heap[smaller] = temp;
			heapify(smaller);
		}
	}

	private int compare(HeapNode node1, HeapNode node2) {
		return node1.timestamp - node2.timestamp;
	}

	private void swap(int i, int j) {
		HeapNode temp = heap[i];
		heap[i] = heap[j];
		heap[j] = temp;
		heap[i].index ^= heap[j].index;
		heap[j].index ^= heap[i].index;
		heap[i].index ^= heap[j].index;
	}
}

class HeapNode {
	int timestamp;
	int key;
	int val;
	int index;

	public HeapNode(int key, int val, int timestamp, int index) {
		this.key = key;
		this.val = val;
		this.timestamp = timestamp;
		this.index = index;
	}
}