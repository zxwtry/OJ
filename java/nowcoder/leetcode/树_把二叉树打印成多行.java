package nowcoder.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        树_把二叉树打印成多行.java
 * @date        2017年7月4日 下午7:55:57
 * @details     剑指Offer
 */
public class 树_把二叉树打印成多行 {
    static public class Solution {
        ArrayList<ArrayList<Integer> > Print(TreeNode n) {
            ArrayList<ArrayList<Integer> > ans = new ArrayList<>();
            if (n == null) return ans;
            LinkedList<TreeNode> q = new LinkedList<>();
            q.add(n);
            while (q.size() != 0) {
                int size = q.size();
                ArrayList<Integer> al = new ArrayList<>(size);
                for (int i = 0; i < size; i ++) {
                    TreeNode now = q.poll();
                    if (now.left != null) {
                        q.add(now.left);
                    }
                    if (now.right != null) {
                        q.add(now.right);
                    }
                    al.add(now.val);
                }
                ans.add(al);
            }
            return ans;
        }
    }
}
