package leetcode;

import java.util.HashSet;

/*
 * 	Given an unsorted array of integers, find the length of the longest
 *  consecutive elements sequence.

	For example,
	Given [100, 4, 200, 1, 3, 2],
	The longest consecutive elements sequence is [1, 2, 3, 4]. Return its length: 4.
	
	Your algorithm should run in O(n) complexity.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P128_LongestConsecutiveSequence.java
 * @type        P128_LongestConsecutiveSequence
 * @date        2017年5月9日 下午10:04:45
 * @details     Solution: AC 86ms 1.69%
 */
public class P128_LongestConsecutiveSequence {
	static class Solution {
	    public int longestConsecutive(int[] nums) {
	    	if (nums == null || nums.length == 0) {
	    		return 0;
	    	}
	    	int max = Integer.MIN_VALUE, min = Integer.MAX_VALUE;
	    	HashSet<Integer> set = new HashSet<>();
	    	for (int n : nums) {
	    		max = Math.max(max, n);
	    		min = Math.min(min, n);
	    		set.add(n);
	    	}
	    	if (max - min == nums.length - 1 && set.size() == nums.length) {
	    		return nums.length;
	    	}
	    	int ans = 0;
	    	for (int n : nums) {
	    		int l = n, r = n;
	    		while (set.contains(-- l)) {
	    			
	    		}
	    		while (set.contains(++ r)) {
	    			
	    		}
	    		ans = Math.max(ans, r - l - 1);
	    	}
	        return ans;
	    }
	}
}
