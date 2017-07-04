package nowcoder.leetcode;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        树_二叉搜索树的第k个结点.java
 * @date        2017年7月4日 下午8:58:11
 * @details     剑指Offer
 */
public class 树_二叉搜索树的第k个结点 {
    static public class Solution {
        int i;
        TreeNode ans;
        TreeNode KthNode(TreeNode pRoot, int k) {
            i = 1;
            ans = null;
            in(pRoot, k);
            return ans;
        }
        void in(TreeNode n, int k) {
            if (n == null)
                return;
            if (ans != null)
                return;
            if (n.left != null) {
                in(n.left, k);
            }
            if (ans != null)
                return;
            if (i == k) {
                ans = n;
                return;
            }
            i++;
            if (n.right != null) {
                in(n.right, k);
            }
        }
    }
}
