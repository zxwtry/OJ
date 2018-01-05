package hiho;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

//8361ms	221MB
public class N004_Trie图_a_z {
	static TrieNode root = new TrieNode();
	public static void main(String[] args) throws FileNotFoundException {
		Scanner scanner = new Scanner(new File("D:/file/data/hiho_n004.txt"));
		int timesOfContruction = Integer.parseInt(scanner.nextLine().trim());
		while (timesOfContruction -- > 0) {
			String string = scanner.nextLine().trim();
			root.insert(string);
		}
		root.suffix = root;
		Queue<TrieNode> trieNodeQueue = new LinkedList<>();
		for (int index = 0; index < 26; index ++) {
			if (root.nexts[index] == null) {
				root.nexts[index] = root;
			} else {
				root.nexts[index].suffix = root;
				trieNodeQueue.add(root.nexts[index]);
			}
		}
		TrieNode rootNow = null;
		while (! trieNodeQueue.isEmpty()) {
			rootNow = trieNodeQueue.poll();
			for (int index = 0; index < 26; index ++) {
				if (rootNow.nexts[index] == null) {
					rootNow.nexts[index] = rootNow.suffix.nexts[index];
				} else {
					rootNow.nexts[index].suffix = rootNow.suffix.nexts[index];
					trieNodeQueue.add(rootNow.nexts[index]);
				}
			}
		}
		String testString = scanner.nextLine().trim();
		rootNow = root;
		boolean isFind = false;
		for (int index = 0; index < testString.length(); index ++) {
			rootNow = rootNow.nexts[testString.charAt(index) - 'a'];
			if (rootNow.isEndOfAWord) {
				isFind = true;
				break;
			}
		}
		System.out.println(isFind ? "YES" : "NO");
		scanner.close();
	}
	static class TrieNode {
		boolean isEndOfAWord = false;
		TrieNode[] nexts = new TrieNode[26];
		TrieNode suffix = null;
		public void insert(String s) {
			TrieNode rootNow = root;
			for (int i = 0; i < s.length(); i ++) {
				int nextsIndex = s.charAt(i) - 'a';
				if (null != rootNow.nexts[nextsIndex]) {
					rootNow = rootNow.nexts[nextsIndex];
				} else {
					TrieNode addTrieNode = new TrieNode();
					rootNow.nexts[nextsIndex] = addTrieNode;
					rootNow = addTrieNode;
				}
			}
			rootNow.isEndOfAWord = true;
		}
	}
}