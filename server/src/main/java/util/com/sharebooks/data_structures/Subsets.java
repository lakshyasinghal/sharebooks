package com.sharebooks.data_structures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Subsets {

	public static void main(String[] args) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		// 15, 20, 12, 19, 4
		list.add(15);
		list.add(20);
		list.add(12);
		list.add(19);
		list.add(4);
		new Subsets().subsets(list);
	}

	public ArrayList<ArrayList<Integer>> subsets(ArrayList<Integer> list) {
		Collections.sort(list);
		ArrayList<ArrayList<Integer>> powerSet = powerSet(list);
		Collections.sort(powerSet, new Comparator<ArrayList<Integer>>() {
			@Override
			public int compare(ArrayList<Integer> set1, ArrayList<Integer> set2) {
				int l1 = set1.size(), l2 = set2.size();
				int sm = l1 < l2 ? l1 : l2;
				for (int i = 0; i < sm; i++) {
					int diff = set1.get(i) - set2.get(i);
					if (diff != 0) {
						return diff;
					}
				}
				if (l1 < l2) {
					return -1;
				}
				return 1;
			}
		});

		Collections.sort(list, new Comparator<Integer>() {
			public int compare(Integer a, Integer b) {
				return b - a;
			}
		});

		return powerSet;
	}

	private ArrayList<ArrayList<Integer>> powerSet(ArrayList<Integer> list) {
		ArrayList<ArrayList<Integer>> powerSet = new ArrayList<ArrayList<Integer>>();
		powerSet.add(new ArrayList<Integer>());
		ArrayList<Integer> currSubset = null;
		int l = list.size();
		for (int i = 0; i < l; i++) {
			int num = list.get(i);
			ArrayList<ArrayList<Integer>> newPowerSet = new ArrayList<ArrayList<Integer>>();
			for (ArrayList<Integer> subset : powerSet) {
				currSubset = new ArrayList<Integer>();
				currSubset.addAll(subset);
				currSubset.add(num);
				newPowerSet.add(currSubset);
			}
			powerSet.addAll(newPowerSet);
		}
		return powerSet;
	}
}
