package com.sharebooks.data_structures;

import java.util.ArrayList;

public class BirthdayBombs {

	public static void main(String[] args) {
		int capacity = 3682;
		String input2 = "13511, 9286, 6132, 2958, 21799, 5160, 22244, 5969, 14955, 12808, 3456, 11238, 6511, 4637, 2558, 18808, 15537, 5598, 14022, 4885, 17572, 3775, 23999, 21993, 22203, 24768, 22045, 10785, 11393, 7080, 12218, 16247, 7709";
		// String input = "17786, 11924, 22802, 13344, 10395, 10501, 16658, 16430, 2371,
		// 15036, 18699, 20068, 22772, 13935, 5549, 8435, 14181, 5745, 5220, 20377,
		// 2576, 6438, 15791, 21539, 22871, 15132, 24076, 3144, 13938, 4811, 9031,
		// 23067, 8078, 23176, 11402, 18465, 20, 3051, 1238, 2382, 9430, 19928, 13793,
		// 23546, 207, 19333, 23324, 14379, 16422, 3535, 1100, 18989, 9965, 16882, 6871,
		// 24179, 7005, 22290, 2314, 20934, 2093, 11336, 10345, 1514, 855, 21738, 11322,
		// 866, 16133, 3904, 19591, 554, 23823, 8376, 15443, 15373, 19052, 13759, 21096,
		// 1817, 17285, 22187, 20797, 18593, 5412, 2660";
		String[] numbers = input2.split(",");
		ArrayList<Integer> powers = new ArrayList<Integer>();
		for (String number : numbers) {
			powers.add(Integer.parseInt(number.trim()));
		}

		ArrayList<Integer> result = new BirthdayBombs().solve(capacity, powers);
	}

	public ArrayList<Integer> solve(int capacity, ArrayList<Integer> powers) {
		ArrayList<Integer> hits = new ArrayList<Integer>();
		int minIndex = maxHits(powers, hits, capacity);
		int sum = powers.get(minIndex) * hits.size();
		int listIndex = 0, minVal = powers.get(minIndex);
		for (int i = 0; i < minIndex; i++) {
			int val = powers.get(i);
			int diff = val - minVal;
			while (listIndex < hits.size() && sum + diff <= capacity) {
				hits.set(listIndex, i);
				listIndex++;
				sum += diff;
			}
		}

		return hits;
	}

	private int maxHits(ArrayList<Integer> powers, ArrayList<Integer> hits, int capacity) {
		int i = 0;
		int min = powers.get(0), minIndex = 0;
		for (Integer power : powers) {
			if (power < min) {
				minIndex = i;
				min = power;
			}
			i++;
		}

		int val = powers.get(minIndex);
		int count = capacity / val;
		for (i = 0; i < count; i++) {
			hits.add(minIndex);
		}
		return minIndex;
	}
}
