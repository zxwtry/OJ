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
			return kmp(s, p);
		}
		private int kmp(String s, String p) {
			int sn = s == null ? 0 : s.length();
			int pn = p == null ? 0 : p.length();
			if (pn == 0) return 0;
			if (sn < pn) return -1;
			int[] next = getNext(p, pn);
            int pi = 0, si = 0;
			while (si < sn)
			    if (s.charAt(si) == p.charAt(pi)) {
			        si ++;
			        pi ++;
			        if (pi == pn) {
//			            many times
//			            System.out.println(si - pn);
//			            si -= pn - 1;
//			            pi = 0;
			            
//			            once
			            return si - pn;
			        }
			    } else if (next[pi] == -1) {
			        si ++;
			    } else pi = next[pi];
			return -1;
		}
		private int[] getNext(String p, int pn) {
		    if (pn < 2) return new int[] {-1};
		    int[] next = new int[pn];
		    next[0] = -1;
		    next[1] = 0;
		    int fi = 2, bi = 0;
		    while (fi < pn)
		        if (p.charAt(fi - 1) == p.charAt(bi)) {
		            next[fi ++] = ++ bi;
		        } else if (bi <= 0) {
		            next[fi ++] = 0;
		        } else bi = next[bi];;
		    return next;
		}
	}
}
