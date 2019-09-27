package com.sharebooks.data_structures;

import java.util.ArrayList;
import java.util.List;

public class MaxProduct {

	public static void main(String[] args) {
		List<Integer> list = new ArrayList<Integer>();
		// 0, 0, 0, -3, -2, 0, 1
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(-3);
		list.add(-2);
		list.add(0);
		list.add(1);
		// 0, 0, 0, 0, 0, -2, 0, 0, 0, 3, 3, 0, 0, 0, 0, 3
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(-2);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(3);
		list.add(3);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(0);
		list.add(3);
		System.out.println(new MaxProduct().maxProduct(list));
	}

	public int maxProduct(final List<Integer> list) {
		int max = Integer.MIN_VALUE;
		int curr = 1, l = list.size();
		int ptr1 = 0, ptr2 = 0;
		boolean zeroPresent = false;
		while (ptr1 < l && list.get(ptr1) == 0) {
			ptr1++;
		}
		ptr2 = ptr1;

		while (ptr2 < l) {
			curr = 1;
			while (ptr2 < l && list.get(ptr2) != 0) {
				int val = list.get(ptr2);
				if (val > max)
					max = val;
				curr *= val;
				if (curr > max)
					max = curr;
				ptr2++;
			}
			// if stopped because of zero
			if (ptr2 < l)
				ptr2--;
			// System.out.println(ptr2);
			if (curr < 0 && ptr2 > ptr1) {
				try {
					while (list.get(ptr1) > 0) {
						curr /= list.get(ptr1);
						ptr1++;
					}
					curr /= list.get(ptr1);
					if (curr > max)
						max = curr;
				} catch (Exception e) {
					System.out.println("Index => " + ptr1);
					System.out.println("Number => " + list.get(ptr1));
				}
			}
			if (ptr2 < l - 1) {
				zeroPresent = true;
				int newStart = ptr2 + 1;
				while (newStart < l && list.get(newStart) == 0)
					newStart++;
				ptr1 = newStart;
				ptr2 = newStart;
			}
		}

		if (max < 0 && zeroPresent)
			max = 0;
		return max;
	}
}
