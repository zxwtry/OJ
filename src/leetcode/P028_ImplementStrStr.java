package leetcode;

/**
 * 	Implement strStr().
 *  
 *  Returns the index of the first occurrence of needle in haystack,
 *  or -1 if needle is not part of haystack.
 */

public class P028_ImplementStrStr {
    //AC 13ms 34.88%
	static class Solution {
		public int strStr(String s, String p) {
		    int sn = s == null ? 0 : s.length();
		    int pn = p == null ? 0 : p.length();
		    //pn == 0, p match at index 0
		    if (pn == 0) return 0;
		    //sn == 0 and pn > 0 can not match
		    if (sn == 0) return -1;
		    int si = 0, pi = 0, next[] = getNext(p, pn);
		    while (si < sn) {
		        if (s.charAt(si) == p.charAt(pi)) {
		            si ++;
		            pi ++;
		            if (pi == pn) break;
		        } else if (next[pi] == -1) {
		            si ++;
		        } else pi = next[pi];
		    }
		    return pi == pn ? si - pn : - 1;
		}
		public int[] getNext(String p, int pn) {
		    //return short p
		    if (pn < 2) return new int[] {-1};
		    int bi = 0, fi = 2, next[] = new int[pn];
		    next[0] = -1;
		    next[1] = 0;
		    while (fi < pn) {
		        if (p.charAt(fi - 1) == p.charAt(bi)) {
		            next[fi ++] = ++ bi;
		        } else if (bi <= 0) {
		            next[fi ++] = 0;
		        } else bi = next[bi];
		    }
		    return next;
		}
	}
}
