package leetcode;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;

import java.util.Queue;

import tools.TreeNode辅助;
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
    public static void main(String[] args) {
        int N = Integer.MIN_VALUE;
        TreeNode s = TreeNode辅助.A_生成满二叉树(new int[] {
                1, 
                2, 3,
                4, 5, 6, 7,
                 8,  9, 10, 11, 12, 13, 14, 15
        });
        TreeNode t = TreeNode辅助.A_生成满二叉树(new int[] {
                4, 
                8, 9
        });
        System.out.println(new Solution2().isSubtree(s, t));
    }
    static public class Solution {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            if (t == null) return true;
            if (s == null) return false;
            Queue<TreeNode> q = new LinkedList<TreeNode>();
            HashMap<TreeNode, Integer> m = new HashMap<TreeNode, Integer>();
            q.add(s);
            int sc = 0;
            while (! q.isEmpty()) {
                int size = q.size();
                while (size -- > 0) {
                    TreeNode n = q.poll();
                    if (n.val == t.val) m.put(n, sc);
                    if (n.val == t.val) m.put(n, sc);
                    if (n.left != null) q.add(n.left);
                    if (n.right != null) q.add(n.right);
                }
                sc ++;
            }
            q.clear();
            q.add(t);
            int tc = 0;
            while (! q.isEmpty()) {
                int size = q.size();
                while (size -- > 0) {
                    TreeNode n = q.poll();
                    if (n.left != null) q.add(n.left);
                    if (n.right != null) q.add(n.right);
                }
                tc ++;
            }
            for (Entry<TreeNode, Integer> e : m.entrySet()) {
                if (sc - e.getValue() >= tc) {
                    if (allTrue(e.getKey(), t)) return true;
                }
            }
            return false;
        }
        private boolean allTrue(TreeNode s, TreeNode t) {
            if (s == t) return true;
            if (s == null || t == null) return false;
            if (s.val != t.val) return false;
            return allTrue(s.left, t.left) && allTrue(s.right, t.right);
        }
    }
    static class Solution2 {
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
