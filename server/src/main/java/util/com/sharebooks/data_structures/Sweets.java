package com.sharebooks.data_structures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Sweets {

	public static void main(String[] args) {
		List<String> votes = new ArrayList<String>();
		votes.add("gulab");
		votes.add("jamun");
		votes.add("jamun");
		votes.add("petha");
		votes.add("malai");
		votes.add("jamun");
		System.out.println(whichSweetShouldIBring(votes));
	}

	static String whichSweetShouldIBring(List<String> votes) {
		Map<String, Integer> sweetVotes = new HashMap<String, Integer>();
		for (String vote : votes) {
			if (sweetVotes.get(vote) != null) {
				sweetVotes.put(vote, (sweetVotes.get(vote) + 1));
			} else {
				sweetVotes.put(vote, 1);
			}
		}

		String fav = null;
		int max = 0;
		for (String key : sweetVotes.keySet()) {
			int count = sweetVotes.get(key);
			if (count > max) {
				fav = key;
				max = count;
			} else if (count == max) {
				if (key.compareTo(fav) > 0) {
					fav = key;
				}
			}
		}

		return fav;
	}
}
