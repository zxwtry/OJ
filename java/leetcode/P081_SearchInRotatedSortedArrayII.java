package leetcode;


/*
 * 	Follow up for "Search in Rotated Sorted Array":
	What if duplicates are allowed?
	
	Would this affect the run-time complexity? How and why?
	
	Write a function to determine if a given target is in the array.
 */

public class P081_SearchInRotatedSortedArrayII {
	public static void main(String[] args) {
		int[] nums = null;
//		nums = new int[] {4,5,5,6,7,0,1,2};
//		nums = new int[] {3, 1};
		nums = new int[] {4,5,6,7,0,1,2};
		int target = 2;
		System.out.println(new Solution().search(nums, target));
	}
	/*
	 * 	AC
	 * 	试错型AC
	 * 	1 ms
	 */
	static class Solution {
	    public boolean search(int[] nums, int target) {
	    	if (nums == null || nums.length == 0)
	    		return false;
	    	if (nums.length == 1)
	    		return nums[0] == target;
	    	int breakIndex = 1;
	    	while (true) {
	    		if (breakIndex == nums.length 
	    				|| nums[breakIndex - 1] > nums[breakIndex])
	    			break;
	    		breakIndex ++;
	    	}
	    	int ans = binaryIndex(nums, 0, breakIndex - 1, target);
	    	if (ans != -1)
	    		return true;
	    	return binaryIndex(nums, breakIndex, nums.length - 1, target) != -1;
	    }
	    int binaryIndex(int[] nums, int sti, int eni, int target) {
	    	if (sti > eni)
	    		return -1;
	    	int cal = (nums[sti] - target) * (nums[eni] - target);
	    	if ( cal  > 0 )
	    		return -1;
	    	else if (cal == 0)
	    		return sti;
	    	while (sti <= eni) {
	    		int mid = (sti + eni) >> 1;
	    		if (nums[mid] == target) {
	    			return mid;
	    		} else if (nums[mid] > target) {
	    			eni = mid - 1;
	    		} else {
	    			sti = mid + 1;
	    		}
	    	}
	    	return -1;
	    }
	}
}
