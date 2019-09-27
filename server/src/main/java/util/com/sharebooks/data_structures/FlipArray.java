package com.sharebooks.data_structures;

import java.util.ArrayList;
import java.util.List;

public class FlipArray {

	public static void main(String[] args) {
		// 8, 4, 5, 7, 6, 2
		List<Integer> list = new ArrayList<Integer>();
		list.add(8);
		list.add(4);
		list.add(5);
		list.add(7);
		list.add(6);
		list.add(2);

		System.out.println(new FlipArray().solve(list));
	}

	// DO NOT MODIFY THE LIST. IT IS READ ONLY
	public int solve(final List<Integer> list) {
		List<Integer> minNonNeg = new ArrayList<Integer>();
		minNonNeg.add(Integer.MAX_VALUE);
		List<Integer> minCount = new ArrayList<Integer>();
		minCount.add(Integer.MAX_VALUE);

		minNonNeg(list, 0, 0, 0, 0, 0, minNonNeg, minCount);
		return minCount.get(0);
	}

	private void minNonNeg(final List<Integer> list, int pos, int sum1, int count1, int sum2, int count2,
			List<Integer> minNonNeg, List<Integer> minCount) {
		int l = list.size();
		if (pos == l) {
			int mnn = minNonNeg.get(0);
			int mc = minCount.get(0);
			int currNN = Math.abs(sum1 - sum2);
			int currMC = sum1 < sum2 ? count1 : count2;
			if (currNN <= mnn) {
				if (currNN < mnn || (currNN == mnn && currMC < mc)) {
					minNonNeg.set(0, currNN);
					minCount.set(0, currMC);
				}
			}
			return;
		}

		sum1 += list.get(pos);
		count1++;
		minNonNeg(list, pos + 1, sum1, count1, sum2, count2, minNonNeg, minCount);
		sum1 -= list.get(pos);
		count1--;
		sum2 += list.get(pos);
		count2++;
		minNonNeg(list, pos + 1, sum1, count1, sum2, count2, minNonNeg, minCount);
		sum2 -= list.get(pos);
		count2--;
		return;
	}
}
