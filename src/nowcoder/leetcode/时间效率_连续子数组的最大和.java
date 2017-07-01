package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        时间效率_连续子数组的最大和.java
 * @date        2017年7月1日 上午10:28:22
 * @details     剑指Offer
 */
public class 时间效率_连续子数组的最大和 {
    static public class Solution {
        public int FindGreatestSumOfSubArray(int[] a) {
            int al = a == null ? 0 : a.length;
            if (al == 0) return 0;
            int[] s = new int[al];
            s[0] = a[0];
            int max = a[0];
            for (int i = 1; i < al; i ++) {
                if (s[i - 1] > 0) {
                    s[i] = s[i - 1] + a[i];
                } else {
                    s[i] = a[i];
                }
                max = Math.max(max, s[i]);
            }
            return max;
        }
    }
}
