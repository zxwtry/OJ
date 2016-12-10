package leetcode;

/**
 * 	Given an array nums, there is a sliding window of size k which is moving from
 *  the very left of the array to the very right. You can only see the k numbers 
 *  in the window. Each time the sliding window moves right by one position.

	For example,
	Given nums = [1,3,-1,-3,5,3,6,7], and k = 3.
	
	Window position                Max
	---------------               -----
	[1  3  -1] -3  5  3  6  7       3
	 1 [3  -1  -3] 5  3  6  7       3
	 1  3 [-1  -3  5] 3  6  7       5
	 1  3  -1 [-3  5  3] 6  7       5
	 1  3  -1  -3 [5  3  6] 7       6
	 1  3  -1  -3  5 [3  6  7]      7
	Therefore, return the max sliding window as [3,3,5,5,6,7].
	
	Note: 
	You may assume k is always valid, ie: 1 ≤ k ≤ input array's size for non-empty array.
	
	Follow up:
	Could you solve it in linear time?
	
	Hint:
	
	How about using a data structure such as deque (double-ended queue)?
	The queue size need not be the same as the window’s size.
	Remove redundant elements and the queue should store only elements that need to be considered.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P239_SlidingWindowMaximum.java
 * @type        P239_SlidingWindowMaximum
 * @date        2016年12月10日 下午10:26:54
 * @details     
 */
public class P239_SlidingWindowMaximum {
	public static void main(String[] args) {
		
	}
	static class Solution {
	    public int[] maxSlidingWindow(int[] nums, int k) {
	        
	    }
	}
}
