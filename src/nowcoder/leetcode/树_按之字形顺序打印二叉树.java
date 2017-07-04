package nowcoder.leetcode;

import java.util.ArrayList;
import java.util.LinkedList;

import tools.TreeNode辅助.TreeNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     nowcoder.leetcode
 * @file        树_按之字形顺序打印二叉树.java
 * @date        2017年7月4日 下午7:51:42
 * @details     剑指Offer
 */
public class 树_按之字形顺序打印二叉树 {
    static public class Solution {
        public ArrayList<ArrayList<Integer> > Print(TreeNode n) {
            LinkedList<TreeNode> q = new LinkedList<TreeNode>();
            ArrayList<ArrayList<Integer>> ans = new ArrayList<ArrayList<Integer>>();
            if (n == null) return ans;
            q.addLast(n);
            boolean fromLeftToRight = true;
            while (q.size() != 0) {
                int size = q.size();
                ArrayList<Integer> one = new ArrayList<Integer>(size);
                TreeNode[] arr = new TreeNode[size];
                for (int i = 0; i < size; i ++) {
                    arr[i] = q.poll();
                    if (arr[i].left != null) {
                        q.add(arr[i].left);
                    }
                    if (arr[i].right != null) {
                        q.add(arr[i].right);
                    }
                }
                if (fromLeftToRight) {
                    for (int i = 0; i < size; i ++) {
                        TreeNode now = arr[i];
                        one.add(now.val);
                    }
                } else {
                    for (int i = 0; i < size; i ++) {
                        TreeNode now = arr[size - 1 - i];
                        one.add(now.val);
                    }
                }
                ans.add(one);
                fromLeftToRight = ! fromLeftToRight;
            }
            return ans;
        }
    }
}
