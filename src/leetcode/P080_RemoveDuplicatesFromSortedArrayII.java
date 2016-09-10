package leetcode;

/*
 * 	Follow up for "Remove Duplicates":
	What if duplicates are allowed at most twice?
	
	For example,
	Given sorted array nums = [1,1,1,2,2,3],
	
	Your function should return length = 5, with the first five elements
	 of nums being 1, 1, 2, 2 and 3. It doesn't matter what you leave
	  beyond the new length.
 */

public class P080_RemoveDuplicatesFromSortedArrayII {
	public static void main(String[] args) {
		System.out.println(new Solution().removeDuplicates(new int[] {
				1, 1, 1, 1, 2, 2, 2, 3, 3, 5, 4, 4, 4, 4, 7, 8,8,8,10
		}));
	}
	/*
	 * 	WA一次
	 * 	2 ms
	 */
	static class Solution {
		int ans = 0;
	    public int removeDuplicates(int[] nums) {
	    	if (nums == null || nums.length == 0)
	    		return ans;
	    	int theSame = 1, pre = 0;
	    	int[] step = new int[nums.length];
	    	for (int i = 1; i <= nums.length; i ++) {
	    		if (i == nums.length || nums[i] != nums[i - 1]) {
	    			if (theSame > 2) {
	    				ans += 2;
		    			step[i - 1] = pre + theSame - 2;
		    			pre += theSame - 2;
	    			} else {
	    				ans += theSame;
	    			}
	    			theSame = 1;
	    		} else {
	    			theSame ++;
	    		}
	    	}
	    	tools.Utils.printArray(step, 10);
	    	int step_now = 0;
	    	for (int i = 0; i != nums.length; i ++) {
	    		if (step_now != 0)
	    			nums[i - step_now] = nums[i];
	    		if (step[i] != 0)
	    			step_now = step[i];
	    	}
	    	for (int i = 0; i != ans; i ++) {
	    		System.out.print(nums[i] + " ");
	    	}
	    	System.out.println();
	        return ans;
	    }
	}
}
