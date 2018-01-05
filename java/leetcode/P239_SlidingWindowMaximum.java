package leetcode;

import java.util.LinkedList;

/**
 * 	Given an array nums, there is a sliding window of size k which is moving from
 *  the very left of the array to the very right. You can only see the k numbers 
 *  in the window. Each time the sliding window moves right by one position.
 *	
 *	For example,
 *	Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
 *	
 *	Window position                Max
 *	---------------               -----
 *	[1  3  -1] -3  5  3  6  7       3
 *	 1 [3  -1  -3] 5  3  6  7       3
 *	 1  3 [-1  -3  5] 3  6  7       5
 *	 1  3  -1 [-3  5  3] 6  7       5
 *	 1  3  -1  -3 [5  3  6] 7       6
 *	 1  3  -1  -3  5 [3  6  7]      7
 *	Therefore, return the max sliding window as [3,3,5,5,6,7].
 *	
 *	Note: 
 *	You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
 *	
 *	Follow up:
 *	Could you solve it in linear time?
 *	
 *	Hint:
 *	
 *	How about using a data structure such as deque (double-ended queue)?
 *	The queue size need not be the same as the window’s size.
 *	Remove redundant elements and the queue should store only elements that need to be considered.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P239_SlidingWindowMaximum.java
 * @type        P239_SlidingWindowMaximum
 * @date        2016年12月10日 下午10:26:54
 * @details     Solution1: AC 39ms 24.94%
 * @details     Solution2: AC 26ms 38.40%
 */
public class P239_SlidingWindowMaximum {
	static class Solution1 {
	    public int[] maxSlidingWindow(int[] nums, int k) {
	    	if (nums == null || nums.length < 1 || k < 1)
	    		return nums;
	        LinkedList<Integer> l1 = new LinkedList<Integer>();
	        LinkedList<Integer> l2 = new LinkedList<Integer>();
	        int[] ans = new int[nums.length - k + 1];
	        int ansIndex = 0;
	        for (int i = 0; i < nums.length; i ++) {
	        	while (!l1.isEmpty() && l1.peekLast() < nums[i]) {
	        		l1.pollLast();
	        		l2.pollLast();
	        	}
	        	while (!l1.isEmpty() && i - l2.peekFirst() >= k) {
	        		l1.pollFirst();
	        		l2.pollFirst();
	        	}
	        	if (l1.isEmpty() || l1.peekLast() >= nums[i]) {
		        	l1.addLast(nums[i]);
		        	l2.addLast(i);
	        	}
	        	if (i >= k - 1) {
	        		ans[ansIndex ++] = l1.peekFirst();
	        	}
	        }
 	        return ans;
	    }
	}
	static class Solution2 {
		public int[] maxSlidingWindow(int[] nums, int k) {
	    	if (nums == null || nums.length < 1 || k < 1)
	    		return nums;
	    	LinkedList<Integer> list = new LinkedList<Integer>();
	    	int[] ans = new int[nums.length - k + 1];
	    	int ansIndex = 0;
	    	for (int i = 0; i < nums.length; i ++) {
	    		while (! list.isEmpty() && nums[list.peekLast()] < nums[i]) {
	    			list.pollLast();
	    		}
	    		while (! list.isEmpty() && i - list.peekFirst() >= k) {
	    			list.pollFirst();
	    		}
	    		if (list.isEmpty() || nums[list.peekLast()] >= nums[i]) {
	    			list.add(i);
	    		}
	    		if (i >= k - 1) {
	    			ans[ansIndex ++] = nums[list.peekFirst()];
	    		}
	    	}
	    	return ans;
		}
	}
}
