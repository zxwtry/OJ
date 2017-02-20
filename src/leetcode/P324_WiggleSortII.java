package leetcode;

import java.util.Arrays;

/**
 * 	Given an unsorted array nums, reorder it such that nums[0] < nums[1] > nums[2] < nums[3]....
 * 	
 *  
 *  Example:
 *  (1) Given nums = [1, 5, 1, 1, 6, 4], one possible answer is [1, 4, 1, 5, 1, 6]. 
 *  (2) Given nums = [1, 3, 2, 2, 3, 1], one possible answer is [2, 3, 1, 3, 1, 2].
 *  
 *  Note:
 *  You may assume all input has valid answer.
 *  
 *  Follow Up:
 *  Can you do it in O(n) time and/or in-place with O(1) extra space?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P324_WiggleSortII.java
 * @type        P324_WiggleSortII
 * @date        2017年1月9日 下午9:09:56
 * @details     Solution1: TLE
 * @details     Solution2: AC 8ms 62.29%
 */
public class P324_WiggleSortII {
	static class Solution1 {
	    public void wiggleSort(int[] nums) {
	        if (nums == null || nums.length < 2) return;
	        int length = nums.length;
	        int i = 0, j = 0, realI = 0, realJ = 0;
	        for (i = 0; i < length; i ++) {
	            realI = getRealIndex(i, length);
	            for (j = i + 1; j < length; j ++) {
	                realJ = getRealIndex(j, length);
	                if (nums[realI] < nums[realJ]) {
	                    swap(nums, realI, realJ);
	                }
	            }
	        }
	    }
	    private int getRealIndex(int index, int length) {
	         if (index < length / 2) {
	             return 2 * index + 1;
	         } else  {
	             return (index - length / 2) * 2;
	         }
	    }
	    private void swap(int[] nums, int i, int j) {
	        int temp = nums[i];
	        nums[i] = nums[j];
	        nums[j] = temp;
	    }
	}
	static class Solution2 {
	    public void wiggleSort(int[] nums) {
	        if (nums == null || nums.length < 2) return;
	        int[] copy = Arrays.copyOf(nums, nums.length);
	        Arrays.sort(copy);
	        int copyIndex = copy.length - 1;
	        int numsIndex = 1;
	        while (numsIndex < nums.length) {
	            nums[numsIndex] = copy[copyIndex];
	            copyIndex --;
	            numsIndex += 2;
	        }
	        numsIndex = 0;
	        numsIndex = (numsIndex % 2 == 1 ? numsIndex - 1 : numsIndex);
	        while (numsIndex < nums.length) {
	            nums[numsIndex] = copy[copyIndex];
	            copyIndex --;
	            numsIndex += 2;
	        }
	    }
	}
}
