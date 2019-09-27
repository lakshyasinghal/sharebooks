package com.sharebooks.data_structures;

public class PowerOf2 {

	public static void main(String[] args) {
		System.out.println(new PowerOf2().power("248"));
	}

	public int power(String numStr) {
		char[] numChars = numStr.toCharArray();
		int l = numStr.length();
		int start = 0, ptr = 0, rem = 0, pv = 0;
		int k = 0;

		while (start <= l - 1) {
			ptr = l - 1;
			int v = Character.getNumericValue(numChars[ptr]);
			if (k == 0 && v == 1)
				break;
			if (v % 2 == 1)
				return 0;
			int nv = v / 2;
			numChars[ptr] = Character.forDigit(nv, 10);
			ptr--;
			while (ptr >= start) {
				v = Character.getNumericValue(numChars[ptr]);
				nv = v / 2;
				rem = v % 2;
				numChars[ptr] = Character.forDigit(nv, 10);
				if (rem == 1) {
					pv = Character.getNumericValue(numChars[ptr + 1]);
					numChars[ptr + 1] = Character.forDigit(pv + 5, 10);
				}
				ptr--;
			}
			k++;
			if (numChars[ptr + 1] == '0')
				start++;
		}

		return k > 0 ? 1 : 0;
	}
}
