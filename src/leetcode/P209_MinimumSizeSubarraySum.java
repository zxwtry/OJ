package leetcode;

/*
 * 	Given an array of n positive integers and a positive integer s, 
 * 	find the minimal length of a subarray of which the sum ≥ s. 
 * 	If there isn't one, return 0 instead.

	For example, given the array [2,3,1,2,4,3] and s = 7,
	the subarray [4,3] has the minimal length under the problem
	 constraint.
	
	click to show more practice.
	
	More practice:
	If you have figured out the O(n) solution, try coding another solution of which the time complexity is O(n log n).
 */

public class P209_MinimumSizeSubarraySum {
	public static void main(String[] args) {
		int[] nums = new int[] {2,3,1,2,4,3};
		int s = 7;
		Solution1 s1 = new Solution1();
		Solution2 s2 = new Solution2();
		System.out.println(s1.minSubArrayLen(s, nums));
		System.out.println(s2.minSubArrayLen(s, nums));
//		int[] nums = tools.Random随机生成器.A_生成一个随机数据(100, 0, 1000);
//		String s = tools.Utils.LEETCODE_int_array_序列化_(nums);
//		System.out.println(s);
	}
	/*
	 * 	提示上面你说有O(N)时间复杂度的方法
	 * 	那么肯定是DP了
	 * 	TLE了
	 *	这个就GG了 
	 *	这个应该是O(N^2)
	 *	毫无疑问TLE
	 */
	static class Solution1 {
	    public int minSubArrayLen(int s, int[] nums) {
	    	int sum = 0;
	    	int preIndex = 0;
	    	int ans = Integer.MAX_VALUE;
	    	for (int i = 0; i < nums.length; i ++) {
	    		if (nums[i] >= s) {
	    			return 1;
	    		}
	    		sum += nums[i];
	    		if (sum >= s) {
	    			ans = Math.min(ans, i - preIndex + 1);
	    		}
	    		int newSum = sum;
	    		int newPreIndex = preIndex;
	    		while (sum >= s) {
	    			newSum -= nums[newPreIndex];
	    			if (newSum < s) {
	    				sum = newSum + nums[newPreIndex];
	    				preIndex = newPreIndex;
	    				ans = Math.min(ans, i - newPreIndex + 1);
	    				break;
	    			}
	    			newPreIndex ++;
	    		}
	    	}
	        return ans == Integer.MAX_VALUE ? 0 : ans;
	    }
	}
	/*
	 * 	一个又一个地去试
	 * 	继续TLE
	 */
	static class Solution2 {
		public int minSubArrayLen(int s, int[] nums) {
			int ans = Integer.MAX_VALUE;
			for (int i = 0; i < nums.length; i ++) {
				int val = s;
				for (int j = i; j < nums.length; j ++) {
					val -= nums[j];
					if (val <= 0) {
						ans = Math.min(ans, j - i + 1);
						break;
					}
				}
			}
			return ans == Integer.MAX_VALUE ? 0 : ans;
		}
	}
}
