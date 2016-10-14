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
		nums = tools.Random随机生成器.A_生成一个随机数据(400, 0, 1000);
		nums = tools.FileUtils.A_读取一维int数组_空格分割("d:/file/data/1014_leetcode209.txt");
		int s = 120331635;
		Solution1 s1 = new Solution1();
		Solution4 s4 = new Solution4();
		System.out.println(s1.minSubArrayLen(s, nums));
		System.out.println(s4.minSubArrayLen(s, nums));
//		int[] nums = tools.Random随机生成器.A_生成一个随机数据(400, 0, 1000);
//		Arrays.sort(nums);
//		Solution4 s4 = new Solution4();
//		int index = s4.getIndex2(600, nums);
//		System.out.println(nums[index - 1] + "..." + nums[index] + "..." + nums[index + 1]);
		
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
	/*
	 * 	适当纪录一些信息，看看能不能使用
	 * 	还是TLE
	 */
	static class Solution3 {
		public int minSubArrayLen(int s, int[] nums) {
			if (nums == null) {
				return 0;
			}
			int ans = Integer.MAX_VALUE;
			int[] recordOfAns = new int[nums.length];
			for (int i = 0; i < nums.length; i ++) {
				int val = s;
				int j = i;
				for (; j < nums.length; j ++) {
					val -= nums[j];
					if (val <= 0) {
						ans = Math.min(ans, j - i + 1);
						break;
					}
				}
				if (j == nums.length) {
					recordOfAns[i] = -1;
					return ans == Integer.MAX_VALUE ? 0 : ans;
				} else {
					recordOfAns[i] = j - i + 1;
				}
			}
			return ans == Integer.MAX_VALUE ? 0 : ans;
		}
	}
	static class Solution4 {
		public int minSubArrayLen(int s, int[] nums) {
			if (nums == null || nums.length == 0) {
				return 0;
			}
			int ans = Integer.MAX_VALUE;
			int len = nums.length;
			int[] sum = new int[len];
			int add = 0;
			for (int i = 0; i < len; i ++) {
				add += nums[i];
				if (nums[i] > s) {
					return 1;
				}
				sum[i] = add;
			}
			if (sum[len - 1] < s) {
				return 0;
			}
			int sti = getIndex1(s, sum);
			for (int i = sti; i < sum.length; i ++) {
				int preI = getIndex2(sum[i] - s, sum);
				ans = Math.min(ans, i - preI);
			}
			return ans;
		}
		int getIndex1(int val, int[] sum) {
			int sti = 0, eni = sum.length - 1;
			if (sum[eni] < val) {
				return eni + 1;
			}
			while (sti < eni) {
				int mid = (sti + eni) / 2;
				if (sum[mid] == val) {
					return mid;
				} else if (sum[mid] < val) {
					sti = mid + 1;
				} else {
					eni = mid;
				}
			}
			return sti;
		}
		int getIndex2(int val, int[] sum) {
			int sti = 0, eni = sum.length - 1;
			if (val < sum[sti]) {
				return sti - 1;
			}
			while (sti < eni) {
				int mid = (sti + eni + 1) / 2;
				if (sum[mid] == val) {
					return mid;
				} else if (sum[mid] < val) {
					sti = mid;
				} else {
					eni = mid - 1;
				}
			}
			return sti;
		}
	}
}
