package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        递归与循环_变态跳台阶.java
 * @date        2017年6月29日 下午10:09:07
 * @details     剑指Offer
 */
public class 递归与循环_变态跳台阶 {
    public class Solution {
        public int JumpFloorII(int t) {
            if (t == 0) return 0;
            if (t == 1) return 1;
            if (t == 2) return 2;
            if (t == 3) return 4;
            int[] a = new int[t + 1];
            a[0] = 0;
            a[1] = 1;
            a[2] = 2;
            a[3] = 4;
            for (int i = 4; i <= t; i ++) {
                int sum = 1;
                for (int j = 1; j < i; j ++) {
                    sum += a[j];
                }
                a[i] = sum;
            }
            return a[t];
        }
    }
}
