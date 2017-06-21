package leetcode;

/**

Given a positive integer a, find the smallest positive integer b whose 
multiplication of each digit equals to a.

If there is no answer or the answer is not fit in 32-bit signed integer, then return 0.

Example 1
Input:

48 
Output:
68
Example 2
Input:

15
Output:
35

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P625_MinimumFactorization.java
 * @date        2017年6月18日 上午10:28:25
 * @details     AC
 */


public class P625_MinimumFactorization {
    static public class Solution {
        public int smallestFactorization(int a) {
            if (a <= 0) return 0;
            if (a == 1) return 1;
            int facLength = 64;
            int[] fac = new int[facLength];
            int facIndex = 0;
            int acpy = a;
            while (acpy != 1) {
                int preAcpy = acpy;
                for (int facValue = 9; facValue > 1; facValue --) {
                    if (acpy % facValue == 0) {
                        acpy /= facValue;
                        fac[facIndex ++] = facValue;
                        break;
                    }
                }
                if (preAcpy == acpy) break;
            }
            if (acpy != 1) return 0;
            long ans = 0;
            for (int facRealIndex = facIndex - 1; facRealIndex > -1; facRealIndex --) {
                ans = ans * 10 + fac[facRealIndex];
            }
            if (ans > Integer.MAX_VALUE || ans < 0) return 0;
            return (int)ans;
        }
    }
    
}
