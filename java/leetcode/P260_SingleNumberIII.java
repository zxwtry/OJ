package leetcode;

import java.util.HashSet;

/**
 * 	Given an array of numbers nums, in which exactly 
 * 	two elements appear only once and all the other 
 * 	elements appear exactly twice. Find the two 
 * 	elements that appear only once.
 *	
 *	For example:
 *	
 *	Given nums = [1, 2, 1, 3, 2, 5], return [3, 5].
 *	
 *	Note:
 *	The order of the result is not important. So 
 *	in the above example, [5, 3] is also correct.
 *	Your algorithm should run in linear runtime 
 *	complexity. Could you implement it using only
 *	constant space complexity?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P260_SingleNumberIII.java
 * @type        P260_SingleNumberIII
 * @date        2016年12月13日 下午10:20:02
 * @details     Solution1: AC 11ms 16.63%
 * @details     Solution2: AC  1ms 82.27%
 */
public class P260_SingleNumberIII {
	static class Solution1 {
	    public int[] singleNumber(int[] nums) {
	    	HashSet<Integer> set = new HashSet<Integer>();
	    	for (int n : nums) {
	    		if (set.contains(n)) {
	    			set.remove(n);
	    		} else {
	    			set.add(n);
	    		}
	    	}
	    	int[] ans = new int[2];
	    	int ansIndex = 0;
	    	for (int v : set) {
	    		if (ansIndex >= 2) break;
	    		ans[ansIndex ++] = v;
	    	}
	    	return ans;
	    }
	}
	static class Solution2 {
		public int[] singleNumber(int[] nums) {
			int XORSign = 0;
			for (int n : nums) {
				XORSign ^= n;
			}
			int lowBit = XORSign & - XORSign;
			int a = 0, b = 0;
			for (int n : nums) {
				if ((n & lowBit) != 0) {
					a ^= n;
				} else {
					b ^= n;
				}
 			}
			return new int[] {a, b};
		}
	}
}
