package leetcode;
/*
 * 	Given an array of size n, find the majority element. 
 * The majority element is the element that appears 
 * more than ⌊ n/2 ⌋ times.

	You may assume that the array is non-empty and the majority element 
	always exist in the array.
	
	Credits:
	Special thanks to @ts for adding this problem and creating
	 all test cases.
 */

public class P169_MajorityElement {
	public static void main(String[] args) {
		
	}
	/*
	 *	2 ms 51.12%
	 */
	static class Solution {
	    public int majorityElement(int[] nums) {
	    	int ans = nums[0], times = 1;
	    	for (int i = nums.length - 1; i > 0; i --) {
	    		if (nums[i] != ans) {
	    			if (times <= 0) {
		    			ans = nums[i];
		    			times = 1;
	    			} else {
	    				times --;
	    			}
	    		} else {
	    			times ++;
	    		}
	    	}
	        return ans;
	    }
	}
}
