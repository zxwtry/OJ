package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        时间空间效率的平衡_丑数.java
 * @date        2017年7月1日 下午12:25:38
 * @details     剑指Offer
 */
public class 时间空间效率的平衡_丑数 {
    static public class Solution {
        public int GetUglyNumber_Solution(int n) {
            if (n < 7) return n;  
            int[] arr = new int[n + 1];
            for (int i = 0; i < 7; i ++) {
                arr[i] = i;
            }
            int i2 = 1;
            int i3 = 1;
            int i5 = 1;
            for (int i = 7; i <= n; i ++) {
                while (arr[i2] * 2 <= arr[i - 1]) i2 ++;
                while (arr[i3] * 3 <= arr[i - 1]) i3 ++;
                while (arr[i5] * 5 <= arr[i - 1]) i5 ++;
                int min = Math.min(arr[i2] * 2, arr[i3] * 3);
                min = Math.min(arr[i5] * 5, min);
                arr[i] = min;
            }
            return arr[n];
        }
    }
}
