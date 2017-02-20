package leetcode;

import java.util.Arrays;
import java.util.LinkedList;

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
 * @details     Solution1: TLE                  n^2
 * @details     Solution2: AC 8ms 62.29%        n*log(n)
 * @details     Solution3: StackOverflowError   n*log(n)
 * @details     Solution4: AC 527ms 0.68%      n*log(n)
 */
public class P324_WiggleSortII {
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
	static class Solution4 {
        public void wiggleSort(int[] nums) {
            if (nums == null || nums.length < 2) return;
            LinkedList<Integer> is = new LinkedList<Integer>();
            LinkedList<Integer> js = new LinkedList<Integer>();
            is.add(0);
            js.add(nums.length - 1);
            int p = 0, i = 0, j = 0;
            while (! is.isEmpty()) {
                i = is.pollFirst();
                j = js.pollFirst();
                if (i < j) {
                    p = partition(nums, i, j);
                    is.addFirst(p + 1);
                    js.addFirst(j);
                    is.addFirst(i);
                    js.addFirst(p - 1);
                }
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
