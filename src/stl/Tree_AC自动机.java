package stl;

import java.util.ArrayDeque;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Queue;

public class Tree_AC自动机 {
	static class TrieTree {
		public TrieNode trieNode = new TrieNode ();
		public Queue<TrieNode> queue = new ArrayDeque<TrieNode> ();
		static class TrieNode {
			public TrieNode[] childNodes;
			public int frequence;
			public char nodeChar;
			public TrieNode faliNode;
			public HashSet<Integer> hashSet = new HashSet<Integer>();
			public TrieNode () {
				childNodes = new TrieNode[26];
				frequence = 0;
			}
		}
	}
}