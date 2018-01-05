package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        递归与循环_矩形覆盖.java
 * @date        2017年6月29日 下午10:15:33
 * @details     剑指Offer
 */
public class 递归与循环_矩形覆盖 {
    public class Solution {
        public int RectCover(int t) {
            if (t == 0) return 0;
            if (t == 1) return 1;
            if (t == 2) return 2;
            if (t == 3) return 1 + 2;
            int[] a = new int[t + 1];
            a[0] = 0;
            a[1] = 1;
            a[2] = 2;
            a[3] = 3;
            for (int i = 4; i <= t; i ++) {
                a[i] = a[i - 1] + a[i - 2];
            }
            return a[t];
        }
    }
}
