package leetcode;

import java.util.HashSet;

import tools.TreeNode辅助.TreeNode;

/**
 * Given a binary tree with n nodes, your task is to check if it's possible to partition the tree to two trees which have the equal sum of values after removing exactly one edge on the original tree.

Example 1:
Input:     
    5
   / \
  10 10
    /  \
   2   3

Output: True
Explanation: 
    5
   / 
  10
      
Sum: 15

   10
  /  \
 2    3

Sum: 15
Example 2:
Input:     
    1
   / \
  2  10
    /  \
   2   20

Output: False
Explanation: You can't split the tree into two trees with equal sum after removing exactly one edge on the tree.
Note:
The range of tree node value is in the range of [-100000, 100000].
1 <= n <= 10000
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P663_EqualTreePartition.java
 * @date        2017年8月20日 上午9:53:30
 * @details     
 */
public class P663_EqualTreePartition {
    
    public static void main(String[] args) {
        System.out.println((-1) % 2);
        int N = Integer.MIN_VALUE;
        TreeNode root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
                0, 
                10, 10,
                N, N, 2, 3,
        });
        System.out.println(new Solution().checkEqualTree(root));
    }
    
    static class Solution {
        public boolean checkEqualTree(TreeNode root) {
            HashSet<Integer> set = new HashSet<>();
            int total = search(root, set, root);
            if (total % 2 == 1 || total % 2 == -1) {
                return false;
            }
            return set.contains(total / 2);
        }
        private int search(TreeNode now, HashSet<Integer> set, TreeNode root) {
            if (now == null) {
                return 0;
            }
            int lv = search(now.left, set, root);
            int rv = search(now.right, set, root);
            int one = lv + rv + now.val;
            if (now != root) {
                set.add(one);
            }
            return one;
        }
    }
}
