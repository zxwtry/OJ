package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        递归与循环_斐波那契数列.java
 * @date        2017年6月29日 下午10:03:07
 * @details     剑指Offer
 */
public class 递归与循环_斐波那契数列 {
    public class Solution {
        public int Fibonacci(int n) {
            if (n < 0) return 0;
            if (n < 2) return n;
            int[] arr = new int[n + 1];
            arr[0] = 1;
            arr[1] = 1;
            for (int i = 2; i < n; i ++) {
                arr[i] = arr[i - 1] + arr[i - 2];
            }
            return arr[n- 1];
        }
    }
}
