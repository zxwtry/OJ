package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        位运算_二进制中1的个数.java
 * @date        2017年6月29日 下午10:19:26
 * @details     剑指Offer
 */
public class 位运算_二进制中1的个数 {
    public class Solution {
        public int NumberOf1(int n) {
            if (n < 0) return 1 + NumberOf1(n & 0x7fffffff);
            int cnt = 0;
            while (n != 0) {
                cnt ++;
                n = n & (n - 1);
            }
            return cnt;
        }
    }
}
