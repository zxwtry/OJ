package leetcode;

import tools.TreeNode辅助.TreeNode;
public class P100_SameTree {
	public static void main(String[] args) {
		
	}
	/*
	 * 	0 ms
	 * 	11.51%
	 */
	static class Solution {
	    public boolean isSameTree(TreeNode p, TreeNode q) {
	        if (p == null && q == null)
	        	return true;
	        if (p == null || q == null || p.val != q.val)
	        	return false;
	        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
	    }
	}
}
