package com.sharebooks.data_structures;

import java.util.ArrayList;

public class WordBreak {

	public ArrayList<String> wordBreak(String sentence, ArrayList<String> words) {
		Trie trie = new Trie();
		for (String word : words) {
			trie.addWord(word);
		}
		return breakWords(sentence, trie);
	}

	private ArrayList<String> breakWords(String remSentence, Trie trie) {
		if (remSentence.equals("")) {
			ArrayList<String> emptyStringCont = new ArrayList<String>();
			emptyStringCont.add("");
			return emptyStringCont;
		}
		ArrayList<String> words = trie.getWords(remSentence);
		if (words.size() == 0) {

		}
		ArrayList<String> sentences = new ArrayList<String>();
		ArrayList<String> currSentences = null;
		for (String word : words) {
			currSentences = breakWords(getRemSentence(remSentence, word), trie);
			for (String currSentence : currSentences) {
				sentences.add(word + " " + currSentence);
			}
		}
		return sentences;
	}

	private String getRemSentence(String sentence, String prefWord) {
		return sentence.replaceFirst(prefWord, "");
	}

	class Trie {
		TrieNode root = new TrieNode();

		public void addWord(String word) {
			char[] chars = word.toCharArray();
			TrieNode last = root;
			for (int i = 0, l = chars.length; i < l; i++) {
				char ch = chars[i];
				TrieNode curr = last.nodes[ch - 96];
				if (curr == null) {
					curr = new TrieNode();
					last.nodes[ch - 96] = curr;
				}
				last = curr;
			}
			last.end = true;
		}

		public ArrayList<String> getWords(String sentence) {
			char[] chars = sentence.toCharArray();
			ArrayList<String> words = new ArrayList<String>();
			TrieNode last = root;
			for (int i = 0, l = chars.length; i < l; i++) {
				char ch = chars[i];
				TrieNode curr = last.nodes[ch - 96];
				if (curr == null) {
					break;
				}
				if (curr.end) {
					words.add(getWord(chars, i));
				}
				last = curr;
			}
			return words;
		}

		private String getWord(char[] chars, int endPos) {
			char[] temp = new char[endPos + 1];
			for (int i = 0; i <= endPos; i++) {
				temp[i] = chars[i];
			}
			return new String(temp);
		}
	}

	class TrieNode {
		TrieNode[] nodes = new TrieNode[26];
		boolean end;
	}
}
