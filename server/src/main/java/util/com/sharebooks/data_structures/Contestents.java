package com.sharebooks.data_structures;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Contestents {

	public static void main(String[] args) throws NumberFormatException, IOException {
		System.out.println((int) 'a');
//		Contestents contestents = new Contestents();
//		contestents.run();
	}

	public void run() throws NumberFormatException, IOException {

		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		// BufferedWriter bufferedWriter = new BufferedWriter(new
		// FileWriter(System.getenv("OUTPUT_PATH")));

		int queries = Integer.parseInt(bufferedReader.readLine().trim());

		for (int i = 0; i < queries; i++) {
			String[] tokens = bufferedReader.readLine().trim().split(" ");
			long lastRank = Long.parseLong(tokens[0]);
			long lastRankHolders = Long.parseLong(tokens[1]);
			numOfContestents(lastRank, lastRankHolders);
		}

		// bufferedWriter.write(res);
		// bufferedWriter.newLine();

		bufferedReader.close();
		// bufferedWriter.close();
	}

	private void numOfContestents(long lastRank, long lastRankHolders) {
		System.out.println(lastRank + lastRankHolders - 1);
	}
}
