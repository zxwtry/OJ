package leetcode;

/*
 * 	Rotate an array of n elements to the right by k steps.

	For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
	
	Note:
	Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 */

public class P189_RotateArray {
	public static void main(String[] args) {
		Solution2 s = new Solution2();
		int[] nums = new int[] {1,2,3,4,5,6,7};
		int k = 5;
		int[] numsSave = nums;
//		s.rotate(numsSave = nums.clone(), k);
		s.rotate(nums, k);
		tools.Utils.printArray(numsSave, 100);
		Solution s1 = new Solution();
//		s1.rotate(numsSave = nums.clone(), k);
		s1.rotate(nums, k);
		tools.Utils.printArray(numsSave, 100);
	}
	/*
	 * 	1 ms
	 * 	12.84%
	 */
	static class Solution {
	    public void rotate(int[] nums, int k) {
	    	k = k % nums.length;
	    	rotate(nums, 0, nums.length - 1);
	        rotate(nums, 0, k - 1);
	        rotate(nums, k, nums.length - 1);
	    }
	    void rotate(int[] nums, int sti, int eni) {
	    	while (sti < eni) {
	    		int temp = nums[sti];
	    		nums[sti] = nums[eni];
	    		nums[eni] = temp;
	    		sti ++;
	    		eni --;
	    	}
	    }
	}
	/*
	 * 	1 ms
	 * 	12.84%
	 */
	static class Solution2 {
	    public void rotate(int[] nums, int k) {
	    	k = k % nums.length;
	    	if (k == 0) {
	    		return;
	    	}
	    	int[] save = nums.clone();
	    	System.arraycopy(save, 0, nums, k, nums.length - k);
	    	System.arraycopy(save, nums.length - k, nums, 0, k);
	    }
	}
}
