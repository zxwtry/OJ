package leetcode;

import tools.TreeNode辅助.TreeNode;

/**
    Given two non-empty binary trees s and t, check whether tree t has exactly 
    the same structure and node values with a subtree of s. A subtree of s is 
    a tree consists of a node in s and all of this node's descendants. 
    The tree s could also be considered as a subtree of itself.
    
    Example 1:
    Given tree s:
    
         3
        / \
       4   5
      / \
     1   2
    Given tree t:
       4 
      / \
     1   2
    Return true, because t has the same structure and node values with a subtree of s.
    Example 2:
    Given tree s:
    
         3
        / \
       4   5
      / \
     1   2
        /
       0
    Given tree t:
       4
      / \
     1   2
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P572_SubtreeOfAnotherTree.java
 * @type        P572_SubtreeOfAnotherTree
 * @date        2017年5月7日 上午9:46:43
 * @details     Solution: 34ms 33.33%
 */
public class P572_SubtreeOfAnotherTree {
    static class Solution {
        private boolean allTrue(TreeNode s, TreeNode t) {
            if (s == t) return true;
            if (s == null || t == null) return false;
            if (s.val != t.val) return false;
            return allTrue(s.left, t.left) && allTrue(s.right, t.right);
        }
        public boolean isSubtree(TreeNode s, TreeNode t) {
            if (s == t) return true;
            if (s == null || t == null) return false;
            if (allTrue(s, t)) return true;
            if (isSubtree(s.left, t)) return true;
            if (isSubtree(s.right, t)) return true;
            return false;
        }
    }
}
