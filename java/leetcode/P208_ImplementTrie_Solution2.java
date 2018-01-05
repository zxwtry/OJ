package leetcode;

import java.util.Iterator;
import java.util.LinkedList;

public class P208_ImplementTrie_Solution2 {
	public static void main(String[] args) {
		Trie t = new Trie();
		t.insert("abc");
		t.insert("abc");
		System.out.println(t.search("abc"));
		System.out.println(t.search("ab"));
		t.insert("ab");
		System.out.println(t.search("ab"));
		t.insert("ab");
		System.out.println(t.search("ab"));
//		System.out.println(t.search("abc"));
	}
	/*
	 * 	55 ms
	 * 	13.10%
	 */
	static class TrieNode {
		char c = '\0';
		LinkedList<TrieNode> nexts = new LinkedList<>();
		boolean isEndOfAWord = false;
		public TrieNode() {}
		public TrieNode(char c) {
			this.c = c;
		}
		public TrieNode get(char c) {
			Iterator<TrieNode> it = nexts.iterator();
			while (it.hasNext()) {
				TrieNode rootNow = it.next();
				if (rootNow.c == c) {
					return rootNow;
				}
			}
			return null;
		}
		public TrieNode insert(char c) {
			Iterator<TrieNode> it = nexts.iterator();
			while (it.hasNext()) {
				TrieNode rootNow = it.next();
				if (rootNow.c == c) {
					return rootNow;
				}
			}
			TrieNode rootNow = new TrieNode(c);
			nexts.add(rootNow);
			return rootNow;
		}
	}
	static class Trie {
		TrieNode root;
		public Trie() {
			root = new TrieNode();
		}
		public void insert(String word) {
			TrieNode rootNow = root;
			for (int i = 0; i < word.length(); i ++) {
				rootNow = rootNow.insert(word.charAt(i));
			}
			rootNow.isEndOfAWord = true;
		}
		public boolean search(String word) {
			TrieNode rootNow = root;
			for (int i = 0; i < word.length(); i ++) {
				rootNow = rootNow.get(word.charAt(i));
				if (rootNow == null) {
					return false;
				}
			}
			return rootNow.isEndOfAWord;
		}
		public boolean startsWith(String prefix) {
			TrieNode rootNow = root;
			for (int i = 0; i < prefix.length(); i ++) {
				rootNow = rootNow.get(prefix.charAt(i));
				if (rootNow == null) {
					return false;
				}
			}
			return true;
		}
	}
}
