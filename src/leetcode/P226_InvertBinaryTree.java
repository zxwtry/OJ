package leetcode;

import java.util.LinkedList;
import java.util.Queue;

import tools.TreeNode辅助.TreeNode;

/*
 * 	Invert a binary tree.
	
	     4
	   /   \
	  2     7
	 / \   / \
	1   3 6   9
	to
	     4
	   /   \
	  7     2
	 / \   / \
	9   6 3   1
	Trivia:
	This problem was inspired by this original tweet by Max Howell:
	Google: 90% of our engineers use the software you wrote (Homebrew),
	 but you can’t invert a binary tree on a whiteboard so fuck off.
 */

public class P226_InvertBinaryTree {
	public static void main(String[] args) {
		
	}
	/*
	 * 	1 ms
	 * 	0.74%
	 */
	static class Solution {
	    public TreeNode invertTree(TreeNode root) {
	    	if (root == null) {
	    		return null;
	    	}
	    	Queue<TreeNode> q = new LinkedList<>();
	    	q.add(root);
	    	while (! q.isEmpty()) {
	    		TreeNode rootNow = q.poll();
	    		TreeNode left = rootNow.left;
	    		TreeNode right = rootNow.right;
	    		if (left != null) {
	    			q.add(left);
	    		}
	    		if (right != null) {
	    			q.add(right);
	    		}
	    		rootNow.left = right;
	    		rootNow.right = left;
	    	}
	        return root;
	    }
	}
}
