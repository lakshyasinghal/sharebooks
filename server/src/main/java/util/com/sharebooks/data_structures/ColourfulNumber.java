package com.sharebooks.data_structures;

import java.util.HashSet;
import java.util.Set;

public class ColourfulNumber {

	public static void main(String[] args) {
		System.out.println(new ColourfulNumber().colorful(12));
	}

	public int colorful(int number) {
		if (number == 0)
			return 0;
		Set<Integer> products = new HashSet<Integer>();
		int[] digits = getDigitsArray(number);
		int l = digits.length;
		int ptr1 = 0, ptr2 = 0;
		for (int window = 1; window <= l; window++) {
			for (ptr1 = 0; ptr1 <= l - window; ptr1++) {
				int last = ptr1 + window;
				int product = 1;
				for (ptr2 = ptr1; ptr2 < last; ptr2++) {
					product *= digits[ptr2];
				}
				if (products.contains(product)) {
					return 0;
				}
				products.add(product);
			}
		}
		return 1;
	}

	private int[] getDigitsArray(int number) {
		int l = 0, i = 0, q = 0;
		while ((number / (int) Math.pow(10, i)) >= 1) {
			// System.out.println((int) Math.pow(10, i));
			l++;
			i++;
		}
		int[] digits = new int[l];
		for (i = 1; i <= l; i++) {
			int digit = (number % (int) Math.pow(10, i)) / (int) Math.pow(10, i - 1);
			digits[l - i] = digit;
		}
		return digits;
	}
}
