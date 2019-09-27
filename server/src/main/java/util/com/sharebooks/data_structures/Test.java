package com.sharebooks.data_structures;

import java.util.List;

public class Test {

	public static void main(String[] args) {

	}

	static int shaktimaan(List<Integer> power) {
		int min_charge = 0, curr_charge = 0;
		for (Integer pow : power) {
			curr_charge += pow;
			if (curr_charge < min_charge) {
				min_charge = curr_charge;
			}
		}
		return min_charge + 1;
	}

	static int maximalDifference(List<Integer> list) {
		int maximal = -1, diff = 0;
		int ptr = 1, curr_min = list.get(0);
		int l = list.size();
		while (ptr < l) {
			int curr = list.get(ptr);
			if (curr < curr_min) {
				curr_min = curr;
			} else if (curr > curr_min) {
				diff = curr - curr_min;
				if (diff > maximal) {
					maximal = diff;
				}
			}
			ptr++;
		}
		Float a = 0.0f;
		System.out.println();
		return maximal;
	}

}
