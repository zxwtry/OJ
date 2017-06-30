package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        举例让抽象具体化_栈的压入弹出序列.java
 * @date        2017年6月30日 下午3:31:38
 * @details     剑指Offer
 */
public class 举例让抽象具体化_栈的压入弹出序列 {
    public class Solution {
        public boolean IsPopOrder(int [] pushA,int [] popA) {
            int n = pushA == null ? 0 : pushA.length;
            if (n == 0) return true;
            int[] h = new int[n];
            int p1 = 0, p2 = 0, h1 = -1;
            for (; p1 < n; p1 ++) {
                int v1 = pushA[p1];
                if (h1 != -1 && h[h1] == v1) {
                    h1 --;
                } else if (popA[p2] == v1) {
                    p2 ++;
                } else {
                    h[ ++ h1] = v1;
                }
            }
            while (h1 != -1) {
                if (popA[p2 ++] != h[h1 --]) return false;
            }
            return true;
        }
    }
}
