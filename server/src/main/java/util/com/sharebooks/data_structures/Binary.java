//package com.sharebooks.data_structures;
//
//import java.util.ArrayList;
//import java.util.List;
//
//public class Binary {
//
//	public static void main(String[] args) {
//		System.out.println(log2(1));
//	}
//
//	public static long minOperations(long n) {
//		// Write your code here
//		int[] binary = binaryArr(n);
//		List<Integer> count = new ArrayList<Integer>();
//		count.add(0);
//		for (int i = binary.length - 1; i >= 0; i--) {
//			minOperations(binary, i, count);
//		}
//		return 1;
//	}
//
//	private static void minOperations(int[] binary, int pos, List<Integer> count) {
//		if (binary[pos] == 0) {
//			return;
//		}
//		if (pos == 0 && binary[pos] == 0) {
//			count.set(0, count.get(0) + 1);
//			return;
//		}
//		OneAndZeroes(binary, pos - 1, count);
//		binary[pos] = 0;
//		count.set(0, count.get(0) + 1);
//		return;
//	}
//
//	private static void OneAndZeroes(int[] binary, int pos, List<Integer> count) {
//		if (binary[pos] == req) {
//
//		} else {
//
//		}
//	}
//
//	private static int[] binaryArr(long n) {
//		long rem = n;
//		int size = log2(n) + 1;
//		int[] binary = new int[size];
//		while (rem > 0) {
//			int val = log2(rem);
//			binary[val] = 1;
//			rem = rem - (long) Math.pow(2, val);
//		}
//		return null;
//	}
//
//	private static int log2(long x) {
//		return (int) (Math.log(x) / Math.log(2));
//	}
//}
