package leetcode;


/*
 * 	Given an array with n objects colored red, white or blue,
 *  sort them so that objects of the same color are adjacent, 
 *  with the colors in the order red, white and blue.

	Here, we will use the integers 0, 1, and 2 to 
	represent the color red, white, and blue respectively.
	
	Note:
	You are not suppose to use the library's sort function 
	for this problem.
 */

public class P075_SortColors {
	public static void main(String[] args) {
//		int[] in = new int[] {1, 2, 1, 0, 1, 2, 0, 1, 2};
//		int[] in = new int[] {2, 1, 1, 1, 0, 1, 1, 1, 1};
		int[] in = new int[] {0, 0};
		new Solution().sortColors(in);
		tools.Utils.printArray(in, 10);
	}
	/*
	 * 	WA了一次，在第一次写条件的时候，尽量写全一点
	 * 	1 ms
	 */
	static class Solution {
	    public void sortColors(int[] nums) {
	    	if (nums == null || nums.length < 2)
	    		return;
	        int sti = 0, eni = nums.length - 1, mid = 0;
	        boolean isDone = false;
	        while (! isDone) {
	        	while (eni >= sti && nums[eni] == 2)
	        		eni --;
	        	while (sti <= eni && nums[sti] == 0)
	        		sti ++;
	        	if (sti >= eni)
	        		isDone = true;
        		if (isDone)	break;
	        	if (nums[sti] == 2) {
	        		swap(nums, sti, eni);
	        		eni --;
	        	} else if (nums[eni] == 0) {
	        		swap(nums, sti, eni);
	        		sti ++;
	        	} else {
	        		mid = sti + 1;
	        		while (nums[mid] == 1) {
	        			mid ++;
	        			if (mid >= eni) {
	        				isDone = true;
	        				break;
	        			}
	        		}
	        		if (isDone)	break;
	        		if (nums[mid] == 0) {
	        			swap(nums, sti, mid);
	        			sti ++;
	        		} else if (nums[mid] == 2) {
	        			swap(nums, mid, eni);
	        			eni --;
	        		}
	        	}
	        }
	    }
	    void swap(int[] nums, int i , int j) {
	    	int temp = nums[i];
	    	nums[i] = nums[j];
	    	nums[j] = temp;
	    }
	}
}
