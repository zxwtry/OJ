package leetcode;

import java.util.Arrays;

/**
 * 	Given a non-empty array containing only positive integers, 
 * 	find if the array can be partitioned into two subsets 
 * 	such that the sum of elements in both subsets is equal.
 *	
 *	Note:
 *	Each of the array element will not exceed 100.
 *	The array size will not exceed 200.
 *	Example 1:
 *	
 *	Input: [1, 5, 11, 5]
 *	
 *	Output: true
 *	
 *	Explanation: The array can be partitioned as [1, 5, 5] and [11].
 *	Example 2:
 *	
 *	Input: [1, 2, 3, 5]
 *	
 *	Output: false
 *	
 *	Explanation: The array cannot be partitioned into equal sum subsets.
 */
/*
 * 	给一个数组int[] nums
 * 	1 <= nums[i] <= 100
 * 	len <= 200
 * 	在数组中出一部分数，保证sum * 2 == AllSUM
 */

public class P416_PartitionEqualSubsetSum {
	public static void main(String[] args) {
//		for (int i = 0; i < 10000; i ++) {
			Solution2 s = new Solution2();
			int[] nums = tools.Random随机生成器.A_生成一个随机数据(400, 1, 1000);
			Arrays.sort(nums);
			tools.Utils.printArray(nums, 200);
			System.out.println(s.canPartition(nums));
//		}
//		int[] arr = tools.Random随机生成器.A_生成一个随机数据(100, 1, 1000);
//		String s = tools.Utils.LEETCODE_int_array_序列化_(arr);
//		System.out.println(s);
	}
	/*
	 * 	TLE
	 */
	static class Solution {
		int sum = 0;
		boolean[] isVisited = null;
		boolean isFind = false;
		public boolean canPartition(int[] nums) {
			for (int val : nums) {
				sum += val;
			}
			if (sum % 2 != 0) {
				return false;
			}
			sum = sum / 2;
			Arrays.sort(nums);
			isVisited = new boolean[nums.length];
			for (int i = nums.length - 1; i > -1; i --) {
				Arrays.fill(isVisited, false);
				search(i, sum - nums[i], nums, 1);
			}
			return isFind;
	    }
		void search(int i, int sum, int[] nums, int depth) {
			if (isFind) {
				return;
			}
			if (depth > nums.length / 2 + 1) {
				return;
			}
			if (sum == 0) {
				isFind = true;
			} else if (sum < 0) {
				return;
			}
			for (int index = 0; index < i; index ++) {
				if (! isVisited[index]) {
					isVisited[index] = true;
					search(index, sum - nums[index], nums, depth + 1);
					isVisited[index] = false;
				}
			}
		}
	}
	/*
	 * 	13 ms
	 */
	static class Solution2 {
		int sum = 0;
		boolean isFind = false;
		public boolean canPartition(int[] nums) {
			for (int val : nums) {
				sum += val;
			}
			if (sum % 2 != 0) {
				return false;
			}
			sum = sum / 2;
			Arrays.sort(nums);
			search(nums, nums[nums.length - 1], 0, nums.length - 2, sum * 2 - nums[nums.length - 1]);
			return isFind;
	    }
		void search(int[] nums, int val1, int val2, int index, int remains) {
			if (isFind) {
				return;
			}
			if (val1 == sum) {
				isFind = true;
				return;
			}
			if (val2 == sum) {
				isFind = true;
				return;
			}
			if (Math.abs(val1 - val2) > remains) {
				return;
			}
			if (index < 0) {
				return;
			}
			search(nums, val1 + nums[index], val2, index - 1, remains - nums[index]);
			search(nums, val1, val2 + nums[index], index - 1, remains - nums[index]);
		}
	}
}