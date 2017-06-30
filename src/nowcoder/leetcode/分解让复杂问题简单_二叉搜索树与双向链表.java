package nowcoder.leetcode;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        分解让复杂问题简单_二叉搜索树与双向链表.java
 * @date        2017年6月30日 下午10:27:47
 * @details     剑指Offer
 */
public class 分解让复杂问题简单_二叉搜索树与双向链表 {
    public class Solution {
        TreeNode p1;
        TreeNode a;
        public TreeNode Convert(TreeNode n) {
            p1 = null;
            a = null;
            if (n == null) return null;
            in(n);
            return a;
        }
        void in(TreeNode n) {
            TreeNode l = n.left;
            TreeNode r = n.right;
            if (l != null) in(l);
            n.left = p1;
            if (a == null) a = n;
            if (p1 != null) p1.right = n;
            p1 = n;
            if (r != null) in(r);
        }
    }
}
