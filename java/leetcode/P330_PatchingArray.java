package leetcode;

/**
 * 	Given a sorted positive integer array nums and an integer n, 
 * 	add/patch elements to the array such that any number in range [1, n] 
 * 	inclusive can be formed by the sum of some elements in the array. 
 * 	Return the minimum number of patches required.

 *  Example 1:
 *  nums = [1, 3], n = 6
 *  Return 1.
 *  
 *  Combinations of nums are [1], [3], [1,3], which form possible sums of: 1, 3, 4.
 *  Now if we add/patch 2 to nums, the combinations are: [1], [2], [3], [1,3], 
 *  [2,3], [1,2,3].
 *  Possible sums are 1, 2, 3, 4, 5, 6, which now covers the range [1, 6].
 *  So we only need 1 patch.
 *  
 *  Example 2:
 *  nums = [1, 5, 10], n = 20
 *  Return 2.
 *  The two patches can be [2, 4].
 *  
 *  Example 3:
 *  nums = [1, 2, 2], n = 5
 *  Return 0.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P330_PatchingArray.java
 * @type        P330_PatchingArray
 * @date        2017年1月10日 下午10:04:05
 * @details     Solution2: AC 1ms 12.17%
 * @details     Solution3: AC 1ms 12.17%
 */
public class P330_PatchingArray {
	static class Solution2 {
	    public int minPatches(int[] nums, int n) {
    	    long miss = 1;
    	    int i = 0, added = 0;
    	    while (miss <= n) {
    	        if (i < nums.length && nums[i] <= miss) {
    	            miss += nums[i++];
    	        } else {
    	            // i >= nums.length 或者 nums[i] > miss
    	            miss += miss;
    	            added++;
    	        }
    	    }
    	    return added;
	    }
	}
	static class Solution3 {
	    public int minPatches(int[] nums, int n) {
	        if (n < 1)
                return 0;
	        if (nums == null)
	            nums = new int[0];
	        long maxGet = 0;
	        int index = 0, numOfPatches = 0;
	        while (maxGet < n) {
	            if (index < nums.length && nums[index] <= maxGet + 1) {
	                maxGet += nums[index ++];
	            } else {
	                maxGet += maxGet + 1;
	                numOfPatches ++;
	            }
	        }
	        return numOfPatches;
	    }
	}
}
