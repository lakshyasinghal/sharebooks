package com.sharebooks.data_structures;

import java.util.ArrayList;
import java.util.List;

public class MaxSumPath {
	public static void main(String[] args) {
		TreeNode root = new TreeNode(1);
		root.left = new TreeNode(2);
		root.right = new TreeNode(3);
		root.right.left = new TreeNode(4);
		root.right.left.right = new TreeNode(5);
		System.out.println(new MaxSumPath().maxPathSum(root));
	}

	public int maxPathSum(TreeNode root) {
		List<Integer> maxContainer = new ArrayList<Integer>();
		maxContainer.add(Integer.MIN_VALUE);
		max(root, maxContainer);
		return maxContainer.get(0);
	}

	private int max(TreeNode curr, List<Integer> maxContainer) {

		if (curr == null)
			return 0;

		System.out.println("Inside node " + curr.val);
		int leftMax = max(curr.left, maxContainer);
		int rightMax = max(curr.right, maxContainer);

		leftMax = leftMax > 0 ? leftMax : 0;
		rightMax = rightMax > 0 ? rightMax : 0;

		int currMax = curr.val + leftMax + rightMax;
		System.out.println("currMax =>" + currMax);
		if (currMax > maxContainer.get(0)) {
			maxContainer.set(0, currMax);
		}

		return curr.val + Math.max(leftMax, rightMax);
	}
}
