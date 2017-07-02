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
            return get(n, m) - 1;
        }
        int get(int l, int m) {
            if (l == 1) return 1;
            return (get(l - 1, m) + m - 1) % l + 1;
        }
    }
}
