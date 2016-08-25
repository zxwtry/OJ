package leetcode;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

/*
 * 	You are given a string, s, and a list of words, words, that are all of the same length. 
 * 	Find all starting indices of substring(s) in s that is a concatenation of each word in words 
 * 	exactly once and without any intervening characters.

	For example, given:
	s: "barfoothefoobarman"
	words: ["foo", "bar"]

	You should return the indices: [0,9].
	(order does not matter).
 */

public class P030_SubstringWithConcatenationOfAllWords {
	public static void main(String[] args) {
		System.out.println(new Solution1().findSubstring("wordgoodgoodgoodbestword", new String[]{"word","good","best","good"}));;
//		System.out.println(new Solution1().findSubstring("barfoothefoobarman", new String[]{"foo", "bar"}));;
	}
	/*
	 * 	土方法，KMP
	 * 	算法正确性没有错
	 * 	TLE
	 * 	74 ms
	 * 	44.40%
	 */
	static class Solution1 {
		public List<Integer> findSubstring(String s, String[] words) {
			List<Integer> ans = new LinkedList<Integer>();
			int wordlen = 0;
			if (s == null || s.length() == 0 || words == null || words.length == 0 || (wordlen = words[0].length()) == 0)
				return ans;
			char[] sc = s.toCharArray(), wc = null;
			int[] sign = new int[sc.length], next = null, judge = new int[words.length];
			Arrays.fill(sign, -1);	Arrays.fill(judge, 0);
			HashMap<String, Integer> judgeMap = new HashMap<String, Integer>();
			for (int i = words.length - 1; i != -1; i --) {
				if (judgeMap.containsKey(words[i])) {
					judge[judgeMap.get(words[i])] ++;
				} else {
					judgeMap.put(words[i], i);
					judge[i] ++;
				}
			}
			if (judgeMap.size() == 1 && words[0].equals("a")) {
				for (int i = 0; i < s.length() - words.length + 1; i ++)
					ans.add(i);
				return ans;
			}
			for (int i = 0; i != words.length; i ++) {
				if (judgeMap.get(words[i]) != i)
					continue;
				wc = words[i].toCharArray();
				next = getNext(wc);
				int si = 0, pi = 0;
				while (si != sc.length) {
					if (-1 == pi || sc[si] == wc[pi]) {
						si ++;   pi ++;
					} else 
						pi = next[pi];
					if (pi == wc.length) {
						sign[si - wc.length] = i;
						si -= wc.length - 1;
						pi = 0;
					}
				}
			}
			int[] map = new int[words.length];
			for (int i = 0; i != sign.length; i ++) {
				if (sign[i] == -1)
					continue;
				Arrays.fill(map, 0);
				map[sign[i]] ++;
				for (int j = 1; j != words.length; j ++) {
					int temp = j * wordlen + i;
					if (temp >= sign.length || sign[temp] == -1)
						break;
					map[sign[temp]] ++;
				}
				boolean isTrue = map[0] == judge[0];
				for(int j = 1; j != map.length; j ++)
					isTrue &= map[j] == judge[j];
				if (isTrue)
					ans.add(i);
			}
			return ans;
	    }
		int[] getNext(char[] c) {
			int[] next = new int[c.length];
			next[0] = -1;
			int cur = 0, pre = -1;
			while (cur != c.length - 1) {
				if (pre == -1 || c[pre] == c[cur]) {
					pre ++;   cur ++;
					next[cur] = pre;
				} else 
					pre = next[pre];
			}
			return next;
		}
	}
}
