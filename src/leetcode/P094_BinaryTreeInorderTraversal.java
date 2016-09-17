package leetcode;

import java.util.LinkedList;
import java.util.List;

import tools.TreeNode辅助.TreeNode;;

/*
 * 	Given a binary tree, return the inorder traversal of its nodes' values.

	For example:
	Given binary tree [1,null,2,3],
	   1
	    \
	     2
	    /
	   3
	return [1,3,2].	
 */


public class P094_BinaryTreeInorderTraversal {
	public static void main(String[] args) {
		
	}
	/*
	 * 	AC
	 * 	0 ms
	 * 	这个时候递归版本
	 */
	static class Solution1 {
		List<Integer> ans = new LinkedList<Integer>();
	    public List<Integer> inorderTraversal(TreeNode root) {
	    	in(root);
	        return ans;
	    }
	    void in(TreeNode root) {
	    	if (root == null) {
	    		return;
	    	}
	    	in(root.left);
	    	ans.add(root.val);
	    	in(root.right);
	    }
	}
}
