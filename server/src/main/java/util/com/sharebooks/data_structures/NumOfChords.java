package com.sharebooks.data_structures;

import java.util.HashMap;
import java.util.Map;

public class NumOfChords {

	public static void main(String[] arghs) {
		System.out.println(new NumOfChords().chordCnt(3));
	}

	public int chordCnt(int N) {
		int numPoints = 2 * N;
		return countWays(new HashMap<Integer, Integer>(), numPoints, 1000000007);
	}

	private Integer countWays(Map<Integer, Integer> cache, int points, int modulo) {
		if (points == 0 || points == 2) {
			return 1;
		}
		Integer ways = getFromCache(cache, points);
		if (ways != null) {
			return ways;
		}
		ways = 0;
		int p1 = 1, p2 = 2;
		while (p2 <= points) {
			ways += ((countWays(cache, points - p2, modulo) % modulo)
					* (countWays(cache, p2 - p1 - 1, modulo) % modulo));
			ways %= modulo;
			p2 += 2;
		}
		addToCache(cache, points, ways);
		return ways;
	}

	private void addToCache(Map<Integer, Integer> cache, int N, int ways) {
		cache.put(N, ways);
	}

	private Integer getFromCache(Map<Integer, Integer> cache, int N) {
		return cache.get(N);
	}
}
