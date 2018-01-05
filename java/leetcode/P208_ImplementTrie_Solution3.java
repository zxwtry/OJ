package leetcode;

import java.util.HashMap;

public class P208_ImplementTrie_Solution3 {
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
		System.out.println(t.search("f"));
	}
	/*
	 * 	35 ms
	 * 	34.18%
	 */
	static class TrieNode {
		HashMap<Character, TrieNode> map;
		boolean isEndOfAWrod = false;
	    public TrieNode() {
	    }
	    public TrieNode insert(char c) {
	    	if (map == null) {
	    		map = new HashMap<>();
	    		TrieNode tc = new TrieNode();
	    		map.put(c, tc);
	    		return tc;
	    	}
	    	if (map.containsKey(c)) {
	    		return map.get(c);
	    	} else {
	    		TrieNode tc = new TrieNode();
	    		map.put(c, tc);
	    		return tc;
	    	}
	    }
	    public TrieNode get(char c) {
	    	if (map != null && map.containsKey(c)) {
	    		return map.get(c);
	    	} else {
	    		return null;
	    	}
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
	        	rootNow = rootNow.insert(word.charAt(i));
	        }
	        rootNow.isEndOfAWrod = true;
	    }
	    public boolean search(String word) {
	        TrieNode rootNow = root;
	        for (int i = 0; i < word.length(); i ++) {
	        	rootNow = rootNow.get(word.charAt(i));
	        	if (rootNow == null) {
	        		return false;
	        	}
	        }
	        return rootNow.isEndOfAWrod;
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
