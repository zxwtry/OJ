package nowcoder.com;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.com
 * @file        小米_懂二进制.java
 * @date        2017年7月5日 下午9:50:18
 * @details     
 */
public class 小米_懂二进制 {
    static public class Solution {
        /**
         * 获得两个整形二进制表达位数不同的数量
         * 
         * @param m 整数m
         * @param n 整数n
         * @return 整型
         */
        public int countBitDiff(int m, int n) {
            return Integer.bitCount(m ^ n);
        }
    }
}
