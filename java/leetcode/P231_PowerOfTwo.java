package leetcode;

/*
 * 	Given an integer, write a function to determine 
 * 	if it is a power of two.

	Credits:
	Special thanks to @jianchao.li.fighter for 
	adding this problem and creating all test cases.
 */

public class P231_PowerOfTwo {
	public static void main(String[] args) {
	}
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     leetcode
	 * @file        P231_PowerOfTwo.java
	 * @type        Solution
	 * @date        2016年12月14日 下午10:32:39
	 * @details     AC 3 ms 5.75%
	 */
	static class Solution {
	    public boolean isPowerOfTwo(int n) {
	        int a = 1;
	        while (a < Integer.MAX_VALUE && a > 0) {
	        	if (a == n) {
	        		return true;
	        	} else {
	        		a = a * 2;
	        	}
	        }
	        return false;
	    }
	}
	
	/**
	 * @author      zxwtry
	 * @email       zxwtry@qq.com
	 * @project     OJ
	 * @package     leetcode
	 * @file        P231_PowerOfTwo.java
	 * @type        Solution2
	 * @date        2016年12月15日 上午9:08:10
	 * @details     AC 2ms 19.17% 
	 */
	static class Solution2 {
		public boolean isPowerOfTwo(int n) {
			if (n <= 0) return false;
			boolean isFindOne = false;
			while (! isFindOne) {
				isFindOne = (n & 0x1) == 1;
				n = n >> 1;
			}
			return n == 0;
		}
	}
	
}
