package leetcode;

/**
 * 	You are given an array x of n positive numbers. You start at point (0,0) and 
 *  moves x[0] metres to the north, then x[1] metres to the west, x[2] metres to the south, 
 *  x[3] metres to the east and so on. In other words, after each move 
 *  your direction changes counter-clockwise.
 *		
 *	Write a one-pass algorithm with O(1) extra space to determine, 
 *  if your path crosses itself, or not.
 *	
 *	Example 1:
 *	Given x = 
 *	[2, 1, 1, 2]
 *	,
 *	┌───┐
 *	│   │
 *	└───┼──>
 *	    │
 *	
 *	Return true (self crossing)
 *	Example 2:
 *	Given x = 
 *	[1, 2, 3, 4]
 *	,
 *	┌──────┐
 *	│      │
 *	│
 *	│
 *	└────────────>
 *	
 *	Return false (not self crossing)
 *	Example 3:
 *	Given x = 
 *	[1, 1, 1, 1]
 *	,
 *	┌───┐
 *	│   │
 *	└───┼>
 *	
 *	Return true (self crossing)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P335_SelfCrossing.java
 * @type        P335_SelfCrossing
 * @date        2017年2月2日 下午3:42:42
 * @details     Solution1: AC 0ms 0.41% *
 */
public class P335_SelfCrossing {
	static class Solution1 {
	    public boolean isSelfCrossing(int[] x) {
	        if (x.length < 4) return false;
	        for (int index = 3; index < x.length; index ++) {
	            if (x[index] >= x[index - 2] && x[index - 3] >= x[index - 1]) return true;
	            if (index >= 4) {
	                if (x[index - 1] == x[index - 3] && x[index] + x[index - 4] >= x[index - 2]) return true;
	            }
	            if (index >= 5) {
	                if ( x[index - 1] < x[index - 3] && x[index - 2] > x[index - 4] &&
	                        x[index - 1] + x[index - 5] >= x[index - 3] &&
	                        x[index] + x[index - 4] >= x[index - 2])
	                    return true;
	            }
	        }
	        return false;
	    }
	}
}
