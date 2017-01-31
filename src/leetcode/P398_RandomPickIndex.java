package leetcode;

/**
 * 	Given an array of integers with possible duplicates, 
 * 	randomly output the index of a given target number. 
 * 	You can assume that the given target number must exist in the array.
 *	
 *	Note:
 *	The array size can be very large. Solution that uses too much extra space 
 *	will not pass the judge.
 *	
 *	Example:
 *	
 *	int[] nums = new int[] {1,2,3,3,3};
 *	Solution solution = new Solution(nums);
 *	
 *	// pick(3) should return either index 2, 3, or 4 randomly. Each index should have equal probability of returning.
 *	solution.pick(3);
 *	
 *	// pick(1) should return 0. Since in the array only nums[0] is equal to 1.
 *	solution.pick(1);
 */

public class P398_RandomPickIndex {
	public static void main(String[] args) {
//		Solution s = new Solution(new int[] {6, 2, 2, 1});
		Solution s = new Solution(new int[] {1, 2, 2, 2, 6, 6});
//		Solution s = new Solution(new int[] {6, 2, 2, 2, 2, 2, 1});
		System.out.println(s.pick(6));
	}
	/*
	 * 	一次AC
	 * 	395 ms
	 */
	static class Solution {
		int[] nums = null, index = null;
	    public Solution(int[] nums) {
	        this.nums = nums;
	        index = new int[nums.length];
	        for (int i = 0; i != index.length; i ++)
	        	index[i] = i;
	    }
	    public int pick(int target) {
	    	sort(0, nums.length - 1);
	    	int numi = bin_search_down(target);
	    	int numj = bin_search_up(target);
	    	int random = (int)( Math.random() * (numj - numi + 1) ) + numi;
	        return index[random];
	    }
		private int bin_search_up(int target) {
			int sti = 0, eni = nums.length - 1;
			while (sti <= eni) {
				int mid = (sti + eni) >> 1;
				if (nums[mid] <= target) {
					sti = mid + 1;
				} else {
					eni = mid - 1;
				}
			}
			return sti - 1;
		}
		private int bin_search_down(int target) {
			int sti = 0, eni = nums.length - 1;
			while (sti <= eni) {
				int mid = (sti + eni) >> 1;
				if (nums[mid] >= target) {
					eni = mid - 1;
				} else {
					sti = mid + 1;
				}
			}
			return sti;
		}
		private void sort(int sti, int eni) {
			if (sti >= eni)
				return;
			int p = part(sti, eni);
			sort(sti, p - 1);
			sort(p + 1, eni);
		}
		private int part(int sti, int eni) {
			int save_val = nums[sti];
			int save_index = index[sti];
			while (sti < eni) {
				while (sti < eni && nums[eni] >= save_val)	eni --;
				nums[sti] = nums[eni];
				index[sti] = index[eni];
				while (sti < eni && nums[sti] <= save_val)	sti ++;
				nums[eni] = nums[sti];
				index[eni] = index[sti];
			}
			nums[sti] = save_val;
			index[sti] = save_index;
			return sti;
		}
	}
}

/**
 * Your Solution object will be instantiated and called as such:
 * Solution obj = new Solution(nums);
 * int param_1 = obj.pick(target);
 */
