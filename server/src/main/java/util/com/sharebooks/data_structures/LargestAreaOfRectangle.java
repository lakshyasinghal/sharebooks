package com.sharebooks.data_structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class LargestAreaOfRectangle {
	public int solve(ArrayList<ArrayList<Integer>> list) {
		int rows = list.size(), cols = list.get(0).size();
		int[][] matrix = new int[rows][cols];
		fillMatrix(matrix, list);
		int max = 0;
		for (int i = 0; i < rows; i++) {
			int currMax = findMax(matrix, i, cols);
			max = currMax > max ? currMax : max;
		}
		return max;
	}

	private int findMax(int[][] matrix, int r, int cols) {
		List<Integer> row = new ArrayList<Integer>();
		for (int i = 0; i < cols; i++) {
			if (matrix[r][i] > 0)
				row.add(matrix[r][i]);
		}

		class Bar {
			int val;
			int position;
		}

		int max = 0;
		Stack<Bar> stack = new Stack<Bar>();
		for (int i = 0, l = row.size(); i <= l; i++) {
			Bar bar = new Bar();
			if (i == l)
				bar.val = 0;
			else
				bar.val = row.get(i);
			bar.position = i;
			while (!stack.isEmpty() && bar.val < stack.peek().val) {
				Bar poppedBar = stack.pop();
				int currArea = poppedBar.val * (i - poppedBar.position);
				if (currArea > max)
					max = currArea;
				bar.position = poppedBar.position;
			}
			stack.push(bar);
		}
		return max;
	}

	private void fillMatrix(int[][] matrix, ArrayList<ArrayList<Integer>> list) {
		int rows = list.size(), cols = list.get(0).size();

		for (int i = 0; i < rows; i++) {
			for (int j = 0; j < cols; j++) {
				int val = list.get(i).get(j);
				if (i == 0)
					matrix[i][j] = val;
				else
					matrix[i][j] = val == 0 ? 0 : matrix[i - 1][j] + 1;
			}
		}
	}
}
