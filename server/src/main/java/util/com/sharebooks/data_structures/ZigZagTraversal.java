package com.sharebooks.data_structures;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class ZigZagTraversal {

	public static void main(String[] args) {
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

		int[][] twoDArr = new ZigZagTraversal().zigzag(node0);
		System.out.println(twoDArr);
	}

	public int[][] zigzag(TreeNode root) {

		Stack<TreeNode> stack1 = new Stack<TreeNode>();
		Stack<TreeNode> stack2 = new Stack<TreeNode>();

		Stack<TreeNode> stack_ = stack1;

		List<List<Integer>> twoDlist = new ArrayList<List<Integer>>();
		int listNum = 0;

		stack1.push(root);
		int stackNum = 1;
		TreeNode curr = null;

		while (!stack_.isEmpty()) {
			if (stackNum == 1) {
				while (!stack_.isEmpty()) {
					curr = stack_.pop();
					insertIn2DList(twoDlist, listNum, curr.val);
					if (curr.left != null) {
						stack2.push(curr.left);
					}
					if (curr.right != null) {
						stack2.push(curr.right);
					}
				}
				stackNum = 2;
				stack_ = stack2;
			} else {
				while (!stack_.isEmpty()) {
					curr = stack_.pop();
					insertIn2DList(twoDlist, listNum, curr.val);
					if (curr.right != null) {
						stack1.push(curr.right);
					}
					if (curr.left != null) {
						stack1.push(curr.left);
					}
				}
				stackNum = 1;
				stack_ = stack1;
			}
			listNum++;
		}

		return get2DArrayFrom2DList(twoDlist);
	}

	public void insertIn2DList(List<List<Integer>> twoDlist, int listNum, int val) {
		if (listNum >= twoDlist.size()) {
			twoDlist.add(new ArrayList<Integer>());
		}

		List<Integer> list = twoDlist.get(listNum);
		list.add(val);
	}

	public int[][] get2DArrayFrom2DList(List<List<Integer>> twoDlist) {
		int l = twoDlist.size();
		int[][] twoDArr = new int[l][];
		int i = 0, j = 0;
		for (List<Integer> list : twoDlist) {
			j = 0;
			twoDArr[i] = new int[list.size()];
			for (Integer val : list) {
				twoDArr[i][j] = val;
				j++;
			}
			i++;
		}
		return twoDArr;
	}
}

class TreeNode {
	int val;
	TreeNode left;
	TreeNode right;

	TreeNode(int x) {
		val = x;
		left = null;
		right = null;
	}
}