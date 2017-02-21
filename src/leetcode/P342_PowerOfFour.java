package leetcode;

import com.sun.xml.internal.bind.v2.runtime.unmarshaller.XsiNilLoader.Single;

/**
 * 	Given an integer (signed 32 bits), write a function to check whether it is a power of 4.
 * 	
 * 	Example:
 * 	Given num = 16, return true. Given num = 5, return false.
 * 	
 * 	Follow up: Could you solve it without loops/recursion?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P342_PowerOfFour.java
 * @type        P342_PowerOfFour
 * @date        2017年2月2日 下午9:16:18
 * @details     
 */
public class P342_PowerOfFour {
	static class Solution1 {
	    public boolean isPowerOfFour(int num) {
	        if (num < 0) return false;
	        while(num != 0 && num != 1) {
	            if ((num & 3) != 0)
	                return false;
	            num = num >> 2;
	        }
	        return num == 1;
	    }
	}
	static class Solution2 {
	    public boolean isPowerOfFour(int num) {
            if (num < 0) return false;
            int sign = num & (num - 1);
            if (sign != 0) return false;
            if 
	    }
	}
}
