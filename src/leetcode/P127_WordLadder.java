package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*
 * 	Given two words (beginWord and endWord), and a dictionary's word list,
 *  find the length of shortest transformation sequence
 *   from beginWord to endWord, such that:

	Only one letter can be changed at a time
	Each intermediate word must exist in the word list
	For example,
	
	Given:
	beginWord = "hit"
	endWord = "cog"
	wordList = ["hot","dot","dog","lot","log"]
	As one shortest transformation is "hit" -> "hot" -> "dot" -> "dog" -> "cog",
	return its length 5.
	
	Note:
	Return 0 if there is no such transformation sequence.
	All words have the same length.
	All words contain only lowercase alphabetic characters.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P127_WordLadder.java
 * @type        P127_WordLadder
 * @date        2017年5月12日 下午10:28:45
 * @details     AC 103ms 78.74%
 */
public class P127_WordLadder {
    static public class Solution {
        public int ladderLength(String b, String e, List<String> w) {
            int sn = b == null ? 0 : b.length();
            if (sn == 0) return  1;
            HashSet<String> nv = new HashSet<String>(w);
            HashSet<String> hv = new HashSet<String>();
            Queue<String> q = new LinkedList<String>();
            q.add(b);
            nv.add(b);
            char[] cs = new char[sn];
            boolean isFind = false;
            int ans = 1;
            while (! q.isEmpty()) {
                int size = q.size();
                ans ++;
                while (size -- > 0) {
                    String n = q.poll();
                    for (int i = 0; i < sn; i ++) cs[i] = n.charAt(i);
                    for (int i = 0; i < sn; i ++) {
                        for (char c = 'a'; c <= 'z'; c ++) {
                            cs[i] = c;
                            String nn = new String(cs);
                            if (! nv.contains(nn)) continue;
                            if (hv.add(nn)) q.add(nn);
                            isFind |= nn.equals(e);
                        }
                        cs[i] = n.charAt(i);
                    }
                }
                if (isFind) break;
                nv.removeAll(hv);
                hv.clear();
            }
            return isFind ? ans : 0;
        }
    }
}
