package leetcode;

import java.util.Arrays;

/*
 * 	Follow up for "Find Minimum in Rotated Sorted Array":
	What if duplicates are allowed?
	
	Would this affect the run-time complexity? How and why?
	Suppose a sorted array is rotated at some pivot unknown to you beforehand.
	
	(i.e., 0 1 2 4 5 6 7 might become 4 5 6 7 0 1 2).
	
	Find the minimum element.
	
	The array may contain duplicates.
 */
public class P154_FindMinimuminRotatedSortedArrayII {
	public static void main(String[] args) {
		int count = 0;
		for (int len = 1; len < 900; len ++) {
			for (int i = 0; i < len; i ++) {
				int[] arr = tools.Random随机生成器.A_生成一个随机数据(len, -len/16, len/16);
				Arrays.sort(arr);
				int[] arr_input = rotate(arr, i);
				int origin_min = arr[0];
				Solution s = new Solution();
				int solution_min = s.findMin(arr_input);
				if (origin_min != solution_min) {
					count ++;
//					System.out.println(len + "..." + i + "..." + origin_min + "..." + solution_min);
				}
				s.findMin(arr_input);
			}
		}
		System.out.println(count);
	}
	static int[] rotate(int[] arr_origin, int index) {
		int[] arr = arr_origin.clone();
		reverse(arr, 0, index );
		reverse(arr, index + 1, arr.length - 1);
		reverse(arr, 0, arr.length - 1);
		return arr;
	}
	static void reverse(int[] arr, int sti, int eni) {
		while(sti < eni) {
			int temp = arr[sti];
			arr[sti] = arr[eni];
			arr[eni] = temp;
			sti ++;
			eni --;
		}
	}
	/*
	 * 	1 ms
	 * 	9.46% 
	 */
	static class Solution {
	    public int findMin(int[] nums) {
	    	if (nums == null || nums.length == 0) {
	    		return 0;
	    	}
	    	if (nums.length == 1) {
	    		return nums[0];
	    	}
	        return getMin(nums, 0, nums.length - 1);
	    }
	    int getMin(int[] nums, int sti, int eni) {
	    	if (sti == eni) {
	    		return nums[0];
	    	}
	    	int mid = (sti + eni) / 2;
//	    	 System.out.printf("%d...%d...%d\r\n", nums[sti], nums[mid], nums[eni]);
	    	if (nums[mid] > nums[sti]) {
	    		if (nums[mid- 1] > nums[mid]) {
	    			return nums[mid];
	    		}
	    		if (nums[mid + 1] < nums[mid]) {
	    			return nums[mid + 1];
	    		}
	    		return getMin(nums, mid + 1, eni);
	    	} else if (nums[mid] < nums[sti]) {
	    		if (nums[mid- 1] > nums[mid]) {
	    			return nums[mid];
	    		}
	    		if (nums[mid + 1] < nums[mid]) {
	    			return nums[mid + 1];
	    		}
	    		return getMin(nums, sti, mid - 1);
	    	} else {
	    		if (nums[sti] > nums[eni] && sti + 1== eni) {
	    			return nums[eni];
	    		}
	    		if (nums[sti] == nums[sti + 1]) {
	    			return getMin(nums, sti + 1, eni);
	    		}
	    		for (int k = sti + 1; k <= eni; k ++) {
	    			if (nums[k] < nums[sti]) {
	    				return nums[k];
	    			}
	    		}
	    		return nums[0];
	    	}
	    }
	    /*
	     * 	sti - 1: 小于最小
	     * 	eni + 1: 大于最大
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
