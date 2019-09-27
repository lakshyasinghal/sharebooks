package com.sharebooks.data_structures;

import java.util.ArrayList;

public class ZeroSubmatrices {
	public int solve(ArrayList<ArrayList<Integer>> list) {
		if (list == null || list.size() == 0)
			return 0;
		int rows = list.size(), cols = list.get(0).size();
		int[][] matrix = new int[rows][cols];
		int zeroCount = 0;
		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				matrix[i][j] = list.get(i).get(j);
			}
			zeroCount += zeroSubmatrices(matrix, i);
		}
		return zeroCount;
	}

	private int zeroSubmatrices(int[][] matrix, int row) {
		int len = matrix[0].length;
		int zeroCount = 0;

		zeroCount += zeroSubarrays(matrix[row]);
		for (int i = row - 1; i >= 0; i++) {
			for (int j = 0; j < len; j++) {
				matrix[i][j] += matrix[row][j];
			}
			zeroCount += zeroSubarrays(matrix[i]);
		}

		return zeroCount;
	}

	private int zeroSubarrays(int[] array) {
		int sum = 0, zeroCount = 0;
		int l = array.length;
		for (int i = 0; i < l; i++) {
			sum = 0;
			for (int j = i; j < l; j++) {
				sum += array[j];
				if (sum == 0) {
					zeroCount++;
				}
			}
		}
		return zeroCount;
	}
}
