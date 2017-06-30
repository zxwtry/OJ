package nowcoder.leetcode;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        面试思路_二叉树的镜像.java
 * @date        2017年6月30日 上午10:27:57
 * @details     剑指Offer
 */
public class 面试思路_二叉树的镜像 {
    static public class Solution {
        public void Mirror(TreeNode n) {
            if (n == null) return ;
            TreeNode l = n.left;
            TreeNode r = n.right;
            n.left = v(r);
            n.right = v(l);
        }
        TreeNode v(TreeNode n) {
            if (n == null) return null;
            TreeNode l = n.left;
            TreeNode r = n.right;
            n.left = v(r);
            n.right = v(l);
            return n;
        }
    }
}
