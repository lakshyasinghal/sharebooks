package com.sharebooks.data_structures;

import java.util.ArrayList;

public class LesserNumbers {
//	A : [ 2, 3, 5, 6, 7, 9 ]
//			B : 5
//			C : 42950
	public static void main(String[] args) {
		ArrayList<Integer> digits = new ArrayList<Integer>();
		digits.add(0);
		digits.add(1);
		digits.add(2);
		digits.add(3);
		digits.add(4);
		digits.add(5);
		digits.add(6);
		digits.add(7);
		digits.add(8);
		digits.add(9);

		System.out.println(new LesserNumbers().solve(digits, 9, 51822));
	}

	public int solve(ArrayList<Integer> digits, int B, int C) {
		if (digits == null || digits.size() == 0) {
			return 0;
		}
		int len = 0, i = 0;
		while (C / (int) Math.pow(10, i) > 0) {
			len++;
			i++;
		}
		if (B < len) {
			int ways = (int) Math.pow(digits.size(), B - 1);
			if (digits.contains(0) && B != 1) {
				ways *= (digits.size() - 1);
			} else {
				ways *= digits.size();
			}
			ways = ways == 0 ? 1 : ways;
			return ways;
		}
		return lesserNums(digits, C, B, B);
	}

	private int lesserNums(ArrayList<Integer> digits, int num, int pos, int B) {
		if (pos == 0) {
			return 0;
		}
		int currDigit = getCurrDigit(num, pos);
		int lesserDigits = getLesserDigits(currDigit, digits);
		if (B != 1 && pos == B && digits.contains(0)) {
			lesserDigits--;
		}
		int lesserNums = 0;
		// lesserNums = lesserDigits * pmt(pos - 1, digits.size());
		lesserNums = lesserDigits * (int) Math.pow(digits.size(), pos - 1);
		if (digits.contains(currDigit)) {
			// usedDigits.add(currDigit);
			lesserNums += lesserNums(digits, num, pos - 1, B);
		}
		return lesserNums;
	}

	private int pmt(int places, int nums) {
		return fact(nums) / fact(nums - places);
	}

	private int fact(int n) {
		int fact = 1;
		while (n > 0) {
			fact *= n;
			n--;
		}
		return fact;
	}

	private int getCurrDigit(int num, int pos) {
		int digit = (num / (int) Math.pow(10, pos - 1)) % 10;
		return digit;
	}

	private int getLesserDigits(int currDigit, ArrayList<Integer> digits) {
		int count = 0;
		for (Integer i : digits) {
			if (i < currDigit) {
				count++;
			}
		}
		return count;
	}
}
