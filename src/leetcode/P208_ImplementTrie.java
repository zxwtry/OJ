package leetcode;

import java.util.HashMap;

public class P208_ImplementTrie {
	public static void main(String[] args) {
		Trie t = new Trie();
		t.insert("abc");
//		t.insert("abc");
//		System.out.println(t.search("abc"));
//		System.out.println(t.search("ab"));
//		t.insert("ab");
//		System.out.println(t.search("ab"));
//		t.insert("ab");
//		System.out.println(t.search("ab"));
		System.out.println(t.search("a"));
	}
	/*
	 * 	57 ms
	 * 	10.63%
	 */
	static class TrieNode {
		char c = '\0';
		HashMap<TrieNode, TrieNode> map;
		boolean isEndOfAWord = false;
	    public TrieNode() {
	    	map = new HashMap<TrieNode, TrieNode>();
	    }
	    public TrieNode(char c) {
	    	this.c = c;
	    }
	    public void config() {
	    	if (map == null) {
	    		map = new HashMap<>();
	    	}
	    }
		@Override
		public boolean equals(Object obj) {
			if (! (obj instanceof TrieNode)) {
				return false;
			}
			TrieNode o = (TrieNode) obj;
			return this.c == o.c;
		}
		@Override
		public int hashCode() {
			return (int) c;
		}
	}
	static class Trie {
	    private TrieNode root;
	    public Trie() {
	        root = new TrieNode();
	    }
	    public void insert(String word) {
	    	TrieNode rootNow = root;
	    	for (int i = 0; i < word.length(); i ++) {
	    		TrieNode addNode = new TrieNode(word.charAt(i));
	    		if (! rootNow.map.containsKey(addNode)) {
	    			rootNow.map.put(addNode, addNode);
	    		}
	    		rootNow = rootNow.map.get(addNode);
	    		rootNow.config();
	    		if (i == word.length() - 1) {
	    			rootNow.isEndOfAWord = true;
	    		}
	    	}
	    }
	    public boolean search(String word) {
	    	TrieNode rootNow = root;
	    	for (int i = 0; i < word.length(); i ++) {
	    		TrieNode searchNode = new TrieNode(word.charAt(i));
	    		if (! rootNow.map.containsKey(searchNode)) {
	    			return false;
	    		}
	    		rootNow = rootNow.map.get(searchNode);
	    	}
	    	return rootNow.isEndOfAWord;
	    }
	    public boolean startsWith(String prefix) {
	    	TrieNode rootNow = root;
	    	for (int i = 0; i < prefix.length(); i ++) {
	    		TrieNode searchNode = new TrieNode(prefix.charAt(i));
	    		if (! rootNow.map.containsKey(searchNode)) {
	    			return false;
	    		}
	    		rootNow = rootNow.map.get(searchNode);
	    	}
	        return true;
	    }
	}
}
