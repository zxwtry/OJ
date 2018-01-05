package leetcode;

/**
 * 	The Hamming distance between two integers is the number of positions 
 * 	at which the corresponding bits are different.

	Given two integers x and y, calculate the Hamming distance.
	
	Note:
	0 ≤ x, y < 231.
	
	Example:
	
	Input: x = 1, y = 4
	
	Output: 2
	
	Explanation:
	1   (0 0 0 1)
	4   (0 1 0 0)
	       ↑   ↑
	
	The above arrows point to positions where the corresponding bits are different.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P461_HammingDistance.java
 * @type        P461_HammingDistance
 * @date        2016年12月18日 上午10:47:25
 * @details     
 */
public class P461_HammingDistance {
	static class Solution {
	    public int hammingDistance(int x, int y) {
	    	int ans = 0;
	        while (x != 0 || y != 0) {
	        	ans += (x & 0x1) == (y & 0x1) ? 0 : 1;
	        	x >>>= 1;
	        	y >>>= 1;
	        }
	        return ans;
	    }
	}
}
