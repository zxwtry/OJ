package leetcode;

import java.util.LinkedList;
import java.util.Queue;

import tools.TreeNode辅助.TreeNode;

/*
 * 	Given a binary tree and a sum, determine if the tree has a root-to-leaf
 *  path such that adding up all the values along the path equals the given sum.

	For example:
	Given the below binary tree and sum = 22,
	              5
	             / \
	            4   8
	           /   / \
	          11  13  4
	         /  \      \
	        7    2      1
	return true, as there exist a root-to-leaf path 5->4->11->2 which sum is 22.
 */

public class P112_PathSum {
	public static void main(String[] args) {
		
	}
	/*
	 * 	AC
	 * 	4 ms
	 */
	static class Solution {
	    public boolean hasPathSum(TreeNode root, int sum) {
	    	if (root == null) {
	    		return false;
	    	}
	    	boolean isDone = false;
	    	Queue<TreeNode> q1 = new LinkedList<>();
	    	Queue<Integer> q2 = new LinkedList<>();
	    	q1.add(root);
	    	q2.add(root.val);
	    	int depth_now = 1;
	    	while (! q1.isEmpty()) {
	    		TreeNode root_now = q1.poll();
	    		depth_now = q2.poll();
	    		if (root_now.left == null && root_now.right == null && depth_now == sum) {
	    			isDone = true;
	    			break;
	    		}
	    		if (root_now.left != null) {
	    			q1.add(root_now.left);
	    			q2.add(depth_now + root_now.left.val);
	    		}
	    		if (root_now.right != null) {
	    			q1.add(root_now.right);
	    			q2.add(depth_now + root_now.right.val);
	    		}
	    	}
	        return isDone;
	    }
	}
}
