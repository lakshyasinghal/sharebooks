package com.sharebooks.data_structures;

import java.util.ArrayList;

public class HeapSort {

	public ListNode mergeKLists(ArrayList<ListNode> list) {
		heapify(list);
		ListNode head = new ListNode(-1);
		ListNode ptr = head;
		int currLast = list.size() - 1;

		while (list.get(0) != null) {
			ListNode curr = list.get(0);
			ListNode next = curr.next;
			ptr.next = curr;
			ptr = ptr.next;
			list.set(0, list.get(currLast));
			list.set(currLast, next);
			if (next == null) {
				currLast--;
			}
			heapify(list, 0, currLast);
		}

		return head.next;
	}

	public void heapify(ArrayList<ListNode> list) {
		int size = list.size();
		for (int i = (size / 2 - 1); i >= 0; i--) {
			heapify(list, i, size - 1);
		}
	}

	private void heapify(ArrayList<ListNode> list, int i, int last) {
		int smaller = i;
		int left = i * 2 + 1;
		int right = i * 2 + 2;

		if (right <= last && list.get(right).val < list.get(smaller).val) {
			smaller = right;
		}
		if (left <= last && list.get(left).val < list.get(smaller).val) {
			smaller = left;
		}

		if (smaller != i) {
			ListNode temp = list.get(smaller);
			list.set(smaller, list.get(i));
			list.set(i, temp);
			heapify(list, smaller, last);
		}
	}
}

class ListNode {
	public int val;
	public ListNode next;

	ListNode(int x) {
		val = x;
		next = null;
	}
}
