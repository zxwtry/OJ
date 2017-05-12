package leetcode;

/**
    Given a string s, partition s such that every substring of 
    the partition is a palindrome.
	
	Return the minimum cuts needed for a palindrome partitioning of s.
	
	For example, given s = "aab",
	Return 1 since the palindrome partitioning ["aa","b"] 
	could be produced using 1 cut.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P132_PalindromePartitioningII.java
 * @type        P132_PalindromePartitioningII
 * @date        2016年12月13日 下午7:49:29
 * @details     Solution1: AC 52ms 14.09%
 */
public class P132_PalindromePartitioningII {
	static class Solution {
		public int minCut(String s) {
			if (s == null || s.length() < 2) return 0;
			int[] m = manacher(s);
			int[] ndp = new int[s.length()];
			for (int i = 0; i < s.length(); i ++) {
				if (isPalindrome(m, i, 0)) {
				    ndp[i] = 0;
				    continue;
				}
				ndp[i] = Integer.MAX_VALUE-1;
				for (int j = 1; j <= i; j ++)
					if (isPalindrome(m, i, j))
					    ndp[i] = Math.min(ndp[j-1] + 1, ndp[i]);
			}
			return ndp[ndp.length - 1];
		}
		private boolean isPalindrome(int[] m, int maxIndex, int minIndex) {
			return m[minIndex + maxIndex + 1] > maxIndex - minIndex;
		}
		private int[] manacher(String s) {
		    int mn = 2 * (s == null ? 0 : s.length()) + 1;
			int[] m = new int[mn];
			int ti = 0, ci = 0, mi = 0, li = 0, ri = 0;
			for (int i = 0; i < mn; i ++) {
				mi = 2 * ci - i;
				if (i >= ti || m[mi] == ti-i) {
					li = ri = i;
					while (li-1 > -1 && ri+1 < mn && access(s, li-1) == access(s, ri+1)) {
						li --;
						ri ++;
					}
					ti = ri;
					ci = i;
					m[i] = (ri - li) / 2;
				} else m[i] = Math.min(m[mi], ti-i);
			}
			return m;
		}
		private char access(String s, int i) {
			return i % 2 == 0 ? 0 : s.charAt(i/2);
		}
	}
}
