package com.sharebooks.data_structures;

public class ReversedWords {

	public static void main(String[] args) {
		String input = "hello ji";
		System.out.println(new ReversedWords().solve(input));
	}

	public String solve(String str) {

		int len = str.length();
		char[] reversed = new char[len];

		int p1 = str.length() - 1, p2 = 0, ptr = 0;

		while (p1 >= 0) {
			// traversing past spaces
			while (p1 >= 0 && str.charAt(p1) == ' ')
				p1--;

			if (p1 < 0)
				break;

			// capturing the word
			p2 = p1;
			while (p2 >= 0 && str.charAt(p2) != ' ')
				p2--;
			p2++;

			// copying the word
			for (int i = p2; i <= p1; i++, ptr++)
				reversed[ptr] = str.charAt(i);
			if (ptr < len) {
				reversed[ptr] = ' ';
				ptr++;
			}

			p1 = p2 - 1;
		}
		ptr--;
		if (ptr >= 0 && ptr < len && reversed[ptr] == ' ')
			ptr--;
		// System.out.println("ptr=>"+ptr);
		if (ptr < 0)
			return "";

		char[] rev = new char[ptr + 1];
		for (int j = 0; j <= ptr; j++) {
			rev[j] = reversed[j];
		}

		return new String(rev);
	}
}
