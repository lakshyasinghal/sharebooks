package com.sharebooks.data_structures;

import java.util.ArrayList;
import java.util.List;

public class RootToLeafSums {

	public static void main(String[] rags) {
		TreeNode node0 = new TreeNode(1);

		TreeNode node1 = new TreeNode(2);
		TreeNode node2 = new TreeNode(3);

		TreeNode node3 = new TreeNode(4);
		TreeNode node4 = new TreeNode(5);
		// TreeNode node5 = new TreeNode(1);

//		TreeNode node6 = new TreeNode(3);
//		TreeNode node7 = new TreeNode(5);
//		TreeNode node8 = new TreeNode(6);
//		TreeNode node9 = new TreeNode(7);
//		TreeNode node10 = new TreeNode(9);

		node0.left = node1;
		node0.right = node2;
		node2.left = node3;
		node3.right = node4;
		// node2.left = node5;
//		node3.left = node6;
//		node3.right = node7;
//		node4.left = node8;
//		node4.right = node9;
//		node5.left = node10;

		System.out.println(new RootToLeafSums().sumNumbers(node0));
	}

	public int sumNumbers(TreeNode A) {
		List<Integer> sums = new ArrayList<Integer>();
		rootToLeafSums(A, new ArrayList<Integer>(), sums);

		int total = 0;
		for (Integer val : sums) {
			total += val;
		}

		return total % 1003;
	}

	private void rootToLeafSums(TreeNode curr, List<Integer> path, List<Integer> sums) {
		path.add(curr.val);

		if (curr.left == null && curr.right == null) {
			sums.add(getNumberFromPath(path));
		}

		if (curr.left != null) {
			rootToLeafSums(curr.left, path, sums);
		}
		if (curr.right != null) {
			rootToLeafSums(curr.right, path, sums);
		}
		path.remove(path.size() - 1);
	}

	private int getNumberFromPath(List<Integer> path) {
		double sum = 0;
		int i = path.size();
		for (Integer val : path) {
			sum += (val * Math.pow(10, i - 1));
			i--;
		}
		return (int) sum;
	}
}
