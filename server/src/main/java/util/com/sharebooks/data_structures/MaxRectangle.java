package com.sharebooks.data_structures;

import java.util.ArrayList;

public class MaxRectangle {

	public static void main(String[] args) {

//		[0, 0, 1, 0, 0, 0, 1, 0, 1]
//				  [0, 1, 1, 0, 0, 0, 0, 0, 0]
//				  [0, 0, 1, 0, 1, 0, 1, 0, 1]
//				  [0, 1, 0, 0, 0, 1, 1, 0, 1]
//				  [0, 1, 0, 0, 0, 0, 1, 1, 1]
//				  [1, 0, 1, 1, 1, 0, 1, 1, 1]
//				  [1, 1, 1, 1, 0, 1, 1, 1, 1]
//				  [1, 1, 1, 0, 1, 0, 1, 0, 1]
		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		list.add(new ArrayList<Integer>());
		list.add(new ArrayList<Integer>());
		list.add(new ArrayList<Integer>());
		list.add(new ArrayList<Integer>());
		list.add(new ArrayList<Integer>());
		list.add(new ArrayList<Integer>());
		list.add(new ArrayList<Integer>());
		list.add(new ArrayList<Integer>());

		list.get(0).add(0);
		list.get(0).add(0);
		list.get(0).add(1);
		list.get(0).add(0);
		list.get(0).add(0);
		list.get(0).add(0);
		list.get(0).add(1);
		list.get(0).add(0);
		list.get(0).add(1);

		list.get(1).add(0);
		list.get(1).add(1);
		list.get(1).add(1);
		list.get(1).add(0);
		list.get(1).add(0);
		list.get(1).add(0);
		list.get(1).add(0);
		list.get(1).add(0);
		list.get(1).add(0);

		list.get(2).add(0);
		list.get(2).add(0);
		list.get(2).add(1);
		list.get(2).add(0);
		list.get(2).add(1);
		list.get(2).add(0);
		list.get(2).add(1);
		list.get(2).add(0);
		list.get(2).add(1);

		list.get(3).add(0);
		list.get(3).add(1);
		list.get(3).add(0);
		list.get(3).add(0);
		list.get(3).add(0);
		list.get(3).add(1);
		list.get(3).add(1);
		list.get(3).add(0);
		list.get(3).add(1);

		list.get(4).add(0);
		list.get(4).add(1);
		list.get(4).add(0);
		list.get(4).add(0);
		list.get(4).add(0);
		list.get(4).add(0);
		list.get(4).add(1);
		list.get(4).add(1);
		list.get(4).add(1);

		list.get(5).add(1);
		list.get(5).add(0);
		list.get(5).add(1);
		list.get(5).add(1);
		list.get(5).add(1);
		list.get(5).add(0);
		list.get(5).add(1);
		list.get(5).add(1);
		list.get(5).add(1);

		list.get(6).add(1);
		list.get(6).add(1);
		list.get(6).add(1);
		list.get(6).add(1);
		list.get(6).add(0);
		list.get(6).add(1);
		list.get(6).add(1);
		list.get(6).add(1);
		list.get(6).add(1);

		list.get(7).add(1);
		list.get(7).add(1);
		list.get(7).add(1);
		list.get(7).add(0);
		list.get(7).add(1);
		list.get(7).add(0);
		list.get(7).add(1);
		list.get(7).add(0);
		list.get(7).add(1);

		System.out.println(new MaxRectangle().maximalRectangle(list));
	}

	public int maximalRectangle(ArrayList<ArrayList<Integer>> list) {
		int rows = list.size(), cols = list.get(0).size();
		int[][] cache = new int[rows][cols];
		buildCache(list, cache);
		int max_area = 0;
		for (int r = 0; r < rows; r++) {
			max_area = Math.max(max_area, findMaxArea(cache, r, cols));
		}
		return max_area;
	}

	private int findMaxArea(int[][] cache, int r, int cols) {
		int max = 0, curr = 0;
		int ptr1 = 0, ptr2 = 0;
		for (int i = 0; i < cols; i++) {
			ptr1 = i;
			ptr2 = i;
			int val = cache[r][i];
			while (ptr1 > -1 && cache[r][ptr1] >= val) {
				ptr1--;
			}
			ptr1++;
			while (ptr2 < cols && cache[r][ptr2] >= val) {
				ptr2++;
			}
			ptr2--;
			curr = val * (ptr2 - ptr1 + 1);
			if (curr > max) {
				max = curr;
			}
		}

		return max;
	}

	private void buildCache(ArrayList<ArrayList<Integer>> list, int[][] cache) {
		int r = 0, c = 0;
		int rows = list.size(), cols = list.get(0).size();
		for (; r < rows; r++) {
			for (c = 0; c < cols; c++) {
				int list_val = list.get(r).get(c);
				int cache_upper_val = 0;
				if (r > 0) {
					cache_upper_val = cache[r - 1][c];
				}
				if (list_val == 1) {
					cache[r][c] = 1 + cache_upper_val;
				}
			}
		}
	}
}
