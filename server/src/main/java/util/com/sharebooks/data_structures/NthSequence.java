package com.sharebooks.data_structures;

public class NthSequence {
	public static void main(String[] args) {
		System.out.println(new NthSequence().countAndSay(2));
	}

	public String countAndSay(int n) {
		if (n < 1)
			return "";
		String curr = "1";
		StringBuilder next = new StringBuilder();
		int ptr1 = 0, ptr2 = 0, i = 2;
		while (i <= n) {
			next = new StringBuilder("");
			ptr1 = 0;
			ptr2 = 0;
			int l = curr.length();
			while (ptr2 < l) {
				while (ptr2 < l && curr.charAt(ptr2) == curr.charAt(ptr1)) {
					ptr2++;
				}
				ptr2--;
				next.append((ptr2 - ptr1 + 1) + Character.toString(curr.charAt(ptr1)));
				ptr1 = ++ptr2;
			}
			curr = next.toString();
			i++;
		}

		return curr;
	}
}
