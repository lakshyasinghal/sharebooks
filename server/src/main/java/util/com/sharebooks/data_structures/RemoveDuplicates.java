package com.sharebooks.data_structures;

import java.util.ArrayList;

public class RemoveDuplicates {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		list.add(5000);
		list.add(5000);
		list.add(5000);
		removeDuplicates(list);
	}

	public static int removeDuplicates(ArrayList<Integer> list) {
		int ptr1 = 0, ptr2 = 1, limit = list.size() - 1;

		while (ptr2 <= limit) {
			while (ptr2 <= limit && (int) list.get(ptr2) == (int) list.get(ptr1)) {
				ptr2++;
			}
			if (ptr2 <= limit) {
				ptr1++;
				list.set(ptr1, list.get(ptr2));
				ptr2++;
			}
		}

		for (int i = limit; i > ptr1; i--) {
			list.remove(i);
		}

		return list.size();
	}
}
