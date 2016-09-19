package leetcode;

import tools.TreeNode辅助.TreeNode;

/*
 * 	Given a binary tree, determine if it is height-balanced.

	For this problem, a height-balanced binary tree is defined as a binary tree
	 in which the depth of the two subtrees of every node never differ by more than 1.
 */

public class P110_BalancedBinaryTree {
	public static void main(String[] args) {
		
	}
	/*
	 * 	AC
	 * 	1 ms
	 */
	static class Solution {
		boolean isFalse = false;
	    public boolean isBalanced(TreeNode root) {
	    	if (root == null) {
	    		return true;
	    	}
	    	zxwtry_count(root);
	        return ! isFalse;
	    }
	    int zxwtry_count(TreeNode root) {
	    	if (isFalse) {
	    		return 0;
	    	}
	    	if (root == null) {
	    		return 0;
	    	}
	    	int left = zxwtry_count(root.left);
	    	int right = zxwtry_count(root.right);
	    	if (Math.abs(left - right) > 1) {
	    		isFalse = true;
	    	}
	    	return Math.max(left, right) + 1;
	    }
	}
}