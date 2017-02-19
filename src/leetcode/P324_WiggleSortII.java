package leetcode;

import java.util.Arrays;

import com.sun.org.apache.bcel.internal.generic.IF_ACMPEQ;

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
 * @details     Solution1: WA
 * @details     Solution2: AC 8ms 62.29%
 */
public class P324_WiggleSortII {
    public static void main(String[] args) {
        int[] nums = {0, 1, 2, 3, 4, 5, 6, 7, 3, 3, 3, 3};
        Solution2 solution1 = new Solution2();
        solution1.wiggleSort(nums);
        tools.Utils.printArray(nums, nums.length);
    }
	static class Solution1 {
	    //0, 1, 2, 3, 4, 5, 6, 7, 3, 3, 3, 3
	    public void wiggleSort(int[] nums) {
	        if (nums == null || nums.length < 2) return;
	        Arrays.sort(nums);
	        int startIndex = 1, middleIndex = nums.length / 2;
	        middleIndex = (middleIndex % 2 == 0 ? middleIndex : middleIndex + 1);
	        while (middleIndex < nums.length) {
	            swap(nums, startIndex, middleIndex);
	            startIndex += 2;
	            middleIndex += 2;
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
