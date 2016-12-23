package nowcoder.zuo;

/**
 * 	字典树又称为前缀树或Trie树，是处理字符串常见的数据结构
 * 	假设组成所有单词的字符仅是'a'-'z'，实现字典树，包含以下4个主要功能。
 * 		1,	void insert(String w): 添加w，可重复添加
 * 		2,	void delete(String w): 删除w，如果w添加过多次，仅删除一个
 * 		3,	boolean search(String w): 查询w是否在字典树中
 * 		4,	int prefixNumber(String p): 返回以字符串p为前缀的单词数量
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.zuo
 * @file        Book085_字典树前缀树的实现.java
 * @type        Book085_字典树前缀树的实现
 * @date        2016年12月22日 下午5:47:37
 * @details     Trie前缀树，用了path和end两个记录变量
 */
public class Book086_字典树前缀树的实现 {
	static class Trie {
		final char startChar = 'a';
		final char endChar = 'z';
		final int mapLen = endChar - startChar + 1;
		final private TrieNode root = new TrieNode();
		public void insert(String w) {
			if (w == null || w.length() == 0) return;
			TrieNode rootNow = root;
			int index = 0;
			for (int i = 0; i < w.length(); i ++) {
				index = w.charAt(i) - startChar;
				if (rootNow.map[index] == null)
					rootNow.map[index] = new TrieNode();
				rootNow.path ++;
				rootNow = rootNow.map[index];
			}
			rootNow.path ++;
			rootNow.end ++;
		}
		public void delete(String w) {
			if (! search(w)) return;
			TrieNode rootNow = root;
			int index = 0;
			for (int i = 0; i < w.length(); i ++) {
				index = w.charAt(i) - startChar;
				if (rootNow.map[index] == null || rootNow.path < 1) return;
				rootNow.path --;
				rootNow = rootNow.map[index];
			}
			if (rootNow.path > 0) {
				rootNow.path --;
				rootNow.end --;
			}
		}
		public boolean search(String w) {
			if (w == null || w.length() == 0) return false;
			TrieNode rootNow = root;
			int index = 0;
			for (int i = 0; i < w.length(); i ++) {
				index = w.charAt(i) - startChar;
				if (rootNow.map[index] == null || rootNow.path < 1) return false;
				rootNow = rootNow.map[index];
			}
			return rootNow.end != 0;
		}
		public int prefixNumber(String p) {
			if (p == null || p.length() == 0) return 0;
			TrieNode rootNow = root;
			int index = 0;
			for (int i = 0; i < p.length(); i ++) {
				index = p.charAt(i) - startChar;
				if (rootNow.map[index] == null) return 0;
				rootNow = rootNow.map[index];
			}
			return rootNow.path;
		}
		class TrieNode {
			public int path, end;
			public TrieNode[] map = new TrieNode[mapLen];
		}
	}
}
