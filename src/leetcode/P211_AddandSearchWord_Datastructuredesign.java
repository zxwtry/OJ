package leetcode;

/*
 * 	Design a data structure that supports the following two operations:

	void addWord(word)
	bool search(word)
	search(word) can search a literal word or a regular expression string 
	containing only letters a-z or .. 
	A . means it can represent any one letter.
	
	For example:
	
	addWord("bad")
	addWord("dad")
	addWord("mad")
	search("pad") -> false
	search("bad") -> true
	search(".ad") -> true
	search("b..") -> true
	Note:
	You may assume that all words are consist of lowercase letters a-z.
 */

public class P211_AddandSearchWord_Datastructuredesign {
	public static void main(String[] args) {
		WordDictionary wd = new WordDictionary();
//		wd.addWord("bad");
//		wd.addWord("dad");
//		wd.addWord("mad");
		System.out.println(wd.search("pad"));
		System.out.println(wd.search("bad"));
		System.out.println(wd.search(".ad"));
		System.out.println(wd.search("b.."));
	}
	/*
	 * 	30 ms
	 * 	74.11%
	 */
	static class WordDictionary {
		TrieNode root = new TrieNode();
	    public void addWord(String word) {
	    	TrieNode rootNow = root;
	        for (int i = 0; i < word.length(); i ++) {
	        	rootNow = rootNow.insert(word.charAt(i));
	        }
	        rootNow.isEndOfAWord = true;
	    }
	    public boolean search(String word) {
	    	char[] cs = word.toCharArray();
	        return search(cs, 0, root);
	    }
	    private boolean search(char[] cs, int i, TrieNode rootNow) {
	    	if (cs[i] == '.') {
	    		if (i == cs.length - 1) {
	    			for (TrieNode node : rootNow.arr) {
	    				if (node != null && node.isEndOfAWord) {
	    					return true;
	    				}
	    			}
	    			return false;
	    		}
	    		for (TrieNode node : rootNow.arr) {
	    			if (node != null) {
	    				if (search(cs, i + 1, node)) {
	    					return true;
	    				}
	    			}
	    		}
	    		return false;
	    	}
	    	int cIndex = cs[i] - 'a';
	    	if (rootNow.arr[cIndex] != null) {
	    		if (i == cs.length - 1) {
	    			return rootNow.arr[cIndex].isEndOfAWord;
	    		}
	    		return search(cs, i + 1, rootNow.arr[cIndex]);
	    	}
			return false;
		}
		static class TrieNode {
			TrieNode[] arr = null;
			boolean isEndOfAWord = false;
		    public TrieNode() {
		    	arr = new TrieNode[26];
		    }
		    public TrieNode insert(char c) {
		    	int locat = c - 'a';
		    	if (arr[locat] == null) {
		    		arr[locat] = new TrieNode();
		    	}
		    	return arr[locat];
		    }
		    public TrieNode get(char c) {
		    	return arr[c - 'a'];
		    }
		}
	}
}
