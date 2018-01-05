package leetcode;

/*
 * 	Given a binary tree, check whether it is a mirror of itself 
 * 	(ie, symmetric around its center).

	For example, this binary tree [1,2,2,3,4,4,3] is symmetric:
	
	    1
	   / \
	  2   2
	 / \ / \
	3  4 4  3
	But the following [1,2,2,null,3,null,3] is not:
	    1
	   / \
	  2   2
	   \   \
	   3    3
	Note:
	Bonus points if you could solve it both recursively and iteratively.
 */

import tools.TreeNode辅助.TreeNode;

public class P101_SymmetricTree {
	static int N = Integer.MIN_VALUE;
	public static void main(String[] args) {
		TreeNode root = null;
		root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
//				1,
//				2, 2,
//				3, 4, 4, 3,
//				5, 6, 7, 8, 8, 7, 6, 5
				
				1,
				2, 2,
				3, 4, 4, 3,
				5, 6, 7, 8, 8, N, 6, 5
				
		});
		Solution s = new Solution();
		System.out.println(s.isSymmetric(root));
	}
	/*
	 * 	AC
	 * 	1 ms
	 */
	static class Solution {
	    public boolean isSymmetric(TreeNode root) {
	    	if (root == null) {
	    		return true;
	    	}
	        return is_my(root.left, root.right);
	    }

		private boolean is_my(TreeNode left, TreeNode right) {
			if (left == null || right == null) {
				return left == null && right == null;
			}
			if (left.val != right.val) {
				return false;
			}
			return is_my(left.left, right.right) && is_my(left.right, right.left);
		}
	}
}
