package com.sharebooks.data_structures;

import java.util.ArrayList;
import java.util.List;

public class LongestSubsequence {

	public static void main(String[] args) {
		LongestSubsequence ls = new LongestSubsequence();
		List<Integer> A = new ArrayList<Integer>();
		A.add(9);
		A.add(7);
		A.add(13);
		A.add(2);
		A.add(6);
		A.add(3);
		A.add(12);
		// 9, 7, 13, 2, 6, 3, 12
		System.out.println(ls.longestSubsequenceLength(A));
	}

	// DO NOT MODIFY THE LIST. IT IS READ ONLY
	public int longestSubsequenceLength(final List<Integer> A) {
		if (A == null) {
			return 0;
		}
		int l = A.size();
		Integer[] decSeqArr = new Integer[l];
		Integer[] decSeqRevArr = new Integer[l];
		int maxSubSeq = 0, currSubSeq = 0;

		for (int i = 0; i < l; i++) {
			decreasingSub(A, i, decSeqArr);
			decreasingSubRev(A, i, decSeqRevArr);
			currSubSeq = decSeqArr[i] + decSeqRevArr[i];
			if (currSubSeq > maxSubSeq) {
				maxSubSeq = currSubSeq;
			}
		}

		return maxSubSeq - 1;
	}

	private int decreasingSub(final List<Integer> list, int pos, Integer[] decSeqCache) {
		if (pos >= list.size()) {
			return 0;
		}
		if (decSeqCache[pos] != null) {
			return decSeqCache[pos];
		}
		int val = list.get(pos), l = list.size(), maxSubSeq = 0, currSubSeq = 0;
		for (int i = pos + 1; i < l; i++) {
			if (val > list.get(i)) {
				currSubSeq = decreasingSub(list, i, decSeqCache);
			}
			maxSubSeq = Math.max(maxSubSeq, currSubSeq);
		}
		maxSubSeq++;
		decSeqCache[pos] = maxSubSeq;
		return maxSubSeq;
	}

	private int decreasingSubRev(final List<Integer> list, int pos, Integer[] decSeqCache) {
		if (pos < 0) {
			return 0;
		}
		if (decSeqCache[pos] != null) {
			return decSeqCache[pos];
		}
		int val = list.get(pos), maxSubSeq = 0, currSubSeq = 0;
		for (int i = pos - 1; i >= 0; i--) {
			if (val > list.get(i)) {
				currSubSeq = decreasingSubRev(list, i, decSeqCache);
			}
			maxSubSeq = Math.max(maxSubSeq, currSubSeq);
		}
		maxSubSeq++;
		decSeqCache[pos] = maxSubSeq;
		return maxSubSeq;
	}

}
