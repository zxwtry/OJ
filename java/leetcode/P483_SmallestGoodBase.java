package leetcode;

/**
 * 	For an integer n, we call k>=2 a good base of n, if all digits of n base k are 1.
 *	
 *	Now given a string representing n, you should return the smallest good base of n 
 *	in string format. 
 *	
 *	Example 1:
 *	Input: "13"
 *	Output: "3"
 *	Explanation: 13 base 3 is 111.
 *	Example 2:
 *	Input: "4681"
 *	Output: "8"
 *	Explanation: 4681 base 8 is 11111.
 *	Example 3:
 *	Input: "1000000000000000000"
 *	Output: "999999999999999999"
 *	Explanation: 1000000000000000000 base 999999999999999999 is 11.
 *	Note:
 *	The range of n is [3, 10^18].
 *	The string representing n is always valid and will not have leading zeros.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P483_SmallestGoodBase.java
 * @type        P483_SmallestGoodBase
 * @date        2017年2月5日 下午2:18:07
 * @details     Solution1: AC 11ms 64.49%
 */
public class P483_SmallestGoodBase {
	static class Solution1 {
	    public String smallestGoodBase(String n) {
	        long num = 0;
	        for (char c : n.toCharArray()) num = num * 10 + c - '0';
	        long x = 1;
	        for (int p = 64; p >= 1; p--) {
	            if ((x << p) < num) {
	                long k = helper(num, p);
	                if (k != -1) return String.valueOf(k);
	            }
	        }
	        return String.valueOf(num - 1);
	    }
	    private long helper(long num, int p) {
	        long l = 1, r = (long)(Math.pow(num, 1.0/p) + 1);
	        while (l < r) {
	            long mid = l + (r - l) / 2;
	            long sum = 0, cur = 1;
	            for (int i = 0; i <= p; i++) {
	                sum += cur;
	                cur *= mid;
	            }
	            if (sum == num) return mid;
	            else if (sum > num) r = mid;
	            else l = mid + 1;
	        }
	        return -1;
	    }
	}
}
