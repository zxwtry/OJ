package leetcode;

import tools.TreeNode辅助.TreeNode;

public class P105_ConstructBinaryTreeFromPreorderAndInorderTraversal {
	public static void main(String[] args) {
		
	}
	/*
	 * 	AC
	 * 	19 ms
	 */
	static class Solution {
	    public TreeNode buildTree(int[] preorder, int[] inorder) {
	    	if (preorder == null || inorder == null) {
	    		return null;
	    	}
	    	if (preorder.length != inorder.length) {
	    		return null;
	    	}
	        return build(preorder, 0, preorder.length - 1, inorder, 0, inorder.length - 1);
	    }
		private TreeNode build(int[] preorder, int i, int j, int[] inorder, int k, int l) {
			if (i > j || k > l) {
				return null;
			}
			TreeNode root = null;
			int root_val = preorder[i];
			int in_index = k;
			for (; in_index <= l; in_index ++) {
				if (inorder[in_index] == root_val) {
					break;
				}
			}
			root = new TreeNode(root_val);
			root.left = build(preorder, i + 1, i + in_index - k, inorder, k, in_index - 1);
			root.right = build(preorder, i + in_index - k + 1, j, inorder, in_index + 1, l);
			return root;
		}
	}
}
