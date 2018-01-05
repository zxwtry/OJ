package leetcode;

import java.util.LinkedList;
import java.util.Queue;

/*
 * 	Given a binary tree, determine if it is a valid binary search tree (BST).

	Assume a BST is defined as follows:
	
	The left subtree of a node contains only nodes with keys less than the node's key.
	The right subtree of a node contains only nodes with keys greater than the node's key.
	Both the left and right subtrees must also be binary search trees.
	Example 1:
	    2
	   / \
	  1   3
	Binary tree [2,1,3], return true.
	Example 2:
	    1
	   / \
	  2   3
	Binary tree [1,2,3], return false.
 */

import tools.TreeNode辅助.TreeNode;;

public class P098_ValidateBinarySearchTree {
	static int N = Integer.MIN_VALUE;
	public static void main(String[] args) {
		TreeNode root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
				10,
				5, 15,
				N, N, 6, 20
		});
		root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
				10,
				5, 15,
				N, N, 12, 20
		});
		Solution2 s = new Solution2();
		System.out.println(s.isValidBST(root));
		tools.TreeNode辅助.B_按层打印(root);
	}
	/*
	 * 	这种想法太简单了，肯定是错误的。
	 */
	static class Solution {
	    public boolean isValidBST(TreeNode root) {
	    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
	    	queue.add(root);
	    	while (! queue.isEmpty()) {
	    		TreeNode root_now = queue.poll();
	    		if (root_now.left != null) {
	    			if (root_now.left.val > root_now.val) {
	    				return false;
	    			}
	    			queue.add(root_now.left);
	    		}
	    		if (root_now.right != null) {
	    			if (root_now.right.val < root_now.val) {
	    				return false;
	    			}
	    			queue.add(root_now.right);
	    		}
	    		
	    	}
	        return true;
	    }
	}
	/*
	 * 	AC
	 * 	0 ms
	 */
	static class Solution2 {
		boolean isFalse = false;
	    public boolean isValidBST(TreeNode root) {
	    	if (root == null) {
	    		return true;
	    	}
	    	search(root, Long.MAX_VALUE, Long.MIN_VALUE);
	    	return ! isFalse;
	    }
	    /*
	     * 	保证root != null
	     */
	    void search(TreeNode root, long max_edge, long min_edge) {
	    	if (isFalse) {
	    		return;
	    	}
	    	if ((long) root.val >= max_edge || (long) root.val <= min_edge) {
	    		isFalse = true;
	    		return;
	    	}
	    	if (root.left != null) {
	    		search(root.left, root.val, min_edge);
	    	}
	    	if (root.right != null) {
	    		search(root.right, max_edge, root.val);
	    	}
	    }
	}
}
