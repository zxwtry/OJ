package nowcoder.com;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

/*
 * 	题目描述
									
	大部分论坛、网站等，为了方便管理，都进行了关于敏感词的设定。
	在多数网站，敏感词一般是指带有敏感政治倾向、暴力倾向、不健康色彩的词或不文明语，
	也有一些网站根据自身实际情况，设定一些只适用于本网站的特殊敏感词。
	比如，当你发贴的时候带有某些事先设定的词时，这个贴是不能发出的。
	或者这个词被自动替换为星号 (*)，或者说是被和谐掉了。请注意敏感词只有小写字母，
	文本如果中的大写字母当做小写字母处理，出现敏感单词，即使作为子串出现也要被和谐，多个子串重叠他们都要被和谐。
	
	例如当敏感词是gre，eat 是
	Your English is Great.
	将被和谐成
	Your English is *****.
	
	请编程，输入给定的文本和关键字，将所有被和谐的部分都打上星号 (*)
	
	
									
	输入
	输入的第一行包含一个整数 n，表示敏感词的总数。
	接下来的 n 行，每行包含一个长度不超过 100 的敏感词，单词不区分大小写。
	接下来的一行包含一段长度不超过 10000的字符串表示待处理的文本。
	
	样例输入
	4
	revolution
	greatwall
	democracy
	science
	Cross the greatwall, we can reach every corner of the world.
	
	输出
	输出一行，表示和谐过后的文本。
	样例输出
	Cross the *********, we can reach every corner of the world.
	时间限制
	C/C++语言：1000MS其它语言：3000MS	
	内存限制
	C/C++语言：65536KB其它语言：589824KB
 */

public class 百度17_敏感词 {
	public static void main(String[] args) {
		solve1();
	}
	
	/**
	 * @method		solve1 
	 * @parameter	void
	 * @return 		void
	 * @details 	第一个Solution
	 */
	private static void solve1() {
		Scanner sc = new Scanner(System.in);
		TrieNode root = new TrieNode();
		for (int times = Integer.parseInt(sc.nextLine().trim()) - 1; times > -1; times --) {
			solve1insert(sc.nextLine().trim(), root);
		}
		root.suffix = root;
		Queue<TrieNode> trieNodeQueue = new LinkedList<TrieNode>();
		for (int i = 0; i < 26; i ++) {
			if (null == root.nexts[i]) {
				root.nexts[i] = root;
			} else {
				root.nexts[i].suffix = root;
				trieNodeQueue.add(root.nexts[i]);
			}
		}
		TrieNode rootNow = null;
		while (! trieNodeQueue.isEmpty()) {
			rootNow = trieNodeQueue.poll();
			for (int i = 0; i < 26; i ++) {
				if (null == root.nexts[i]) {
					rootNow.nexts[i] = rootNow.suffix.nexts[i];
				} else {
					rootNow.nexts[i].suffix = rootNow.suffix.nexts[i];
					trieNodeQueue.add(rootNow.nexts[i]);
				}
			}
		}
		String str = sc.nextLine().trim();
		rootNow = root;
		StringBuilder st = new StringBuilder(str);
		str = str.toLowerCase();
		LinkedList<Integer> list = new LinkedList<Integer>();
		for (int i= 0; i < str.length(); i ++) {
			rootNow = rootNow.nexts[str.charAt(i) - 'a'];
			if (rootNow.isEndOfAWord) {
				list.add(i);
				System.out.println(i);
			}
		}
		Iterator<Integer> it = list.iterator();
		while (it.hasNext()) {
			
		}
		sc.close();
	}

	/**
	 * @method		solve1insert 
	 * @parameter	s		---	匹配串
	 * @parameter	root	---	Trie图的root
	 * @return 		void
	 * @details 	对应第一个Solution
	 */
	private static void solve1insert(String s, TrieNode root) {
		TrieNode rootNow = root;
		for (int i = 0; i < s.length(); i ++) {
			int nextIndex = s.charAt(i) - 'a';
			if (null != rootNow.nexts[nextIndex]) {
				rootNow = rootNow.nexts[nextIndex];
			} else {
				TrieNode addTrieNode = new TrieNode();
				rootNow.nexts[nextIndex] = addTrieNode;
				rootNow = addTrieNode;
			}
		}
		rootNow.isEndOfAWord = true;
	}

	/**
	 * @author		zxwtry
	 * @email		zxwtry@qq.com
	 * @project		OJ
	 * @package		nowcoder.com
	 * @file		百度17_敏感词.java
	 * @date		2016年11月19日 下午7:01:01
	 * @details		TrieNode前缀数的修改版
	 * @details		限定匹配串全部是小写字母
	 * @details		对应第一个Solution
	 */
	static class TrieNode {
		boolean isEndOfAWord = false;
		TrieNode[] nexts = new TrieNode[26];
		TrieNode suffix = null;
	}
}
