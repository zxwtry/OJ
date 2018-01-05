package hiho;

import java.io.File;
import java.util.HashMap;
import java.util.Scanner;

/*
 * 	Trie树
 * 	http://hihocoder.com/problemset/problem/1014
 * 	输入的第一行为一个正整数n，表示词典的大小，其后n行，
 * 	每一行一个单词（不保证是英文单词，也有可能是火星文单词哦），
 * 	单词由不超过10个的小写英文字母组成，可能存在相同的单词，
 * 	此时应将其视作不同的单词。接下来的一行为一个正整数m，
 * 	表示小Hi询问的次数，其后m行，每一行一个字符串，
 * 	该字符串由不超过10个的小写英文字母组成，表示小Hi的一个询问。
	
	在20%的数据中n, m<=10，词典的字母表大小<=2.
	
	在60%的数据中n, m<=1000，词典的字母表大小<=5.
	
	在100%的数据中n, m<=100000，词典的字母表大小<=26.
 */
//AC    10804ms    96MB
public class N002_Trie树 {
	static TrieNode root = new TrieNode();
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(new File("D:/file/data/hiho_n002.txt"));
		int insertTimes = Integer.parseInt(scanner.nextLine());
		while (insertTimes -- > 0) {
			String line = scanner.nextLine().trim();
			constructTrieTree(line);
		}
		int searchTimes = Integer.parseInt(scanner.nextLine());
		while (searchTimes -- > 0) {
			String line = scanner.nextLine().trim();
			System.out.println(searchTrieTree(line));
		}
		scanner.close();
	}
	private static int searchTrieTree(String line) {
		TrieNode rootNow = root;
		for (int i = 0; i < line.length(); i ++) {
			if (rootNow == null) {
				return 0;
			}
			rootNow = rootNow.search(line.charAt(i));
		}
		return rootNow == null ? 0 : rootNow.countOfUseedAsPrefix;
	}
	static void constructTrieTree(String line) {
		TrieNode rootNow = root;
		for (int i = 0; i < line.length(); i ++) {
			rootNow = rootNow.insert(line.charAt(i));
		}
	}
	static class TrieNode {
		char c = '\0';
		HashMap<TrieNode, TrieNode> map = null;
		int countOfUseedAsPrefix = 0;
		public TrieNode() {
		}
		public TrieNode(char c) {
			this.c = c;
		}
		public TrieNode insert(char c) {
			TrieNode t = new TrieNode(c);
			if (map == null) {
				map = new HashMap<>();
			}
			if (! map.containsKey(t)) {
				t.countOfUseedAsPrefix = 1;
				map.put(t, t);
				return t;
			} else {
				TrieNode fromMap = map.get(t);
				fromMap.countOfUseedAsPrefix ++;
				map.put(fromMap, fromMap);
				return fromMap;
			}
		}
		public TrieNode search(char c) {
			TrieNode t = new TrieNode(c);
			if (map == null || ! map.containsKey(t)) {
				return null;
			} else {
				return map.get(t);
			}
		}
		@Override
		public String toString() {
			return String.valueOf(c);
		}
		@Override
		public boolean equals(Object obj) {
			if (! (obj instanceof TrieNode)) {
				return false;
			}
			TrieNode t = (TrieNode) obj;
			return this.c == t.c;
		}
		@Override
		public int hashCode() {
			return c;
		}
	}
}