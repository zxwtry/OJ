package leetcode;

import java.util.ArrayList;

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
 * @details     Solution:  34ms 33.33%
 * @details     Solution2: 32ms 33.33%  kmp String
 * @details     Solution3: 47ms 11.11%  kmp Integer  (kmp Long 66ms)
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
    static class Solution2 {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            StringBuilder s1 = new StringBuilder(), t1 = new StringBuilder();
            posOrder(s, s1);
            posOrder(t, t1);
            return kmp(s1, t1);
        }
        private boolean kmp(StringBuilder s1, StringBuilder t1) {
            int sn = s1.length(), tn = t1.length();
            int si = 0, ti = 0, next[] = next(t1, tn);
            while (si < sn) {
                if (s1.charAt(si) == t1.charAt(ti)) {
                    si ++;
                    ti ++;
                    if (ti == tn) return true;
                } else if (next[ti] == -1) {
                    si ++;
                } else ti = next[ti];
            }
            return false;
        }
        private int[] next(StringBuilder t1, int tn) {
            if (tn < 2) return new int[]{-1};
            int bi = 0, fi = 2, next[] = new int[tn];
            next[0] = -1;
            next[1] = 0;
            while (fi < tn) {
                if (t1.charAt(fi-1) == t1.charAt(bi)) {
                    next[fi ++] = ++ bi;
                } else if (bi <= 0) {
                    next[fi ++] = 0;
                } else bi = next[bi];
            }
            return next;
        }
        private void posOrder(TreeNode s, StringBuilder s1) {
            if (s == null) {
                s1.append("null");
            } else {
                posOrder(s.left, s1);
                posOrder(s.right, s1);
                s1.append(s.val);
                s1.append('#');
            }
        }
    }
    static class Solution3 {
        public boolean isSubtree(TreeNode s, TreeNode t) {
            ArrayList<Integer> sl = new ArrayList<Integer>(), tl = new ArrayList<Integer>();
            posOrder(s, sl);
            posOrder(t, tl);
            return kmp(sl, tl);
        }
        private boolean kmp(ArrayList<Integer> s, ArrayList<Integer> p) {
            int sn = s.size(), pn = p.size();
            int si = 0, pi = 0, next[] = next(p, pn);
            while (si < sn) {
                if (s.get(si).intValue() == p.get(pi).intValue()) {
                    si ++;
                    pi ++;
                    if (pi == pn) return true;
                } else if (next[pi] == -1) {
                    si ++;
                } else pi = next[pi];
            }
            return false;
        }
        private int[] next(ArrayList<Integer> p, int pn) {
            if (pn < 2) return new int[] {-1};
            int bi = 0, fi = 2, next[] = new int[pn];
            next[0] = -1;
            next[1] = 0;
            while (fi < pn) {
                if (p.get(fi-1).intValue() == p.get(bi).intValue()) {
                    next[fi ++] = ++ bi;
                } else if (bi <= 0) {
                    next[fi ++] = 0;
                } else bi = next[bi];
            }
            return next;
        }
        private void posOrder(TreeNode n, ArrayList<Integer> l) {
            if (n == null) {
                l.add(Integer.MIN_VALUE);
            } else {
                posOrder(n.left, l);
                posOrder(n.right, l);
                l.add(n.val);
            }
        }
    }
}
