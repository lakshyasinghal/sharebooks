package com.sharebooks.data_structures;

import java.util.ArrayList;
import java.util.List;

public class LCA {

	public static void main(String[] rags) {
		TreeNode node0 = new TreeNode(2);

		TreeNode node1 = new TreeNode(4);
		TreeNode node2 = new TreeNode(5);

		TreeNode node3 = new TreeNode(8);
		TreeNode node4 = new TreeNode(9);
		TreeNode node5 = new TreeNode(12);

		TreeNode node6 = new TreeNode(20);
		TreeNode node7 = new TreeNode(21);
		TreeNode node8 = new TreeNode(22);
		TreeNode node9 = new TreeNode(24);
		TreeNode node10 = new TreeNode(25);

		node0.left = node1;
		node0.right = node2;
		node1.left = node3;
		node1.right = node4;
		node2.left = node5;
		node3.left = node6;
		node3.right = node7;
		node4.left = node8;
		node4.right = node9;
		node5.left = node10;

		System.out.println(new LCA().lca(node0, 25, 2));
	}

	public int lca(TreeNode A, int B, int C) {
		ArrayList<TreeNode> lcaContainer = new ArrayList<TreeNode>();
		lca(B, C, A, lcaContainer);
		int val = -1;
		if (lcaContainer.size() > 0) {
			val = lcaContainer.get(0).val;
		}
		return val;
	}

	private int lca(int a, int b, TreeNode curr, ArrayList<TreeNode> lcaContainer) {
		if (curr == null) {
			return 0;
		}
		int cnt = 0;
		List<Character> chars = new ArrayList<Character>();

		int leftCount = lca(a, b, curr.left, lcaContainer);
		if (leftCount == 2) {
			return 2;
		}
		int rightCount = lca(a, b, curr.right, lcaContainer);
		if (rightCount == 2) {
			return 2;
		}
		cnt = leftCount + rightCount;

		if (cnt != 2 && (curr.val == a || curr.val == b)) {
			cnt++;
		}

		if (cnt == 2) {
			lcaContainer.add(curr);
		}

		return cnt;
	}
}
