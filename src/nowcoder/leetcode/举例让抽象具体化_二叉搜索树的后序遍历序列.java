package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        举例让抽象具体化_二叉搜索树的后序遍历序列.java
 * @date        2017年6月30日 下午3:49:23
 * @details     剑指Offer
 */
public class 举例让抽象具体化_二叉搜索树的后序遍历序列 {
    static public class Solution {
        public boolean VerifySquenceOfBST(int [] s) {
            int sl = s == null ? 0 : s.length;
            if (sl == 0) return false;
            return v(s, 0, sl - 1);
        }
        boolean v(int[] s, int i, int j) {
            if (j - i < 3) return true;
            int mv = s[j];
            int k = i;
            for (; k < j; k ++) {
                if (s[k] > mv) break;
            }
            for (; k < j; k ++) {
                if (s[k] < mv) return false;
            }
            return v(s, i, k - 1) && v(s, k, j - 1);
        }
    }
}
