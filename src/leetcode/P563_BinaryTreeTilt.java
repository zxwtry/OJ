package leetcode;

import tools.TreeNode辅助.TreeNode;

/**
 *  Given a binary tree, return the tilt of the whole tree.

    The tilt of a tree node is defined as the absolute difference between 
    the sum of all left subtree node values and the sum of all right subtree
     node values. Null node has tilt 0.
    
    The tilt of the whole tree is defined as the sum of all nodes' tilt.
    
    Example:
    Input: 
             1
           /   \
          2     3
    Output: 1
    Explanation: 
    Tilt of node 2 : 0
    Tilt of node 3 : 0
    Tilt of node 1 : |2-3| = 1
    Tilt of binary tree : 0 + 0 + 1 = 1
    Note:
    
    The sum of node values in any subtree won't exceed the range of 32-bit integer.
    All the tilt values won't exceed the range of 32-bit integer.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P563_BinaryTreeTilt.java
 * @type        P563_BinaryTreeTilt
 * @date        2017年4月23日 上午9:50:15
 * @details     Solution: AC
 */
public class P563_BinaryTreeTilt {
    public static void main(String[] args) {
        TreeNode root = tools.TreeNode辅助.A_生成满二叉树(
                new int[] {
                        8,
                        3, 9,
                        1, 2, 7, 10
                }
                );
        System.out.println(new Solution().findTilt(root));
    }
    static class Solution {
        int sum = 0;
        public int findTilt(TreeNode root) {
            sum = 0;
            f(root);
            return sum;
        }
        public int f(TreeNode n) {
            if (n == null) return 0;
            int l = f(n.left);
            int r = f(n.right);
            sum += Math.abs(l - r);
            return n.val + l + r;
        }
    }
}
