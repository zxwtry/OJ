package leetcode;

import java.util.ArrayList;

import com.sun.org.apache.xerces.internal.impl.xs.SchemaSymbols;

/**
 * 	Given a positive integer n, find the least number of perfect 
 * 	square numbers (for example, 1, 4, 9, 16, ...) which sum to n.
 * 	
 * 	For example, given n = 12, return 3 because 12 = 4 + 4 + 4; 
 * 	given n = 13, return 2 because 13 = 4 + 9.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P279_PerfectSquares.java
 * @type        P279_PerfectSquares
 * @date        2016年12月14日 下午10:23:11
 * @details     Solution1: AC  75ms 42.59%
 * @details     Solution2: AC 100ms 33.81%
 * @details     Solution3: AC  61ms 74.41%
 */
public class P279_PerfectSquares {
	static class Solution1 {
	    public int numSquares(int n) {
	    	int[] sqrtArray = new int[n + 1];
	    	sqrtArray[0] = 0;
	    	int sqrtIndex = 0;
	    	int newIndex = 0;
	    	for (int sqrtArrayIndex = 0; sqrtArrayIndex <= n; sqrtArrayIndex ++) {
	    		sqrtIndex = 1;
	    		while (true) {
	    			newIndex = sqrtArrayIndex + sqrtIndex * sqrtIndex;
	    			if (newIndex > n) break;
	    			if (sqrtArray[newIndex] == 0) {
	    				sqrtArray[newIndex] = sqrtArray[sqrtArrayIndex] + 1;
	    			} else {
	    				sqrtArray[newIndex] = Math.min(sqrtArray[newIndex],  sqrtArray[sqrtArrayIndex] + 1);
	    			}
 	    			sqrtIndex ++;
	    		}
	    	}
	    	return sqrtArray[n];
	    }
	}
	static class Solution2 {
		public int numSquares(int n) {
			ArrayList<Integer> dpList =  new ArrayList<Integer>(n);
			dpList.add(0);
			int numSquareNow = 0;
			while (dpList.size() <= n) {
				numSquareNow = Integer.MAX_VALUE;
				for (int index = 1; index * index <= dpList.size(); index ++) {
					numSquareNow = Math.min(numSquareNow, dpList.get(dpList.size() - index * index) + 1);
				}
				dpList.add(numSquareNow);
			}
			return dpList.get(n);
		}
	}
	static class Solution3 {
		public int numSquares(int n) {
			int[] dp = new int[n + 1];
			int dpIndex = 1;
			int numSquareNow = 0;
			while (dpIndex <= n) {
				numSquareNow = Integer.MAX_VALUE;
				for (int index = 1; index * index <= dpIndex; index ++) {
					numSquareNow = Math.min(numSquareNow, dp[dpIndex - index * index] + 1);
				}
				dp[dpIndex ++] = numSquareNow;
			}
			return dp[n];
		}
	}
}
