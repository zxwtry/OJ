package leetcode;

import com.sun.corba.se.impl.presentation.rmi.StubFactoryFactoryStaticImpl;

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
	static class Solution {
		public int minCut(String s) {
			System.out.println("####");
			int len = s.length();
			boolean[][] isPal = new boolean[len][len];
			for (int i = 0; i < len; i ++) {
				for (int j = 0; j <= i; j ++) {
					if (! isPal[i][j] && s.charAt(i) == s.charAt(j)) {
						
					}
				}
			}
			System.out.println("####");
//			return 0;
		}
	}
}
