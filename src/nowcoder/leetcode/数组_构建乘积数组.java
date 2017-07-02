package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        数组_构建乘积数组.java
 * @date        2017年7月2日 下午7:49:40
 * @details     剑指Offer
 */
public class 数组_构建乘积数组 {
    static public class Solution {
        public int[] multiply(int[] a) {
            int an = a == null ? 0 : a.length;
            int[] b = new int[an];
            for (int i = 0; i < an; i ++) b[i] = 1;
            for (int i = 0; i < an; i ++) {
                for (int j = 0; j < an; j ++) {
                    if (i != j) {
                        b[i] *= a[j];
                    }
                }
            }
            return b;
        }
    }
}
