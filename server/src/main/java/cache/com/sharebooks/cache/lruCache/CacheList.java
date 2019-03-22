//package com.sharebooks.cache.lruCache;
//
//
//
//public class CacheList {
//
//	private CacheNode startNode;
//	private CacheNode endNode;
//	private int size;
//
//
//	public CacheList(){
//
//	}
//
//
//	public CacheNode insert(int key , Object obj) throws Exception{
//		try{
//			CacheNode node = new CacheNode(key , obj);
//
//			if(startNode == null){
//				startNode = node;
//				endNode = node;
//			}
//
//			endNode.setNext(node);
//			node.setPrev(endNode);
//			endNode = node;
//
//			size++;
//
//			return node;
//		}
//		catch(Exception ex){
//			System.out.println("Error in insert in CacheList");
//			throw ex;
//		}
//	} 
//
//
//	public void moveToLast(CacheNode node) throws Exception{
//		try{
//			CacheNode prevNode = node.getPrev();
//			CacheNode nextNode = node.getNext();
//
//			if(size == 1 || node.equals(endNode)){
//				return;
//			}
//			else{
//				if(node.equals(startNode)){
//					startNode = nextNode;
//				}
//				nextNode.setPrev(prevNode);
//				if(prevNode != null){
//					prevNode.setNext(nextNode);
//				}
//				endNode.setNext(node);
//				node.setPrev(endNode);
//				node.setNext(null);
//				endNode = node;
//			}
//		}
//		catch(Exception ex){
//			System.out.println("Error in moveToLast in CacheList");
//			throw ex;
//		}
//	}
//
//	public void remove(CacheNode node) throws Exception{
//		try{
//			CacheNode nextNode = node.getNext();
//
//			if(size == 1){
//				startNode = null;
//				endNode = null;
//			}
//			else{
//				nextNode.setPrev(null);
//				startNode = nextNode;
//			}
//
//			size--;
//		}
//		catch(Exception ex){
//			System.out.println("Error in delete in CacheList");
//			throw ex;
//		}
//	}
//
//
//	public CacheNode getStartNode(){
//		return startNode;
//	}
//
//	public CacheNode getEndNode(){
//		return endNode;
//	}
//
//}