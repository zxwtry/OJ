package leetcode;

import tools.TreeNode辅助.TreeNode;

/*
 * 	Given a binary tree, find the maximum path sum.

	For this problem, a path is defined as any sequence of nodes
	 from some starting node to any node in the tree 
	 along the parent-child connections. 
	 The path does not need to go through the root.
	
	For example:
	Given the below binary tree,
	
	       1
	      / \
	     2   3
	Return 6.
 */


public class P124_BinaryTreeMaximumPathSum {
	static int N = Integer.MIN_VALUE;
	public static void main(String[] args) {
		TreeNode root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
				-1,
				-2,-3,
//				40, 60, 800, 100,
//				1,2,3,40,600,800,100,0
//				-6,
//				N, 3,
//				N,N,2,N
				
		});
		Solution s = new Solution();
		System.out.println(s.maxPathSum(root));
	}
	/*
	 * 	3 ms
	 * 	20.74%
	 */
	static class Solution {
		int max = Integer.MIN_VALUE;
		int single_max = Integer.MIN_VALUE;
		boolean isCut = false;
	    public int maxPathSum(TreeNode root) {
	    	if (root == null) {
	    		return Integer.MIN_VALUE;
	    	}
	    	search(root);
	        return max;
	    }
		private int search(TreeNode root) {
			if (root == null) {
				return 0;
			}
			isCut = false;
			max = Math.max(max, root.val);
			single_max = Math.max(single_max, root.val);
			int left_val = 0;
			if (root.left != null) {
				left_val = search(root.left);
				if (! isCut) {
					max = Math.max(max, left_val);
				}
			}
			int right_val = 0;
			if (root.right != null) {
				right_val = search(root.right);
				if (! isCut) {
					max = Math.max(max, right_val);
				}
			}
			max = Math.max(max, root.val + left_val + right_val);
			int val = root.val + Math.max(left_val, right_val);
			max = Math.max(max, val);
			if (val < 0) {
				isCut = true;
				return 0;
			} else {
				return val;
			}
		}
	}
}
