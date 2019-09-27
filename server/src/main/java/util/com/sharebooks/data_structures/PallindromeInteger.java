package com.sharebooks.data_structures;

public class PallindromeInteger {

	public static void main(String[] args) {
		System.out.println(new PallindromeInteger().isPalindrome(1234321));
	}

	public int isPalindrome(int num) {
		if (num < 0) {
			return 0;
		}
		int ptr1 = numLen(num), ptr2 = 1;
		while (ptr1 > ptr2) {
			if (nthDigit(num, ptr1) != nthDigit(num, ptr2)) {
				return 0;
			}
			ptr1--;
			ptr2++;
		}
		return 1;
	}

	private int numLen(int num) {
		int n = 0;
		while (num / Math.pow(10, n) > 1) {
			n++;
		}
		return n;
	}

	private int nthDigit(int num, int n) {
		// 23432147
		int q = num / (int) Math.pow(10, n - 1);
		return q % 10;
	}
}
