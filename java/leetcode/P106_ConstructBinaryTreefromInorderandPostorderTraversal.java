package leetcode;

import tools.TreeNode辅助.TreeNode;

public class P106_ConstructBinaryTreefromInorderandPostorderTraversal {
	public static void main(String[] args) {
		TreeNode root = new Solution().buildTree(new int[] {2, 1, 3}, new int[] {2, 3, 1});
		tools.TreeNode辅助.B_按层打印(root);
	}
	/*
	 * 	AC
	 * 	20 ms
	 */
	static class Solution {
	    public TreeNode buildTree(int[] inorder, int[] postorder) {
	        if (inorder == null || postorder == null || inorder.length == 0) {
	        	return null;
	        }
	        if (inorder.length != postorder.length) {
	        	return null;
	        }
	        return build(inorder, 0, inorder.length - 1, postorder, 0, postorder.length - 1);
	    }
		private TreeNode build(int[] inorder, int i, int j, int[] postorder, int k, int l) {
			if (i > j || k > l) {
				return null;
			}
			int root_val = postorder[l];
			int in_index = i;
			for (; in_index <= j; in_index ++) {
				if (inorder[in_index] == root_val) {
					break;
				}
			}
			if (in_index > j) {
				return null;
			}
			TreeNode root = new TreeNode(root_val);
			root.left = build(inorder, i, in_index - 1, postorder, k, k + in_index - i - 1);
			root.right = build(inorder, in_index + 1, j, postorder, k + in_index - i, l - 1);
			return root;
		}
	}
}
