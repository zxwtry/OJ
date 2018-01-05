package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        知识迁移能力_孩子们的游戏约瑟夫环.java
 * @date        2017年7月2日 上午9:20:55
 * @details     剑指Offer
 */
public class 知识迁移能力_孩子们的游戏约瑟夫环 {
    static public class Solution {
        public int LastRemaining_Solution(int n, int m) {
            if (n == 0) return -1;
            return realGet(n, m) - 1;
        }
        int get(int n, int m) {
            if (n == 1) return 1;
            return (get(n - 1, m) + m - 1) % n + 1;
        }
        int realGet(int n, int m) {
            if (n <= 0) return 0;
            int[] arr = new int[n + 1];
            arr[0] = 0;
            arr[1] = 1;
            for (int i = 2; i <= n; i ++) {
                arr[i] = (arr[i - 1] + m - 1) % i + 1;
            }
            return arr[n];
        }
    }
}
