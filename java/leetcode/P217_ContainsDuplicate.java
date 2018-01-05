package leetcode;

/*
 * 	Given an array of integers, find if the array contains any duplicates.
 *  Your function should return true if any value appears at least twice 
 *  in the array, and it should return false if every element is distinct.
 */
public class P217_ContainsDuplicate {
	public static void main(String[] args) {
		
	}
	/*
	 * 	3 ms
	 * 	99.14%
	 */
	static class Solution {
	    public boolean containsDuplicate(int[] nums) {
	    	int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;
	    	for (int val : nums) {
	    		if (val < min) {
	    			min = val;
	    		} else if (val == min) {
	    			return true;
	    		}
	    		if (val > max) {
	    			max = val;
	    		} else if (val == max) {
	    			return true;
	    		}
	    	}
	    	boolean[] isAppeared = new boolean[max - min];
	    	for (int val : nums) {
	    		if (isAppeared[val - min]) {
	    			return true;
	    		} else {
	    			isAppeared[val - min] = true;
	    		}
	    	}
	        return false;
	    }
	}
}
