package leetcode;

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
 * @details     Solution1: AC 75ms 42.59%
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
}
