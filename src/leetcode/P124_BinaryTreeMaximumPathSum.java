package leetcode;

import tools.TreeNode辅助.TreeNode;

/*
    Given a binary tree, find the maximum path sum.

	For this problem, a path is defined as any sequence of nodes
	 from some starting node to any node in the tree 
	 along the parent-child connections. 
	 The path does not need to go through the root.
	
	For example:
	Given the below binary tree,
	
	       1
	      / \
	     2   3
	Return 6.
 */


/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P124_BinaryTreeMaximumPathSum.java
 * @type        P124_BinaryTreeMaximumPathSum
 * @date        2017年5月7日 下午10:02:15
 * @details     Solution: AC 3ms 19.84%
 */
public class P124_BinaryTreeMaximumPathSum {
	static class Solution {
	    public int maxPathSum(TreeNode root) {
	        if (root == null) return 0;
	        int[] ans = {Integer.MIN_VALUE};
	    	search(root, ans);
	        return ans[0];
	    }
		private int search(TreeNode root, int[] ans) {
		    if (root == null) return 0;
		    int lv = Math.max(0, search(root.left, ans));
		    int rv = Math.max(0, search(root.right, ans));
		    ans[0] = Math.max(ans[0], lv+rv+root.val);
		    return Math.max(lv, rv) + root.val;
		}
	}
}
