package leetcode;


/*
 * 	突然看到a-z
 * 	想了一个非常邪恶的方式:::::用一个定长数组完成所有的工作
 */
public class P208_ImplementTrie_Solution4 {
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
