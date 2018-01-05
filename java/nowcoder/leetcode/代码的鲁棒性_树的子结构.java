package nowcoder.leetcode;

import java.util.LinkedList;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        代码的鲁棒性_树的子结构.java
 * @date        2017年6月30日 上午10:24:19
 * @details     剑指Offer
 */
public class 代码的鲁棒性_树的子结构 {
    static public class Solution {
        public boolean HasSubtree(TreeNode a,TreeNode b) {
            if (a == null || b == null) return false;
            LinkedList<TreeNode> l = new LinkedList<>();
            l.addLast(a);
            while (l.size() != 0) {
                TreeNode n = l.pollFirst();
                if (isSame(n, b)) return true;
                if (n.left != null) l.addLast(n.left);
                if (n.right != null) l.addLast(n.right);
            }
            return false;
        }
        boolean isSame (TreeNode a, TreeNode b) {
            if (a == null || b == null) return a == null && b == null;
            if (a.val == b.val) {
                return isSame(b.left == null ? null : a.left, b.left) && 
                    isSame(b.right == null ? null : a.right, b.right);
            } return false;
        }
    }
}
