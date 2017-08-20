package leetcode;

import java.util.LinkedList;

import tools.TreeNode辅助.TreeNode;

/**
 *  Given a binary tree, write a function to get the maximum width of the given tree. The width of a tree is the maximum width among all levels. The binary tree has the same structure as a full binary tree, but some nodes are null.

The width of one level is defined as the length between the end-nodes (the leftmost and right most non-null nodes in the level, where the null nodes between the end-nodes are also counted into the length calculation.

Example 1:
Input: 

           1
         /   \
        3     2
       / \     \  
      5   3     9 

Output: 4
Explanation: The maximum width existing in the third level with the length 4 (5,3,null,9).
Example 2:
Input: 

          1
         /  
        3    
       / \       
      5   3     

Output: 2
Explanation: The maximum width existing in the third level with the length 2 (5,3).
Example 3:
Input: 

          1
         / \
        3   2 
       /        
      5      

Output: 2
Explanation: The maximum width existing in the second level with the length 2 (3,2).
Example 4:
Input: 

          1
         / \
        3   2
       /     \  
      5       9 
     /         \
    6           7
Output: 8
Explanation:The maximum width existing in the fourth level with the length 8 (6,null,null,null,null,null,null,7).


Note: Answer will in the range of 32-bit signed integer.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P662_MaximumWidthOfBinaryTree.java
 * @date        2017年8月20日 上午9:43:22
 * @details     
 */
public class P662_MaximumWidthOfBinaryTree {
    public static void main(String[] args) {
        int N = Integer.MIN_VALUE;
        TreeNode root = tools.TreeNode辅助.A_生成满二叉树(
                new int[] {
                        1,
                        2, 3,
                        4,N, N, 5,
                        4, N, N, N, N, N, N, N
                });
        System.out.println(new Solution().widthOfBinaryTree(root));
    }
    static class Solution {
        public int widthOfBinaryTree(TreeNode root) {
            if (root == null) {
                return 0;
            }
            LinkedList<TreeNode> ll = new LinkedList<>();
            ll.add(root);
            int ans = 0;
            while (true) {
                while (ll.size() != 0 && ll.peekFirst() == null) {
                    ll.pollFirst();
                }
                while (ll.size() != 0 && ll.peekLast() == null) {
                    ll.pollLast();
                }
                int size = ll.size();
                if (size == 0) {
                    break;
                }
                ans = Math.max(ans, size);
                for (int i = 0; i < size; i ++) {
                    TreeNode now = ll.poll();
                    if (now != null) {
                        ll.add(now.left);
                        ll.add(now.right);
                    } else {
                        ll.add(null);
                        ll.add(null);
                    }
                }
            }
            return ans;
        }
        
    }
}
