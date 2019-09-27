package com.sharebooks.data_structures;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class ValidParentheses {

	public static void main(String[] args) {
		System.out.println(new ValidParentheses().isValid("()[]{}"));
	}

	public int isValid(String str) {
		Set<Character> closeSet = new HashSet<Character>();
		closeSet.add(')');
		closeSet.add(']');
		closeSet.add('}');
		Map<Character, Character> set = new HashMap<Character, Character>();
		set.put(')', '(');
		set.put(']', '[');
		set.put('}', '{');

		Stack<Character> stack = new Stack<Character>();
		for (int i = 0, l = str.length(); i < l; i++) {
			char ch = str.charAt(i);
			if (closeSet.contains(ch)) {
				if (!stack.isEmpty() && set.get(ch).charValue() == stack.peek().charValue())
					stack.pop();
				else
					return 0;
			} else
				stack.push(ch);
		}

		return 1;
	}
}
