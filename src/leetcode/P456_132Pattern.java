package leetcode;

import java.util.Arrays;

/*
 * 	Given a sequence of n integers a1, a2, ..., an, a 132 pattern is
 * 	 a subsequence ai, aj, ak such that i < j < k and ai < ak < aj. 
 * 	Design an algorithm that takes a list of n numbers as input and 
 * 	checks whether there is a 132 pattern in the list.

	Note: n will be less than 15,000.
	
	Example 1:
	Input: [1, 2, 3, 4]
	
	Output: False
	
	Explanation: There is no 132 pattern in the sequence.
	Example 2:
	Input: [3, 1, 4, 2]
	
	Output: True
	
	Explanation: There is a 132 pattern in the sequence: [1, 4, 2].
	Example 3:
	Input: [-1, 3, 2, 0]
	
	Output: True
	
	Explanation: There are three 132 patterns in the sequence: [-1, 3, 2], [-1, 3, 0] and [-1, 2, 0].
 */

public class P456_132Pattern {
	public static void main(String[] args) {
		int n = 10;
		int min = 0;
		int max = 20;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		tools.Utils.printArray(arr, 100);
//		debugSolution();
	}
	static void debugSolution() {
		int[] nums = new int[] {1, 1, 2};
		Solution s = new Solution();
		System.out.println(s.find132pattern(nums));
	}
	static class Solution {
	    public boolean find132pattern(int[] nums) {
	        if (nums == null || nums.length < 3) {
	        	return false;
	        }
	        boolean isFound = false;
	        for (int i = 0; ! isFound && i < nums.length - 2; i ++) {
	        	if (nums[i] < nums[i + 2] && nums[i + 2] < nums[i + 1]) {
	        		isFound = true;
	        	}
	        }
	        return isFound;
	    }
		public int[] getSmallArray(int[] arr) {
			if (arr == null || arr.length == 0) {
				return new int[0];
			}
			int[] small = new int[arr.length];
			Arrays.fill(small, -1);
			long min = Long.MAX_VALUE;
			for (int index = 0; index < arr.length; index ++) {
				if (min > arr[index]) {
					min = arr[index];
					small[index] = index;
				} else {
					arr[index] = arr[index - 1];
				}
			}
			return small;
		}
	}
	
	/*
	 * 	先将问题进行简化为：
	 * 	一个数组：arr	，不是有序的。
	 * 	返回一个数组：small，表示第一个比当前val小的下标
	 */
	static class SmallSolution {
		public int[] getSmallArray(int[] arr) {
			if (arr == null || arr.length == 0) {
				return new int[0];
			}
			int[] small = new int[arr.length];
			Arrays.fill(small, -1);
			long min = Long.MAX_VALUE;
			for (int index = 0; index < arr.length; index ++) {
				if (min > arr[index]) {
					min = arr[index];
					small[index] = index;
				} else {
					arr[index] = arr[index - 1];
				}
			}
			return small;
		}
	}
}