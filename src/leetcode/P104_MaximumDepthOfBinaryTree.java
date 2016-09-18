package leetcode;

/*
 * 	Given a binary tree, find its maximum depth.

	The maximum depth is the number of nodes along the longest 
	path from the root node down to the farthest leaf node.
 */

import tools.TreeNode辅助.TreeNode;

public class P104_MaximumDepthOfBinaryTree {
	public static void main(String[] args) {
		
	}
	/*
	 * 	AC
	 * 	0 ms
	 */
	static class Solution {
		int max_depth = 0;
	    public int maxDepth(TreeNode root) {
	    	find_max_depth(root, 0);
	        return max_depth;
	    }
		private void find_max_depth(TreeNode root, int depth) {
			if (root == null) {
				return;
			}
			max_depth = Math.max(depth + 1, max_depth);
			find_max_depth(root.left, depth + 1);
			find_max_depth(root.right, depth + 1);
		}
	    
	}
}
