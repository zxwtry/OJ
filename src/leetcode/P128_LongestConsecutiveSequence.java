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

public class P128_LongestConsecutiveSequence {
	public static void main(String[] args) {
		int[] nums = new int[]{100, 4, 200, 1, 3, 2, 2};
		nums = new int[] {4, 3, 2, 1};
		nums = new int[] {4, 3, 8, 2, 1};
		nums = new int[] {1};
		nums = new int[] {9,1,4,7,3,-1,0,5,8,-1,6};
		Solution s = new Solution();
		System.out.println(s.longestConsecutive(nums));
	}
	/*
	 * 	AC
	 * 	86 ms
	 * 	1.69%
	 */
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
