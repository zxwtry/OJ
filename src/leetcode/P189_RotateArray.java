package leetcode;

//import java.sql.Savepoint;

/*
 * 	Rotate an array of n elements to the right by k steps.

	For example, with n = 7 and k = 3, the array [1,2,3,4,5,6,7] is rotated to [5,6,7,1,2,3,4].
	
	Note:
	Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 */

public class P189_RotateArray {
	public static void main(String[] args) {
		boolean isAllSame = true;
		for (int len = 1; len < 10000; len ++) {
			Solution3 s = new Solution3();
			int[] nums = tools.Random随机生成器.A_生成一个随机数据(len, - 6 * len, 20 * len);
			int k = 2;
			int[] numsSave1 = null;
			s.rotate(numsSave1 = nums.clone(), k);
			Solution s1 = new Solution();
			int[] numsSave2 = null;
			s1.rotate(numsSave2 = nums.clone(), k);
			boolean isSame = true;
			for (int i = 0; i < nums.length; i ++) {
				isSame &= numsSave1[i] == numsSave2[i];
			}
			isAllSame &= isSame;
		}
		System.out.println(isAllSame);
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
	/*
	 * 	1 ms
	 * 	12.84%
	 */
	static class Solution3 {
		public void rotate(int[] nums, int k) {
			int count = 0;
			int indexDefault = 0;
			do {
				int index = indexDefault, nextIndex = 0;
				int save = nums[index];
				int temp = 0;
				do {
					nextIndex = (k + index) % nums.length;
					temp = nums[nextIndex];
					nums[nextIndex] = save;
					save = temp;
					index = nextIndex;
					count ++;
				} while (index != indexDefault);
				indexDefault ++;
			} while (count <  nums.length);
		}
	}
}
