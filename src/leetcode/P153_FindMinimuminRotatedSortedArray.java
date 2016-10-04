package leetcode;

import java.util.Arrays;

/*
 * 	Suppose a sorted array is rotated at some pivot unknown to you beforehand.

	(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	
	Find the minimum element.
	
	You may assume no duplicate exists in the array.
 */

public class P153_FindMinimuminRotatedSortedArray {
	public static void main(String[] args) {
		int[] arr = tools.Random随机生成器.A_生成一个不重复随机数据(100, 0, 100);
		Arrays.sort(arr);
		tools.Utils.printArray(arr, 100);
		Solution s = new Solution();
		int selectIndex = 0;
		for (; selectIndex < arr.length; selectIndex ++) {
			int ansIndex = s.binaySearch(arr, 0, arr.length - 1, arr[selectIndex]);
			System.out.println(arr[ansIndex] == arr[selectIndex]);
		}
	}
	static class Solution {
	    public int findMin(int[] nums) {
	        return 0;
	    }
	    /*
	     * 	sti - 1: 大于最大
	     * 	eni + 1: 小于最小
	     */
	    int binaySearch(int[] nums, int sti, int eni, int val) {
	    	if (val < nums[sti]) {
	    		return sti - 1;
	    	}
	    	if (val > nums[eni]) {
	    		return eni + 1;
	    	}
	    	int mid = 0;
	    	while (sti < eni) {
	    		mid = (sti + eni) / 2;
	    		if (nums[mid] == val) {
	    			return mid;
	    		} else if (nums[mid] > val) {
	    			eni = mid - 1;
	    		} else {
	    			sti = mid + 1;
	    		}
	    	}
	    	return sti;
	    }
	}
}
