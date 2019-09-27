package com.sharebooks.data_structures;

import java.util.Stack;

public class AbsolutePath {
	private static String CURR = ".";
	private static String PREV = "..";

	public static void main(String[] args) {
		String path = "/a/./b/../../c/";
		System.out.println(new AbsolutePath().simplifyPath(path));
	}

	public String simplifyPath(String path) {

		Stack<String> sPath = new Stack<String>();
		Stack<String> ordered = new Stack<String>();
		String[] tokens = path.split("/");
		for (String token : tokens) {
			if (token.equals("") || token.equals(CURR))
				continue;
			if (token.equals(PREV)) {
				if (!sPath.isEmpty())
					sPath.pop();
			} else
				sPath.push(token);
		}

		while (!sPath.isEmpty()) {
			ordered.push(sPath.pop());
		}

		StringBuilder absPath = new StringBuilder();
		while (!ordered.isEmpty()) {
			absPath.append("/");
			absPath.append(ordered.pop());
		}
		String res = absPath.toString();
		return res.equals("") ? "/" : res;
	}
}
