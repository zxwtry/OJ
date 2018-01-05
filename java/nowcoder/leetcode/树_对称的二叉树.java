package nowcoder.leetcode;

import java.util.LinkedList;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        树_对称的二叉树.java
 * @date        2017年7月4日 下午7:41:00
 * @details     剑指Offer
 */
public class 树_对称的二叉树 {
    static public class Solution {
        boolean ans = true;
        boolean isSymmetrical(TreeNode n)
        {
            if (n == null) return true;
            LinkedList<TreeNode> ll = new LinkedList<>();
            lo(ll, n);
            ro(ll, n);
            return ans;
        }
        void ro(LinkedList<TreeNode> ll, TreeNode n) {
            if (n == null) {
                return;
            }
            if (n.left == null && n.right == null) {
                ans &= ll.pollFirst() == null;
            }
            if (! ans) return;
            if (n.right != null) {
                ro(ll, n.right);
            }
            TreeNode po = ll.pollFirst();
            if (po == null) ans = false;
            else ans &= po.val == n.val;
            if (n.left != null) {
                ro(ll, n.left);
            }
        }
        void lo(LinkedList<TreeNode> ll, TreeNode n) {
            if (n == null) return;
            if (n.left == null && n.right == null) {
                ll.add(null);
            }
            if (n.left != null) {
                lo(ll, n.left);
            }
            ll.addLast(n);
            if (n.right != null) {
                lo(ll, n.right);
            }
        }
    }
}
