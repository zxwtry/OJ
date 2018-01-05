package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        递归与循环_跳台阶.java
 * @date        2017年6月29日 下午10:06:02
 * @details     剑指Offer
 */
public class 递归与循环_跳台阶 {
    public class Solution {
        public int JumpFloor(int t) {
            if (t < 3) return t;
            int[] a = new int[t + 1];
            
            a[0] = 0;
            a[1] = 1;
            a[2] = 2;
            for (int i = 3; i <= t; i ++) {
                a[i] = a[i - 1] + a[i - 2];
            }
            return a[t];
        }
    }
}
