package com.sharebooks.data_structures;

import java.util.ArrayList;

public class MaxSum {

	// [16, 5, 54, 55, 36, 82, 61, 77, 66, 61]
	// [31, 30, 36, 70, 9, 37, 1, 11, 68, 14]
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		list.add(new ArrayList<Integer>());
		list.get(0).add(16);
		list.get(0).add(5);
		list.get(0).add(54);
		list.get(0).add(55);
		list.get(0).add(36);
		list.get(0).add(82);
		list.get(0).add(61);
		list.get(0).add(77);
		list.get(0).add(66);
		list.get(0).add(61);

		list.add(new ArrayList<Integer>());
		list.get(1).add(31);
		list.get(1).add(30);
		list.get(1).add(36);
		list.get(1).add(70);
		list.get(1).add(9);
		list.get(1).add(37);
		list.get(1).add(1);
		list.get(1).add(11);
		list.get(1).add(68);
		list.get(1).add(14);

		System.out.println(new MaxSum().adjacent(list));
	}

	public int adjacent(ArrayList<ArrayList<Integer>> list) {
		if (list == null || list.size() == 0 || list.get(0).size() == 0) {
			return 0;
		}
		int N = list.get(0).size();
		int[] arr = new int[N];
		int i = 0;
		for (i = 0; i < N; i++) {
			arr[i] = Math.max(list.get(0).get(i), list.get(1).get(i));
		}
		if (N < 3) {
			if (N == 2) {
				return Math.max(arr[0], arr[1]);
			} else if (N == 1) {
				return arr[0];
			}
		}
		int max = 0;
		int first = arr[N - 3] + arr[N - 1], second = arr[N - 2], third = arr[N - 1];
		max = Math.max(second, first);
		for (i = N - 4; i >= 0; i--) {
			int currMax = Math.max(second, third) + arr[i];
			if (currMax > max) {
				max = currMax;
			}
			third = second;
			second = first;
			first = currMax;
		}
		return max;
	}
}
