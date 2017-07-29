package leetcode;


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P009_PalindromeNumber.java
 * @date        2017年7月29日 下午10:06:22
 * @details     Solution: AC 198ms 82.56%
 */
public class P009_PalindromeNumber {
	static class Solution {
	    public boolean isPalindrome(int x) {
	        if (x < 0) return false;
	        int p = x, q = 0;
	        while (p != 0) {
	            q = q * 10 + (p % 10);
	            p = p / 10;
	        }
	        return q == x;
	    }
	}
}
