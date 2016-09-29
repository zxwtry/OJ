package leetcode;

/*
 * 	Given an array of integers, every element appears twice except for one.
 *  Find that single one.

	Note:
	Your algorithm should have a linear runtime complexity. 
		Could you implement it without using extra memory?
 */
public class P136_SingleNumber {
	public static void main(String[] args) {
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(100, 0, 900);
		System.out.println(tools.Utils.LEETCODE_int_array_序列化_(arr));
//		int[] new_arr = new int[] {
//			315, 140, 472, 637, 280, 741, 416, 105, 754, 206, 75, 729, 496, 61, 444, 865, 707, 813, 686, 243, 531, 771, 463, 890, 133, 623, 873, 381, 422, 218, 9, 395, 807, 693, 23, 159, 736, 816, 154, 17, 138, 466, 339, 493, 586, 896, 798, 136, 690, 170, 367, 625, 801, 408, 827, 569, 5, 24, 57, 733, 400, 275, 533, 166, 230, 342, 672, 503, 750, 517, 529, 616, 818, 590, 213, 726, 608, 691, 645, 37, 747, 134, 516, 872, 505, 129, 661, 896, 70, 81, 137, 148, 83, 157, 520, 312, 701, 417, 446, 830,
//			315, 140, 472, 637, 280, 741, 416, 105, 754, 206, 75, 729, 496, 61, 444, 865, 707, 813, 686, 243, 531, 771, 463, 890, 133, 623, 873, 381, 422, 218, 9, 395, 807, 693, 23, 159, 736, 816, 154, 17, 138, 466, 339, 493, 586, 896, 798, 136, 690, 170, 367, 625, 801, 408, 827, 569, 5, 24, 57, 733, 400, 275, 533, 166, 230, 342, 672, 503, 750, 517, 529, 616, 818, 590, 213, 726, 608, 691, 645, 37, 747, 134, 516, 872, 505, 129, 661, 896, 70, 81, 137, 148, 83, 157, 520, 312, 701, 417, 446, 830
//		};
//		Solution s = new Solution();
//		System.out.println(s.singleNumber(new_arr));
	}
	/*
	 * 	1 ms
	 * 	41.62%
	 */
	static class Solution {
	    public int singleNumber(int[] nums) {
	    	if (nums == null || nums.length == 0) {
	    		return 0;
	    	}
	    	int ans = 0;
	    	for (int val : nums) {
	    		ans ^= val;
	    	}
	        return ans;
	    }
	}
}
