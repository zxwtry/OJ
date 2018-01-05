package nowcoder.leetcode;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        知识迁移能力_平衡二叉树.java
 * @date        2017年7月1日 下午9:57:49
 * @details     剑指Offer
 */
public class 知识迁移能力_平衡二叉树 {
    static public class Solution {
        boolean isb = true;
        public boolean IsBalanced_Solution(TreeNode root) {
            d(root);
            return isb;
        }
        int d(TreeNode n) {
            if (n == null || ! isb) return 0;
            int l = d(n.left);
            int r = d(n.right);
            if (Math.abs(l - r) < 2) {
                return Math.max(l , r) + 1;
            } else {
                isb = false;
                return 0;
            }
        }
    }
}
