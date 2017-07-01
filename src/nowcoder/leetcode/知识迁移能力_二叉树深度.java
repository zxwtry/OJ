package nowcoder.leetcode;

import java.util.LinkedList;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        知识迁移能力_二叉树深度.java
 * @date        2017年7月1日 下午9:42:19
 * @details     剑指Offer
 */
public class 知识迁移能力_二叉树深度 {
    static public class Solution {
        public int TreeDepth(TreeNode root) {
            int len = 0;
            if (root == null) return len;
            LinkedList<TreeNode> ll = new LinkedList<>();
            ll.add(root);
            while (ll.size() != 0) {
                int size = ll.size();
                for (int i = 0; i < size; i ++) {
                    TreeNode now = ll.poll();
                    if (now.left != null) ll.add(now.left);
                    if (now.right != null) ll.add(now.right);
                }
                len ++;
            }
            return len;
        }
    }
}
