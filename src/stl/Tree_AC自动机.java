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
		public void addTrieNode (String word, int id) {
			addTrieNode(trieNode, word, id);
		}
		public void addTrieNode (TrieNode trieNode, String word, int id) {
			if (word == null || word.length() == 0)
				return;
			int k = word.charAt(0) - 'a';
			if (trieNode.childNodes[k] == null) {
				trieNode.childNodes[k] = new TrieNode();
				trieNode.childNodes[k].nodeChar = word.charAt(0);
			}
			String nextWord = word.substring(1);
			if (nextWord == null || nextWord.length() == 0) {
				trieNode.childNodes[k].frequence ++;
				trieNode.childNodes[k].hashSet.add(id);
			}
			addTrieNode(trieNode.childNodes[k], nextWord, id);
		}
        public void buildFailNodeBFS() {
            buildFailNodeBFS(trieNode);
        }
		public void buildFailNodeBFS (TrieNode trieNode) {
			queue.add(trieNode);
			while (!queue.isEmpty()) {
				TrieNode temp = queue.poll();
				TrieNode failNode = null;
				for (int i = 0; i < 26; i ++) {
					if (temp.childNodes[i] == null)
						continue;
					if (temp == trieNode) {
						temp.childNodes[i].faliNode = trieNode;
					} else {
						failNode = temp.faliNode;
						while (failNode != null) {
                            if (failNode.childNodes[i] != null)
                            {
                                temp.childNodes[i].faliNode = failNode.childNodes[i];
                                break;
                            }
                            failNode = failNode.faliNode;
						}
                        if (failNode == null)
                            temp.childNodes[i].faliNode = trieNode;
					}
					queue.add(temp.childNodes[i]);
				}
			}
		}
		public HashSet<Integer> searchAC (String s) {
			HashSet<Integer> hash = new HashSet<Integer>();
			searchAC(trieNode, s, hash);
			return hash;
		}
		public void searchAC (TrieNode root , String s , HashSet<Integer> hashSet) {
			TrieNode head = root;
			char[] c = s.toCharArray();
			for (int i = 0; i < c.length; i ++) {
				int index = c[i] - 'a';
				while ((head.childNodes[index] == null) && (head != root)) {
					head = head.faliNode;
				}
                head = head.childNodes[index];
                if (head == null)
                    head = root;
                TrieNode temp = head;
                while (temp != root && temp.frequence != -1) {
                	Iterator<Integer> it = temp.hashSet.iterator();
                	while (it.hasNext()) {
                		hashSet.add(it.next());
                	}
                	temp.frequence = -1;
                	temp = temp.faliNode;
                }
			}
		}
	}
}