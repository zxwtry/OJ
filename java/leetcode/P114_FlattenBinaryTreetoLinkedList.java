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
				4, 5, 6, 7,
				8, 9, 10, 11, 12, 13, 14, 15,
		});
		tools.TreeNode辅助.B_按层打印(root);
		new Solution3().flatten(root);
		tools.TreeNode辅助.B_按层打印(root);
	}
	//AC 1ms 31.65%
	static class Solution {
	    public void flatten(TreeNode root) {
	        pre_reverse_order(root, new TreeNode[] {null});
	    }
	    void pre_reverse_order(TreeNode root, TreeNode[] pre) {
	        if (root == null) return;
	        pre_reverse_order(root.right, pre);
	        pre_reverse_order(root.left, pre);
	        root.right = pre[0];
	        root.left = null;
	        pre[0] = root;
	    }
	}
	//AC 1ms 31.65%
	static class Solution3 {
	    public void flatten(TreeNode root) {
	        TreeNode n = root, p = null;
	        while (n != null) {
	            System.out.println(n);
	            while (n.left != null) {
	                p = n.left;
	                while (p.right != null)
	                    p = p.right;
	                p.right = n.right;
	                n.right = n.left;
	                n.left = null;
	            }
	            n = n.right;
	        }
	    }
	}
}
