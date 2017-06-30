package nowcoder.leetcode;

import java.util.ArrayList;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        举例让抽象具体化_二叉树中和为某一值的路径.java
 * @date        2017年6月30日 下午4:05:03
 * @details     剑指Offer
 */
public class 举例让抽象具体化_二叉树中和为某一值的路径 {
    public class Solution {
        public ArrayList<ArrayList<Integer>> FindPath(TreeNode n,int t) {
            ArrayList<ArrayList<Integer>> ans = new ArrayList<>();
            if (n == null) return ans;
            ArrayList<Integer> trace = new ArrayList<>();
            s(ans, trace, t, n);
            return ans;
        }
        void s(ArrayList<ArrayList<Integer>> ans, ArrayList<Integer> trace, int t, TreeNode n) {
            if (n.left != null) {
                trace.add(n.val);
                s(ans, trace, t - n.val, n.left);
                trace.remove(trace.size() - 1);
            }
            if (n.right != null) {
                trace.add(n.val);
                s(ans, trace, t - n.val, n.right);
                trace.remove(trace.size() - 1);
            }
            if (n.left == null && n.right == null) {
                if (t == n.val) {
                    trace.add(n.val);
                    ans.add(new ArrayList<>(trace));
                    trace.remove(trace.size() - 1);
                }
            }
        }
    }
}
