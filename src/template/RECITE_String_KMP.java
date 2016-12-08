package template;


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     template
 * @file        RECITE_String_KMP.java
 * @type        RECITE_String_KMP
 * @date        2016年12月6日 下午4:12:12
 * @details     https://leetcode.com/problems/implement-strstr/
 */
public class RECITE_String_KMP {
	
	static class Solution {
		public int strStr(String s, String p) {
			int[] next = getNext(p);
			return kmp(next, s, p);
		}

		private int kmp(int[] next, String s, String p) {
			int pi = 0;
			for (int si = 0; si <= s.length() - p.length(); si ++) {
				while (pi < p.length() && s.charAt(si + pi) == p.charAt(pi)) pi ++;
				if (pi == p.length()) return si;
				pi = next[pi];
			}
			return -1;
		}

		private int[] getNext(String p) {
			int[] next = new int[p.length() + 1];
			next[0] = -1;
			int bi = -1, fi = 0;
			for (; fi < p.length(); fi ++) {
				if (bi == -1 || p.charAt(bi) == p.charAt(fi)) {
					bi ++;
					fi ++;
					next[fi] = bi;
				} else {
					bi = next[bi];
				}
			}
			next[0] = 0;
			return next;
		}
	}
	
	
}
