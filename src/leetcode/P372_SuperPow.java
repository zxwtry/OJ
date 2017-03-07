package leetcode;

/**
 *  Your task is to calculate ab mod 1337 where a is a positive integer and b is
 *  an extremely large positive integer given in the form of an array.
 *  
 *  Example1:
 *  
 *  a = 2
 *  b = [3]
 *  
 *  Result: 8
 *  Example2:
 *  
 *  a = 2
 *  b = [1,0]
 *  
 *  Result: 1024
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P372_SuperPow.java
 * @type        P372_SuperPow
 * @date        2017年3月6日 下午7:15:56
 * @details     Solution1: AC 14ms 18.60%
 * @details     Solution2: AC  8ms 58.93%
 */
public class P372_SuperPow {
    //ab%k = ((a%k)*(b%k)) % k
    static class Solution1 {
        final int base = 1337;
        public int superPow(int a, int[] b) {
            return superPow(a, b, 0, b.length - 1);
        }
        public int superPow(int a, int[] b, int i, int j) {
            if (i > j) return 1;
            return mod(superPow(a, b, i, j - 1), 10) * mod(a, b[j]) % base;
        }
        public int mod(int a, int b) {
            a %= base;
            int j = 1;
            for (int i = 0; i < b; i ++)
                j = (a * j) % base;
            return j;
        }
    }
}
