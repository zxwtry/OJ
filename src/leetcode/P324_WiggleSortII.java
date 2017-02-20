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
 * @details     Solution1: TLE              n^2
 * @details     Solution2: AC 8ms 62.29%    n*log(n)
 * @details     Solution3: StackOverflowError
 */
public class P324_WiggleSortII {
    public static void main(String[] args) {
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        Solution3 solution3 = new Solution3();
        int[] arr1 = tools.Random随机生成器.A_生成一个随机数据(15, 0, 4);
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);
        int[] arr3 = Arrays.copyOf(arr1, arr1.length);
        solution1.wiggleSort(arr1);
        solution2.wiggleSort(arr2);
        solution3.wiggleSort(arr3);
        tools.Utils.printArray(arr1, arr1.length);
        tools.Utils.printArray(arr2, arr1.length);
        tools.Utils.printArray(arr3, arr1.length);
    }
	static class Solution1 {
	    public void wiggleSort(int[] nums) {
	        if (nums == null || nums.length < 2) return;
	        int length = nums.length;
	        int j = 0, realI = 0, realJ = 0;
	        boolean isSwap = true;
	        while(isSwap) {
	            isSwap = false;
	            for (j = 1; j < length; j ++) {
	                realJ = getRealIndex(j, length);
	                realI = getRealIndex(j - 1, length);
	                if (nums[realI] < nums[realJ]) {
	                    swap(nums, realI, realJ);
	                    isSwap = true;
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
	static class Solution3 {
        public void wiggleSort(int[] nums) {
            if (nums == null || nums.length < 2) return;
            qsort(nums, 0, nums.length - 1);
        }
        private void qsort(int[] nums, int i, int j) {
            if(i < j) {
                int p = partition(nums, i, j);
                qsort(nums, i, p - 1);
                qsort(nums, p + 1, j);
            }
        }
        private int partition(int[] nums, int i, int j) {
            int saveValue = nums[getRealIndex(i, nums.length)];
            while (i < j) {
                while (i < j && nums[getRealIndex(j, nums.length)] <= saveValue) j --;
                nums[getRealIndex(i, nums.length)] = nums[getRealIndex(j, nums.length)];
                while (i < j && nums[getRealIndex(i, nums.length)] >= saveValue) i ++;
                nums[getRealIndex(j, nums.length)] = nums[getRealIndex(i, nums.length)];
            }
            nums[getRealIndex(i, nums.length)] = saveValue;
            return i;
        }
        private int getRealIndex(int index, int length) {
             if (index < length / 2) {
                 return 2 * index + 1;
             } else  {
                 return (index - length / 2) * 2;
             }
        }
    }
}
