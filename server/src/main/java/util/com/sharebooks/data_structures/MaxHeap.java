package com.sharebooks.data_structures;

import java.util.HashMap;
import java.util.Map;

//The question is how many ways are there to form max heap from n distinct integers
public class MaxHeap {

	public static void main(String[] args) {
		int ways = (int) new MaxHeap().solve(67);
		System.out.println(ways);
	}

	public int solve(int n) {
		Map<Integer, Long> cache = new HashMap<Integer, Long>();
		long baseVal = 1;
		cache.put(0, baseVal);
		cache.put(1, baseVal);
		return (int) waysForN(n, cache, 1000000007);
	}

	private long waysForN(int n, Map<Integer, Long> cache, long modulo) {
		if (cache.get(n) != null) {
			return cache.get(n);
		}
		int remaining = n - 1;
		int rightNum = numOfRightNodes(n);
		int leftNum = remaining - rightNum;
		long ways = arrangements(remaining, rightNum) * waysForN(rightNum, cache, modulo)
				* waysForN(leftNum, cache, modulo);
		cache.put(n, (ways % modulo));
		return (ways % modulo);
	}

	private int numOfRightNodes(int n) {
		int level = 0, sum = 1, rightNum = 0;
		while (sum <= n) {
			level++;
			sum += (int) Math.pow(2, level);
		}
		sum -= Math.pow(2, level);
		level--;
		int rem = n - sum;
		rightNum = (sum - 1) / 2;
		int nextLevelNum = (int) Math.pow(2, level);
		if (rem < nextLevelNum) {
			rightNum += rem;
		} else {
			rightNum += nextLevelNum;
		}
		return rightNum;
	}

	private long arrangements(int m, int k) {
		long fact = 1;
		int z = m - k;
		while (m > k) {
			fact *= m;
			m--;
		}
		while (z > 0) {
			fact /= z;
			z--;
		}
		return fact;
	}
}
