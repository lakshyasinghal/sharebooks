package com.sharebooks.data_structures;

import java.util.ArrayList;

public class ContinuousOnes {

	public static void main(String[] args) {
		// 1, 0, 1, 0, 1, 1, 1, 1, 0, 0, 0, 0, 1, 1, 1, 1, 0
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(1);
		list.add(0);
		list.add(1);
		list.add(0);
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(1);
		list.add(0);
		ArrayList<Integer> res = maxone(list, 4);
	}

	public static ArrayList<Integer> maxone(ArrayList<Integer> list, int M) {
		if (M == 0)
			return maxone(list);
		int p1 = 0, p2 = 0, max = 0, limit = list.size() - 1;
		int[] indices = new int[2];
		while (p2 <= limit && M > 0) {
			if (list.get(p2) == 0) {
				M--;
			}
			p2++;
		}
		while (p2 <= limit && list.get(p2) == 1) {
			p2++;
		}
		p2--;
		max = p2 - p1 + 1;
		indices[0] = p1;
		indices[1] = p2;

		while (p2 <= limit) {
			while (p1 <= limit && list.get(p1) == 1) {
				p1++;
			}
			p1++;
			p2++;
			while (p2 <= limit && list.get(p2) == 1) {
				p2++;
			}
			while (p2 < limit && list.get(p2 + 1) == 1) {
				p2++;
			}
			if (p2 <= limit && (p2 - p1 + 1) > max) {
				max = p2 - p1 + 1;
				indices[0] = p1;
				indices[1] = p2;
			}
		}

		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int j = indices[0]; j <= indices[1]; j++) {
			res.add(j);
		}
		return res;
	}

	private static ArrayList<Integer> maxone(ArrayList<Integer> list) {
		int p1 = 0, p2 = 0, limit = list.size() - 1, max = 0;
		int[] indices = new int[2];
		while (p1 <= limit && p2 <= limit) {
			while (p1 <= limit && list.get(p1) == 0) {
				p1++;
			}
			p2 = p1;
			while (p2 <= limit && list.get(p2) == 1) {
				p2++;
			}
			p2--;
			if (p2 - p1 + 1 > max) {
				max = p2 - p1 + 1;
				indices[0] = p1;
				indices[1] = p2;
			}
			p1 = p2 + 1;
		}

		ArrayList<Integer> res = new ArrayList<Integer>();
		for (int j = indices[0]; j <= indices[1]; j++) {
			res.add(j);
		}
		return res;
	}
}
