package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tools.TreeNode辅助.TreeNode;

/**

Given a non-empty binary tree, return the average value of the nodes 
on each level in the form of an array.

Example 1:
Input:
    3
   / \
  9  20
    /  \
   15   7
Output: [3, 14.5, 11]
Explanation:
The average value of nodes on level 0 is 3,  on level 1 is 14.5, and 
on level 2 is 11. Hence return [3, 14.5, 11].
Note:
The range of node's value is in the range of 32-bit signed integer.

 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P637_AverageOfLevelsInBinaryTree.java
 * @date        2017年7月9日 上午9:31:03
 * @details     Solution AC
 */
public class P637_AverageOfLevelsInBinaryTree {
    static public class Solution {
        public List<Double> averageOfLevels(TreeNode root) {
            if (root == null) return new ArrayList<Double>(0);
            LinkedList<TreeNode> q = new LinkedList<>();
            List<Double> a = new LinkedList<>();
            q.add(root);
            while (q.size() != 0) {
                long sum = 0;
                int size = q.size();
                for (int i = 0; i < size; i ++) {
                    TreeNode now = q.poll();
                    if (now.left != null) q.add(now.left);
                    if (now.right != null) q.add(now.right);
                    sum += now.val;
                }
                a.add(((double)(sum)/(double)(size)));
            }
            return a;
        }
    }
}
