package leetcode;


/*
 * 	Given a binary tree, flatten it to a linked list in-place.

	For example,
	Given
	
	         1
	        / \
	       2   5
	      / \   \
	     3   4   6
	The flattened tree should look like:
	   1
	    \
	     2
	      \
	       3
	        \
	         4
	          \
	           5
	            \
	             6
	click to show hints.
	
	Hints:
	If you notice carefully in the flattened tree,
	 each node's right child points to the next node of a pre-order traversal.
 */


import tools.TreeNode辅助.TreeNode;

public class P114_FlattenBinaryTreetoLinkedList {
	static int N = Integer.MIN_VALUE;
	public static void main(String[] args) {
		TreeNode root = tools.TreeNode辅助.A_生成满二叉树(new int[]{
				1,
				2, 3,
				N, 5, 6, 7
		});
		tools.TreeNode辅助.B_按层打印(root);
		new Solution().flatten(root);
		tools.TreeNode辅助.B_按层打印(root);
	}
	/*
	 * 	AC
	 * 	1 ms
	 */
	static class Solution {
		TreeNode pre = null;
	    public void flatten(TreeNode root) {
	        pre_order(root);
	    }
	    void pre_order(TreeNode root) {
	    	if (root == null) {
	    		return;
	    	}
	    	if (pre != null) {
	    		pre.right = root;
	    	}
	    	pre = root;
	    	TreeNode left = root.left, right = root.right;
	    	if (left != null) {
	    		root.left = null;
	    		pre_order(left);
	    	}
	    	if (right != null) {
	    		pre_order(right);
	    	}
	    }
	}
}
