package com.sharebooks.data_structures;

import java.util.HashMap;
import java.util.Map;

public class ReverseNum {
	public static void main(String[] args) {
		System.out.println(new ReverseNum().trailingZeroes(40));
	}

	public int trailingZeroes(int num) {
		int countOf5s = 0;
		Map<Integer, Integer> cache = new HashMap<Integer, Integer>();
		while (num > 4) {
			int pow5 = log5(num);
			int q = num / (int) Math.pow(5, pow5);
			countOf5s += q * (numOf5s(pow5, cache));
			num = num - (q * (int) Math.pow(5, pow5));
		}
		return countOf5s;
	}

	private int numOf5s(int pow5, Map<Integer, Integer> cache) {
		if (cache.get(pow5) != null) {
			return cache.get(pow5);
		}
		if (pow5 == 1) {
			return 1;
		}
		int numOf5s = numOf5s(pow5 - 1, cache) * 5 + 1;
		cache.put(pow5, numOf5s);
		return numOf5s;
	}

	private int log5(int num) {
		return (int) (Math.log(num) / Math.log(5));
	}
}
