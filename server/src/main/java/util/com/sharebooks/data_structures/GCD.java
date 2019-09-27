package com.sharebooks.data_structures;

public class GCD {
	public static void main(String[] args) {
		System.out.println(new GCD().gcd(4, 6));
	}

	public int gcd(int n, int m) {
		int root_n = (int) Math.sqrt(n);
		int gcd = 1;
		for (int i = 2; i <= root_n; i++) {
			int j = 1;
			if (n % i == 0) {
				j = n / i;
				if (m % i == 0 && i > gcd) {
					gcd = i;
				}
				if (m % j == 0 && j > gcd) {
					gcd = j;
				}
			}
		}

		return gcd;
	}
}
