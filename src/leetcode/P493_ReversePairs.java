package leetcode;

import java.util.ArrayList;

/**
 *	Given an array nums, we call (i, j) an important reverse pair if i < j and 
 *	nums[i] > 2*nums[j].
 *	You need to return the number of important reverse pairs in the given array.
 *	Example1:
 *	Input: [1,3,2,3,1]
 *	Output: 2
 *	Example2:
 *	Input: [2,4,3,5,1]
 *	Output: 3
 *	Note:
 *	The length of the given array will not exceed 50,000.
 *	All the numbers in the input array are in the range of 32-bit integer.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P493_ReversePairs.java
 * @type        P493_ReversePairs
 * @date        2017年2月12日 下午4:03:34
 * @details     Solution1: AC 880 ms
 * @details     Solution2: AC 808 ms
 */
public class P493_ReversePairs {
	static class Solution1 {
	    public int reversePairs(int[] nums) {
	    	int count = 0;
	    	ArrayList<Integer> list = new ArrayList<Integer>();
	    	int leftIndex = 0, rightIndex = 0, middleIndex = 0, minus = 0;
	    	for (int index = nums.length - 1; index > -1; index --) {
	    		leftIndex = 0;
	    		rightIndex = list.size();
	    		while (leftIndex < rightIndex) {
	    			middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
	    			minus = cmp2(nums, index, list, middleIndex);
	    			if (minus > 0) leftIndex = middleIndex + 1;
	    			else rightIndex = middleIndex;
	    		}
	    		count += leftIndex;
	    		leftIndex = 0;
	    		rightIndex = list.size();
	    		while (leftIndex < rightIndex) {
	    			middleIndex = leftIndex + (rightIndex - leftIndex) / 2;
	    			minus = cmp1(nums, index, list, middleIndex);
	    			if (minus > 0) leftIndex = middleIndex + 1;
	    			else rightIndex = middleIndex;
	    		}
	    		list.add(rightIndex, nums[index]);
	    	}
	    	return count;
	    }
	    private int cmp1(int[] nums, int numsIndex, ArrayList<Integer> list, int listIndex) {
	    	long cut = (long)nums[numsIndex] - (long)list.get(listIndex);
	    	if (cut == 0) {
	    		return 0;
	    	} else {
	    		return cut > 0 ? 1 : -1;
	    	}
	    }
	    private int cmp2(int[] nums, int numsIndex, ArrayList<Integer> list, int listIndex) {
	    	long cut = (long)nums[numsIndex] - 2 * (long)list.get(listIndex);
	    	if (cut == 0) {
	    		return 0;
	    	} else {
	    		return cut > 0 ? 1 : -1;
	    	}
	    }
	}
	
	static class Solution2 {
		public int reversePairs(int[] nums) {
	    	int count = 0;
	    	ArrayList<Integer> list = new ArrayList<Integer>();
	    	for (int index = nums.length - 1; index > -1; index --) {
	    		int countAdd = binaryNumberSmallerThanHalf(nums, index, list);
	    		list.add(binaryFirstLarger(nums, index, list), nums[index]);
	    		count += countAdd;
	    	}
	    	return count;
		}
		public int binaryNumberSmallerThanHalf(int[] nums, int numsIndex, ArrayList<Integer> list) {
			//list从小到大
			if (list.size() == 0) return 0;
			int listLeftIndex = 0, listRightIndex = list.size() - 1;
			if (cmp2(nums, numsIndex, list, listLeftIndex) <= 0) return 0;
			if (cmp2(nums, numsIndex, list, listRightIndex) > 0) return list.size();
			int listMiddleIndex = 0;
			int cut = 0;
			while (listLeftIndex < listRightIndex) {
				listMiddleIndex = (listLeftIndex + listRightIndex) / 2;
				cut = cmp2(nums, numsIndex, list, listMiddleIndex);
				if (cut > 0) {
					listLeftIndex = listMiddleIndex + 1;
				} else {
					listRightIndex = listMiddleIndex;
				}
			}
			return listLeftIndex;
		}
		private int binaryFirstLarger(int[] nums, int numsIndex, ArrayList<Integer> list) {
			//list从小到大
			if (list.size() == 0) return 0;
			int listLeftIndex = 0, listRightIndex = list.size() - 1;
			if (cmp1(nums, numsIndex, list, listRightIndex) >= 0) return listRightIndex + 1;
			if (cmp1(nums, numsIndex, list, listLeftIndex) <= 0) return listLeftIndex;
			int listMiddleIndex = 0;
			int cut = 0;
			while (listLeftIndex < listRightIndex) {
				listMiddleIndex = (listLeftIndex + listRightIndex + 1) / 2;
				cut = cmp1(nums, numsIndex, list, listMiddleIndex);
				if (cut > 0) {
					listLeftIndex = listMiddleIndex;
				} else if (cut == 0) {
					return listMiddleIndex;
				} else {
					listRightIndex = listMiddleIndex - 1;
				}
			}
			return listRightIndex + 1;
		}
		private int cmp1(int[] nums, int numsIndex, ArrayList<Integer> list, int listIndex) {
	    	long cut = (long)nums[numsIndex] - (long)list.get(listIndex);
	    	if (cut == 0) {
	    		return 0;
	    	} else {
	    		return cut > 0 ? 1 : -1;
	    	}
	    }
	    private int cmp2(int[] nums, int numsIndex, ArrayList<Integer> list, int listIndex) {
	    	long cut = (long)nums[numsIndex] - 2 * (long)list.get(listIndex);
	    	if (cut == 0) {
	    		return 0;
	    	} else {
	    		return cut > 0 ? 1 : -1;
	    	}
	    }
	}
	
	static class StandardSolution {
		public int reversePairs(int[] nums) {
	       if (nums == null || nums.length < 2) return 0; 
	       int count = 0;
	       for (int i = 0; i < nums.length; i ++) {
	    	   for (int j = i + 1; j < nums.length; j ++) {
	    		   count += isReversePairs(nums, i, j) ? 1 : 0;
	    	   }
	       }
	       return count;
	    }
	    private boolean isReversePairs(int[] nums, int i, int j) {
	    	return i < j && nums[i] > 2 * nums[j];
	    }
	}
}
