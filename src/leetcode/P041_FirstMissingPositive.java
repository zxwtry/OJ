package leetcode;

/*
 * 	Given an unsorted integer array, find the first missing positive integer.
 * 
	For example,
	Given [1,2,0] return 3,
	and [3,4,-1,1] return 2.
	
	Your algorithm should run in O(n) time and uses constant space.
 */

public class P041_FirstMissingPositive {
    
    static class Solution {
        /**
         *  对left的操作:
         *      定下，nums[left]的范围是：[left+1, right+1]
         *      低于这个范围，高于这个范围都是无效的。
         *      如果nums[left]有效，那么个将nums[val]填到该填的地方
         *  对right的操作：
         *      不能对right直接操作，因为只能从左边生成连续。
         *      right维持的是：还有可能填充的最大下标
         */
        public int firstMissingPositive(int[] nums) {
            int left = 0, right = nums == null ? -1 : nums.length - 1;
            while (left <= right) {
                //当前数据范围：[left+1, right+1]
                int val = nums[left];
                if (val == left + 1) {
                    left ++;
                } else if (val <= left) {
                    //当前val无效
                    nums[left] = nums[right];
                    right --;
                } else if (val > right + 1) {
                    //当前val无效
                    nums[left] = nums[right];
                    right --;
                } else if (nums[nums[left] - 1] == val) {
                    //当前val无效
                    nums[left] = nums[right];
                    right --;
                } else {
                    //将val放到该放的地方
                    int tmp = nums[nums[left] - 1];
                    nums[nums[left] - 1] = nums[left];
                    nums[left] = tmp;
                }
            }
            return left + 1;
        }
    }
}
