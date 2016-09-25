package leetcode;

import tools.TreeNode辅助.TreeNode;

public class P404_SumOfLeftLeaves {
	static int N = Integer.MIN_VALUE;
	public static void main(String[] args) {
		TreeNode root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
				3,
				N, 20,
				N, N, -1, 7
		});
		Solution s = new Solution();
		System.out.println(s.sumOfLeftLeaves(root));
	}
	/*
	 * 	AC
	 */
	static class Solution {
		int sum = 0;
	    public int sumOfLeftLeaves(TreeNode root) {
	    	search(root, 0);
	        return sum;
	    }
		private void search(TreeNode root, int sign) {
			if (root == null) {
				return;
			}
			if (root.left == null && root.right == null && sign == -1) {
				sum += root.val;
			}
			if (root.left != null) {
				search(root.left, -1);
			}
			if (root.right != null) {
				search(root.right, 1);
			}
		}
	}
}
