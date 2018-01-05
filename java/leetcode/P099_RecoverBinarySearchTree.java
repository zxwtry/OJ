package leetcode;

import java.util.ArrayList;

/*
 * 	Two elements of a binary search tree (BST) are swapped by mistake.

	Recover the tree without changing its structure.
	
	Note:
	A solution using O(n) space is pretty straight forward. 
	Could you devise a constant space solution?
 */

import tools.TreeNode辅助.TreeNode;

public class P099_RecoverBinarySearchTree {
	static int N = Integer.MIN_VALUE;

	public static void main(String[] args) {
		TreeNode root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
//				1,
//				N,3,
//				N,N,N,2
				10,
				5, 15, 
				N, N, 7, 20 
		});
		tools.TreeNode辅助.B_按层打印(root);
		new Solution().recoverTree(root);
		tools.TreeNode辅助.B_按层打印(root);
	}
	/*
	 * 	这样的AC，有点挫
	 * 	192 ms
	 */
	static class Solution {
		ArrayList<TreeNode> arr = new ArrayList<TreeNode>();
		boolean isFalse = false;
		public void recoverTree(TreeNode root) {
			searchAllTreeNode(root);
			for (int i = 0; i < arr.size(); i++) {
				for (int j = i + 1; j < arr.size(); j++) {
					TreeNode tree1 = arr.get(i);
					TreeNode tree2 = arr.get(j);
					if (tree1 == tree2) {
						continue;
					}
					int temp = tree1.val;
					tree1.val = tree2.val;
					tree2.val = temp;
					isFalse = false;
					search_judge(root, Long.MAX_VALUE, Long.MIN_VALUE);
					if (!isFalse) {
						return;
					}
					temp = tree1.val;
					tree1.val = tree2.val;
					tree2.val = temp;
				}
			}
		}
		private void searchAllTreeNode(TreeNode root) {
			if (root == null) {
				return;
			}
			arr.add(root);
			if (root.right != null) {
				searchAllTreeNode(root.right);
			}
			if (root.left != null) {
				searchAllTreeNode(root.left);
			}
		}
		void search_judge(TreeNode root, long max_edge, long min_edge) {
			if (isFalse) {
				return;
			}
			if ((long) root.val >= max_edge || (long) root.val <= min_edge) {
				isFalse = true;
				return;
			}
			if (root.left != null) {
				search_judge(root.left, root.val, min_edge);
			}
			if (root.right != null) {
				search_judge(root.right, max_edge, root.val);
			}
		}
	}
}
