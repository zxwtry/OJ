package leetcode;

/**
 * 	Given an array of n integers where n > 1, nums, return an array output such that
 *  output[i] is equal to the product of all the elements of nums except nums[i].
 *	
 *	Solve it without division and in O(n).
 *	
 *	For example, given [1,2,3,4], return [24,12,8,6].
 *	
 *	Follow up:
 *	Could you solve it with constant space complexity? (Note: The output array
 *	 does not count as extra space for the purpose of space complexity analysis.)
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P238_ProductofArrayExceptSelf.java
 * @type        P238_ProductofArrayExceptSelf
 * @date        2016年12月10日 下午10:25:35
 * @details     
 */
public class P238_ProductofArrayExceptSelf {
	public static void main(String[] args) {
		int[] nums = new int[] {4, 6, 8, 12, 16, 15};
		Solution2 sol2 = new Solution2();
		sol2.productExceptSelf(nums);
		tools.Utils.printArray(nums, nums.length);
	}
	static class Solution1 {
	    public int[] productExceptSelf(int[] nums) {
	    	int sign = nums[0], g = 0, next = 1;
	    	int[] ans = new int[nums.length];
	    	for (int i = 1; i < nums.length; i ++) {
	    		g = gcd(sign, nums[i]);
	    		sign /= g;
	    		sign *= nums[i];
	    	}
	    	for (int i = 0; i < nums.length; i ++) {
	    		g = gcd(nums[i], sign / nums[i]);
	    	}
	    	return ans;
	    }
	    public int gcd(int a, int b) {
	    	return b == 0 ? a : gcd(b, a % b);
	    }
	}
	static class Solution2 {
		public int[] productExceptSelf(int[] nums) {
			
		}
	}
	
}
