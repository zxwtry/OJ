package leetcode;

/**
 * 	Given an array containing n distinct numbers 
 * 	taken from 0, 1, 2, ..., n, find the one that 
 * 	is missing from the array.
 *	
 *	For example,
 *	Given nums = [0, 1, 3] return 2.
 *	
 *	Note:
 *	Your algorithm should run in linear runtime 
 *	complexity. Could you implement it using only 
 *	constant extra space complexity?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P268_MissingNumber.java
 * @type        P268_MissingNumber
 * @date        2016年12月13日 下午10:25:19
 * @details     Solution1: AC 2ms 23.20%
 * @details     Solution1: AC 2ms 23.20%
 */
public class P268_MissingNumber {
	public static void main(String[] args) {
		int[] nums = new int[] {0, 1, 3,4,2,6};
		Solution1 solution1 = new Solution1();
		System.out.println(solution1.missingNumber(nums));
	}
	static class Solution1 {
	    public int missingNumber(int[] nums) {
	        boolean[] arr = new boolean[nums.length + 1];
	        for (int num : nums) {
	        	arr[num] = true;
	        }
	        for (int arrIndex = 0; arrIndex < arr.length; arrIndex ++) {
	        	if (! arr[arrIndex]) {
	        		return arrIndex;
	        	}
	        }
	        return 0;
	    }
	}
	static class Solution2 {
		public int missingNumber(int[] nums) {
	        int leftIndex = 0, rightIndex = nums.length;
	        while (leftIndex < rightIndex) {
	        	if (nums[leftIndex] == leftIndex) {
	        		leftIndex ++;
	        	} else if (nums[rightIndex] == rightIndex) {
	        		rightIndex --;
	        	}
	        }
	        return 0;
	    }
	}
}
