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
 * @details     
 */
public class P132_PalindromePartitioningII {
	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.minCut("aabccba"));
	}
	//改成Manacher 还是TLE
	static class Solution {
		int maxn = Integer.MAX_VALUE;
		char[] cs = null;
		char[] m = null;
		int[] r = null;
	    public int minCut(String s) {
	        cs = s.toCharArray();
	        manacher(s);
	        for (int i = cs.length-1; i >= 0; i --)
	        	search(0, i, 0);
//	        tools.Utils.printArray(m, 100);
//	        tools.Utils.printArray(r, 100);
	        return maxn;
	    }
		private void manacher(String s) {
			m = new char[2 * s.length() + 1];
			int mei = -1;
			for (int i = 0; i < s.length(); i ++) {
				m[++ mei] = '#';
				m[++ mei] = s.charAt(i);
 			}
			m[++mei] = '#';
			int mti = 0; 	//maxTouchedIndex
			int lci = 0;	//lastCircleIndex
			int mis = 0;	//mi symmetry about lci
			r = new int[mei+1];
			for (int mi = 0; mi <= mei; mi ++) {
				mis = 2 * lci - mi;
				if (mi >= mti || r[mis] + mi == mti) {
					int li = mi, ri = mi;
					while (li-1 > -1 && ri+1 <= mei && m[li-1] == m[ri+1]) {
						li --;
						ri ++;
					}
					mti = ri;
					lci = mi;
					r[mi] = (ri-li) / 2;
				} else if (r[mis] + mi < mti) {
					r[mi] = r[mis];
				} else {
					r[mi] = (mti>mei?mei:mti) - mi;
				}
			}
		}
		
		private void search(int s, int t, int n) {
			if (n >= maxn) return;
			if (isP(s, t)) {
				for (int i = cs.length-1; i > t; i --)
					search(t+1, i, n + 1);
				if (t == cs.length - 1)
					maxn = Math.min(maxn, n);
			}
		}
		private boolean isP(int s, int t) {
//			while (s < t) {
//				if (cs[s] != cs[t]) {
//					return false;
//				} else {
//					s ++;
//					t --;
//				}
//			}
//			return true;
			int ms = 2 * s + 1;
			int mt = 2 * t + 1;
			int mid = (ms + mt) / 2;
			return r[mid] > (t-s);
		}
		
	}
}
