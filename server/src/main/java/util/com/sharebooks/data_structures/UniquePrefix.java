package com.sharebooks.data_structures;

import java.util.ArrayList;
import java.util.List;

public class UniquePrefix {

	public static void main(String[] args) {
//		ArrayList<String> list = new ArrayList<String>();
//		list.add("bearcat");
//		list.add("bert");
//		ArrayList<String> prefixList = new UniquePrefix().prefix(list);
//		System.out.println(prefixList);
		System.out.println((int) Math.pow(10, 3));
	}

	public ArrayList<String> prefix(ArrayList<String> A) {
		Trie trie = new Trie();
		for (String str : A) {
			trie.addWord(str);
		}

		ArrayList<String> prefixes = new ArrayList<String>();
		for (String str : A) {
			prefixes.add(trie.getUniquePrefix(str));
		}

		return prefixes;
	}
}

class Trie {
	TrieNode root = new TrieNode();

	public String getUniquePrefix(String word) {
		char[] chars = word.toCharArray();
		List<Character> prefix = new ArrayList<Character>();
		TrieNode curr = root;
		int i = 0, l = word.length();

		while (i < l) {
			char ch = chars[i];
			prefix.add(ch);
			int index = getCharIndex(ch);
			curr = curr.nodes[index];
			if (curr.childCnt < 2) {
				break;
			}
			i++;
		}

		char[] prefixChars = new char[prefix.size()];
		for (i = 0, l = prefix.size(); i < l; i++) {
			prefixChars[i] = prefix.get(i);
		}

		return new String(prefixChars);
	}

	public void addWord(String word) {
		char[] chars = word.toCharArray();
		TrieNode curr = null, prev = root;
		int i = 0, l = word.length();

		while (i < l) {
			char ch = chars[i];
			int index = getCharIndex(ch);
			curr = prev.nodes[index];
			if (curr == null) {
				curr = new TrieNode();
				prev.nodes[index] = curr;
			}
			prev.childCnt += 1;
			prev = curr;
			i++;
		}
	}

	private int getCharIndex(char ch) {
		return ch - 97;
	}
}

class TrieNode {
	TrieNode[] nodes = new TrieNode[26];
	int childCnt;
}