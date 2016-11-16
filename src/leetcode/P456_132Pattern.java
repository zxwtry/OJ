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
		debugNewSmallSolution();
	}
	static void debugNewSmallSolution() {
		int n = 10;
		int min = 0;
		int max = n * 3;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		NewSmallSolution s = new NewSmallSolution();
		int[] small = s.getSmall(arr);
		tools.Utils.printArray(arr, 100);
		tools.Utils.printArray(small, 100);
	}
	static void debugSolution() {
		int[] nums = new int[] {3,5,0,3,4};
		Solution s = new Solution();
		System.out.println(s.find132pattern(nums));
	}
	static class Solution {
	    public boolean find132pattern(int[] nums) {
	        if (nums == null || nums.length < 3) {
	        	return false;
	        }
	        boolean isFound = false;
	        int[] preMin = new int[nums.length];
	        int[] posMax = new int[nums.length];
	        preMin[0] = Integer.MAX_VALUE;
	        posMax[nums.length - 1] = Integer.MIN_VALUE;
	        for (int i = 1; i < nums.length; i ++) {
	        	int preMinNow = Math.min(preMin[i - 1], nums[i - 1]);
	        	preMin[i] = preMinNow < nums[i] ? preMinNow : Integer.MAX_VALUE;
	        }
	        for (int i = nums.length - 2; i > -1; i --) {
	        	int posMaxNow = Math.max(posMax[i + 1], nums[i + 1]);
	        	posMax[i] = posMaxNow < nums[i] ? posMaxNow : Integer.MIN_VALUE;
	        }
	        tools.Utils.printArray(preMin, 100);
	        tools.Utils.printArray(posMax, 100);
	        for (int i = 1; ! isFound && i < nums.length; i ++) {
	        	if (posMax[i] > preMin[i] && nums[i] > posMax[i]) {
	        		isFound = true;
	        	}
	        }
	        return isFound;
	    }
	}
	
	/*
	 * 	提交之后，TLE
	 */
	static class SlowSolution {
		public boolean find132pattern(int[] nums) {
			boolean isFind = false;
			for (int i = 0; ! isFind && i < nums.length; i ++) {
				for (int j = i + 1; ! isFind && j < nums.length; j ++) {
					for (int k = j + 1; ! isFind && k < nums.length; k ++) {
						if (nums[i] < nums[k] && nums[k] < nums[j]) {
							isFind = true;
						}
					}
				}
			}
			return isFind;
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
	
	/*
	 * 	小问题：
	 * 		O(N)的时间下，完成如下功能：
	 * 		输入一个数组arr：
	 * 		1,	对每一位，找下标前面的，小于当前val，最小值
	 * 		2,	返回的small是数值数组，不是index数组
	 */
	static class NewSmallSolution {
		public int[] getSmall(int[] arr) {
			int[] small = new int[arr.length];
			small[0] = Integer.MAX_VALUE;
			int minNow = arr[0]; 
			for (int index = 1; index < arr.length; index ++) {
				small[index] = arr[index] <=minNow ? Integer.MAX_VALUE : minNow;
				minNow = Math.min(minNow, arr[index]);
			}
			return small;
		}
	}
}