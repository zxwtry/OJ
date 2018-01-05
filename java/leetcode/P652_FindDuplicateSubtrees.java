package leetcode;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import tools.TreeNode辅助;
import tools.TreeNode辅助.TreeNode;

/**

Given a binary tree, return all duplicate subtrees. 
For each kind of duplicate subtrees, you only need to return the root node of any one of them.

Two trees are duplicate if they have the same structure with same node values.

Example 1: 
        1
       / \
      2   3
     /   / \
    4   2   4
       /
      4
The following are two duplicate subtrees:
      2
     /
    4
and
    4
Therefore, you need to return above trees' root in the form of a list.

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P652_FindDuplicateSubtrees.java
 * @date        2017年7月30日 上午10:16:19
 * @details     
 */
public class P652_FindDuplicateSubtrees {
    public static void main(String[] args) {
        int N = Integer.MIN_VALUE;
        TreeNode root = TreeNode辅助.A_生成满二叉树(new int[] {
                1,
                2, 3,
                4, N, 2, 4,
                N, N, N, N, 4, N, N, N,
        });
        List<TreeNode> ans = new Solution().findDuplicateSubtrees(root);
        for (TreeNode a : ans) {
            System.out.println(a);
        }
    }
    static public class Solution {
        LinkedList<TreeNode> ans = new LinkedList<>();
        LinkedList<TreeNode> lst = new LinkedList<>();
        HashSet<TreeNode> vit = new HashSet<>();
        public List<TreeNode> findDuplicateSubtrees(TreeNode root) {
            ans.clear();
            lst.clear();
            vit.clear();
            addLst(root);
            in(root);
            return ans;
        }
        private void addLst(TreeNode root) {
            if (root == null) return;
            addLst(root.left);
            lst.addLast(root);
            addLst(root.right);
        }
        private void in(TreeNode root) {
            if (root == null) return;
            in(root.left);
            lst.pollFirst();
            HashSet<TreeNode> nowSet = new HashSet<>();
            if (! vit.contains(root)) {
                for (TreeNode now : lst) {
                    if ((! vit.contains(now)) && same(root, now)) {
                        nowSet.add(now);
                    }
                }
            }
            if (nowSet.size() != 0) {
                ans.add(root);
            }
            vit.add(root);
            vit.addAll(nowSet);
            in(root.right);
        }
        boolean same(TreeNode a, TreeNode b) {
            if (a == null || b == null) return a == null && b == null;
            if (a.val != b.val) return false;
            return same(a.left, b.left) && same(a.right, b.right);
        }
    }
}
