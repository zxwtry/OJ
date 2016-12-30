package leetcode;

/**
 * 	Given a string s, partition s such that every substring of 
 * 	the partition is a palindrome.
	
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
	static class Solution1 {
		int[] R = null;
		public int minCut(String s) {
			if (s == null || s.length() < 2) return 0;
			init(s);
			int[] ndp = new int[s.length()];
			for (int i = 0; i < s.length(); i ++) {
				ndp[i] = Integer.MAX_VALUE;
				for (int j = 0; j <= i; j ++) {
					if (! np(i, j)) continue;
					if (j == 0) {
						ndp[i] = 0;
						break;
					}
					if (ndp[j-1] != Integer.MAX_VALUE)
						ndp[i] = Math.min(ndp[j-1] + 1, ndp[i]);
				}
			}
			return ndp[ndp.length - 1];
		}
		private boolean np(int i, int j) {
			return R[(2*i+2*j+2)/2] > (i-j);
		}
		private void init(String s) {
			R = new int[s.length()*2 + 1];
			char[] m = new char[R.length];
			for (int i = 0; i < s.length(); i ++)
				m[2 * i + 1] = s.charAt(i);
			int mti = 0;	//maxTouchedIndex
			int lci = 0;	//lastCircleIndex
			int syi = 0;	//symmetric index about lci
			for (int i = 0; i < m.length; i ++) {
				syi = 2 * lci - i;
				if (i >= mti || R[syi] + i == mti) {
					int l = i, r = i;
					while (l > 0 && r < m.length - 1 && m[l] == m[r]) {
						l --;
						r ++;
					}
					mti = r;
					lci = i;
					R[i] = (r - l) / 2;
				} else {
					R[i] = R[syi] + i < mti ? R[syi] : mti - i;
				}
			}
		}
	}
}
