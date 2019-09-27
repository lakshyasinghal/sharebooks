package com.sharebooks.data_structures;

import java.util.ArrayList;
import java.util.Collections;

public class CandyDistribution {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(3);
		list.add(4);
		list.add(4);
		list.add(6);
		list.add(9);
		// list.add(2);
		// list.add(-12);

		System.out.println(new CandyDistribution().candy(list));
	}

	public int candy(ArrayList<Integer> list) {
		Collections.sort(list);
		int len = list.size();
		int ptr1 = 0, ptr2 = 0;
		int curr = 1;
		while (ptr1 < len) {
			while (ptr2 < len && list.get(ptr2) == list.get(ptr1)) {
				ptr2++;
			}
			ptr2--;
			if (list.get(ptr1) != curr) {
				for (int i = ptr1; i <= ptr2; i++) {
					list.set(i, curr);
				}
			}
			curr++;
			ptr1 = ++ptr2;
		}
		int min_sum = 0;
		for (Integer n : list) {
			min_sum += n;
		}

		return min_sum;
	}
}
