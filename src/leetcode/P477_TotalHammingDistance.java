package leetcode;

/**
 * 	The Hamming distance between two integers is the number of positions 
 * 	at which the corresponding bits are different.

	Now your job is to find the total Hamming distance between all pairs 
	of the given numbers.
	
	Example:
	Input: 4, 14, 2
	
	Output: 6
	
	Explanation: In binary representation, the 4 is 0100, 14 is 1110, 
	and 2 is 0010 (just
	showing the four bits relevant in this case). So the answer will be:
	HammingDistance(4, 14) + HammingDistance(4, 2) + HammingDistance
	(14, 2) = 2 + 2 + 2 = 6.
	Note:
	Elements of the given array are in the range of 0 to 10^9
	Length of the array will not exceed 10^4.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P477_TotalHammingDistance.java
 * @type        P477_TotalHammingDistance
 * @date        2016年12月18日 上午10:53:37
 * @details     
 */
public class P477_TotalHammingDistance {
	static class Solution {
	    public int totalHammingDistance(int[] nums) {
	        int n = nums.length;
	        int ans = 0;
	        for (int i = 0; i < 32; i ++) {
	        	int c = 0;
	        	for (int v : nums) {
	        		c += ((v >>> i) & 0x1) == 1 ? 1 : 0;
	        	}
	        	ans += c * (n -c);
	        }
	        return ans;
	    }
	}
}
