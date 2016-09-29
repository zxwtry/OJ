package leetcode;

/*
 * 	Given an array of integers, 
 *  every element appears three times except for one. Find that single one.

	Note:
	Your algorithm should have a linear runtime complexity. 
	Could you implement it without using extra memory?
 */

public class P137_SingleNumberII {
	public static void main(String[] args) {
		int unique = 400;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据_除了某个数(100, 0, 900, unique);
		int[] input = new int[arr.length * 3 + 1];
		for (int i = 0; i < input.length; i ++) {
			if (i == input.length - 1) {
				input[i] = unique;
			} else {
				input[i] = arr[i / 3];
			}
		}
		System.out.println(tools.Utils.LEETCODE_int_array_序列化_(input));
//		Solution s = new Solution();
//		System.out.println(s.singleNumber(new int[]{1111}));
	}
	/*
	 * 	想通了就简单，不想到这个方法就完全没法做
	 * 	总共是3 * k + 1个数
	 * 	对于每一个bit从0得到31都是 3 * k + 1个
	 * 	但是对于每一个bit就能够确定一点，
	 * 		如果 加起来是3 * k，那么结果在这一位就是0
	 * 		如果结果加起来是3 * k + 1，那么结果在这一位就是1
	 * 	50.50%
	 * 	4 ms
	 */
	static class Solution {
	    public int singleNumber(int[] nums) {
	    	if (nums == null || nums.length % 3 != 1) {
	    		return -1;
	    	}
	    	int ans = 0;
	    	int part_sum = 0;
	    	for (int i = 0; i < 32; i ++) {
	    		part_sum = 0;
	    		for (int val : nums) {
	    			part_sum += (val >>> i) & 0x1;
	    		}
	    		if (part_sum % 3 == 1) {
	    			ans += 1 << i;
	    		}
	    	}
	        return ans;
	    }
	}
}
