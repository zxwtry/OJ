package leetcode;

import tools.TreeNode辅助.TreeNode;

/*
 * 	Implement an iterator over a binary search tree (BST). 
 * 	Your iterator will be initialized with the root node of a BST.
	
	Calling next() will return the next smallest number in the BST.
	
	Note: next() and hasNext() should run in average O(1) time
	and uses O(h) memory, where h is the height of the tree.
 */

public class P173_BinarySearchTreeIterator {
	public static void main(String[] args) {
		int N = Integer.MIN_VALUE;
		TreeNode root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
			10,
			N, N,
			N, N, N, N
		});
		BSTIterator it = new BSTIterator(root);
		while (it.hasNext()) {
			System.out.println(it.next());
		}
	}
	/*
	 * 	4 ms 97.56%
	 */
	static class BSTIterator {
		TreeNode root = null;
		TreeNode minNode = null;
		TreeNode minNodeParent = null;
	    public BSTIterator(TreeNode root) {
	        this.root = root;
	    }

	    /** @return whether we have a next smallest number */
	    public boolean hasNext() {
	    	minNode = root;
	    	minNodeParent = null;
	    	while (minNode != null && minNode.left != null) {
	    		minNodeParent = minNode;
	    		minNode = minNode.left;
	    	}
	        return minNode != null;
	    }

	    /** @return the next smallest number */
	    public int next() {
	    	if (minNodeParent != null) {
	    		minNodeParent.left = minNode.right;
	    	} else {
	    		root = minNode.right;
	    	}
	        return minNode.val;
	    }
	}
}
