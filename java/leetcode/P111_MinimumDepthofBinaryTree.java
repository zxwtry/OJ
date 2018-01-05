package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 	Given a binary tree, find its minimum depth.

	The minimum depth is the number of nodes along the shortest path from 
	the root node down to the nearest leaf node.
 */

import tools.TreeNode辅助.TreeNode;

public class P111_MinimumDepthofBinaryTree {
	public static void main(String[] args) {
		
	}
	/*
	 * 	AC
	 * 	1 ms
	 */
	static class Solution {
	    public int minDepth(TreeNode root) {
	    	if (root == null) {
	    		return 0;
	    	}
	    	Queue<TreeNode> q1 = new LinkedList<>();
	    	Queue<Integer> q2 = new LinkedList<>();
	    	q1.add(root);
	    	q2.add(1);
	    	int depth_now = 1;
	    	while (! q1.isEmpty()) {
	    		TreeNode root_now = q1.poll();
	    		depth_now = q2.poll();
	    		if (root_now.left == null && root_now.right == null) {
	    			return depth_now;
	    		}
	    		if (root_now.left != null) {
	    			q1.add(root_now.left);
	    			q2.add(depth_now + 1);
	    		}
	    		if (root_now.right != null) {
	    			q1.add(root_now.right);
	    			q2.add(depth_now + 1);
	    		}
	    	}
	        return depth_now;
	    }
	}
}
