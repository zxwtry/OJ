package leetcode;

import java.util.Arrays;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Array;

/**
 * 	Given an integer array nums, return the number of range sums that lie in 
 * 	[lower, upper] inclusive.
 *	Range sum S(i, j) is defined as the sum of the elememnts in nums between
 *	indices i and j (i ≤ j), inclusive.
 *	
 *	Note:
 *	A naive algorithm of O(n2) is trivial. You MUST do better than that.
 *	
 *	Example:
 *	Given nums = [-2, 5, -1], lower = -2, upper = 2,
 *	Return 3.
 *	The three ranges are : [0, 0], [2, 2], [0, 2] and their respective sums 
 *	are: -2, -1, 2.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P327_CountOfRangeSum.java
 * @type        P327_CountOfRangeSum
 * @date        2017年1月10日 下午9:59:31
 * @details     Solution1: TLE              时间O(N^2) 空间O(1)
 * @details     Solution2: AC 14ms 88.61%   时间O(N * logN) 空间O(N)
 */
public class P327_CountOfRangeSum {
    public static void main(String[] args) {
        int[] arr1 = tools.Random随机生成器.A_生成一个随机数据(100, -10, 30);
        int[] arr2 = Arrays.copyOf(arr1, arr1.length);
        Solution1 solution1 = new Solution1();
        Solution2 solution2 = new Solution2();
        int lower = -5;
        int upper = 30;
        System.out.println(solution1.countRangeSum(arr1, lower, upper));
        System.out.println(solution2.countRangeSum(arr2, lower, upper));
        
        
    }
	static class Solution1 {
	    public int countRangeSum(int[] nums, int lower, int upper) {
	        if (null == nums || nums.length == 0)
	        	return 0;
	        int len = nums.length;
	        int ans = 0;
	        long sum = 0;
	        for (int i = 0; i < len; i ++) {
	        	sum = 0;
	        	for (int j = i; j < len; j ++) {
	        		sum += nums[j];
	        		if (sum >= lower && sum <= upper)
	        			ans ++;
	        	}
	        }
	        return ans;
	    }
	}
	static class Solution2 {
	    public int countRangeSum(int[] nums, int lower, int upper) {
	        int n = nums.length;
	        long[] sums = new long[n + 1];
	        for (int i = 0; i < n; ++i)
	            sums[i + 1] = sums[i] + nums[i];
	        return countWhileMergeSort(sums, 0, n + 1, lower, upper);
	    }
	    private int countWhileMergeSort(long[] sums, int start, int end, int lower, int upper) {
	        if (end - start <= 1) return 0;
	        int mid = (start + end) / 2;
	        int count = countWhileMergeSort(sums, start, mid, lower, upper) 
	                  + countWhileMergeSort(sums, mid, end, lower, upper);
	        int j = mid, k = mid, t = mid;
	        long[] cache = new long[end - start];
	        for (int i = start, r = 0; i < mid; ++i, ++r) {
	            while (k < end && sums[k] - sums[i] < lower) k++;
	            while (j < end && sums[j] - sums[i] <= upper) j++;
	            while (t < end && sums[t] < sums[i]) cache[r++] = sums[t++];
	            cache[r] = sums[i];
	            count += j - k;
	        }
	        System.arraycopy(cache, 0, sums, start, t - start);
	        return count;
	    }
	}
	static class Solution3 {
	    public int countRangeSum(int[] nums, int lower, int upper) {
            if (null == nums || nums.length == 0)
                return 0;
            
	}
}