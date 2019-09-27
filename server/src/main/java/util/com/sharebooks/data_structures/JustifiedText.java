package com.sharebooks.data_structures;

import java.util.ArrayList;

public class JustifiedText {

	public static void main(String[] args) {
		ArrayList<String> words = new ArrayList<String>();
		// "What", "must", "be", "shall", "be."
		words.add("lakshya");
		words.add("likes");
		words.add("nidhi");
		words.add("but");
		words.add("nidhi");
		words.add("does");
		words.add("not");

		System.out.println(new JustifiedText().fullJustify(words, 12));
	}

	public ArrayList<String> fullJustify(ArrayList<String> words, int L) {
		ArrayList<String> list = new ArrayList<String>();
		int total = words.size();
		int ptr1 = 0, ptr2 = 0, CL = 0;
		// char[] temp = null;
		while (ptr1 < total) {
			ptr2 = ptr1;
			CL = 0;
			while (ptr2 < total && CL <= L) {
				CL += words.get(ptr2).length();
				ptr2++;
			}
			ptr2--;
			if (CL > L) {
				CL -= words.get(ptr2).length();
				ptr2--;
			}

			if (ptr2 == total - 1)
				list.add(fillLineForLast(words, ptr1, ptr2, L, CL));
			else
				list.add(fillLine(words, ptr1, ptr2, L, CL));
			ptr1 = ptr2 + 1;
		}

		return list;
	}

	private String fillLine(ArrayList<String> words, int p1, int p2, int L, int CL) {
		int leftWords = p2 - p1 > 0 ? p2 - p1 : 1;
		int spaces = (L - CL) / (leftWords);
		char[] res = new char[L];
		int ptr = 0, i = p1;
		for (; i < p2; i++) {
			for (char ch : words.get(i).toCharArray()) {
				res[ptr] = ch;
				ptr++;
			}
			for (int j = 0; j < spaces; j++) {
				res[ptr] = ' ';
				ptr++;
			}
		}
		for (char ch : words.get(i).toCharArray()) {
			res[ptr] = ch;
			ptr++;
		}
		return new String(res);
	}

	private String fillLineForLast(ArrayList<String> words, int p1, int p2, int L, int CL) {
		int leftWords = p2 - p1;
		int es = L - CL; // extra spaces
		// int ls=0; //last spaces
		int start = p1;
		if (es < leftWords) {
			start = p1 + leftWords - es;
		}

		char[] res = new char[L];
		int ptr = 0, i = p1;
		for (; i < start; i++) {
			for (char ch : words.get(i).toCharArray()) {
				res[ptr] = ch;
				ptr++;
			}
		}

		for (; i < p2; i++) {
			for (char ch : words.get(i).toCharArray()) {
				res[ptr] = ch;
				ptr++;
			}
			res[ptr] = ' ';
			ptr++;
		}

		for (char ch : words.get(i).toCharArray()) {
			res[ptr] = ch;
			ptr++;
		}

		while (ptr < L) {
			res[ptr] = ' ';
			ptr++;
		}
		return new String(res);
	}
}
