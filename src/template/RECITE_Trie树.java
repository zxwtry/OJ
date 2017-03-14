package template;

import java.util.HashMap;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_Trie树.java
 * @type        RECITE_Trie树
 * @date        2017年3月14日 下午9:37:27
 * @details     
 */
public class RECITE_Trie树 {
    static void constructTrieTree(String s, TrieNode root) {
        if (s == null || s.length() == 0) return;
        TrieNode rootNow = root;
        int n = s.length();
        for (int i = 0; i < n; i ++)
            rootNow = rootNow.insert(s.charAt(i));
        rootNow.isEndOfAWord = true;
    }
    static class TrieNode {
        HashMap<Character, TrieNode> map = null;
        boolean isEndOfAWord = false;
        public TrieNode insert(char c) {
            if (null == map) map = new HashMap<>();
            TrieNode t = map.get(c);
            if (t == null) {
                t = new TrieNode();
                map.put(c, t);
            }
            return t;
        }
        public TrieNode search(char c) {
            if (null == map) return null;
            return map.get(c);
        }
    }
}