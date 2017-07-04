package nowcoder.leetcode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        树_二叉树的下一个节点.java
 * @date        2017年7月4日 下午7:29:56
 * @details     剑指Offer
 */
public class 树_二叉树的下一个节点 {
    static public class TreeLinkNode {
        int val;
        TreeLinkNode left = null;
        TreeLinkNode right = null;
        TreeLinkNode next = null;

        TreeLinkNode(int val) {
            this.val = val;
        }
    }

    static public class Solution {
        public TreeLinkNode GetNext(TreeLinkNode n) {
            if (n == null)
                return null;
            if (n.right != null) {
                TreeLinkNode t = n.right;
                while (t.left != null) {
                    t = t.left;
                }
                return t;
            } else {
                TreeLinkNode p = n.next;
                while (p != null && p.right == n) {
                    n = p;
                    p = p.next;
                }
                return p;
            }
        }
    }
}
