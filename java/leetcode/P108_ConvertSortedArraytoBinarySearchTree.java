package leetcode;

import tools.TreeNode辅助.TreeNode;

/*
 * 	Given an array where elements are sorted in ascending order, 
 * 	convert it to a height balanced BST.
 */

public class P108_ConvertSortedArraytoBinarySearchTree {
	public static void main(String[] args) {
		
	}
	/*
	 * 	AC
	 * 	1 ms
	 */
	static class Solution {
	    public TreeNode sortedArrayToBST(int[] nums) {
	        if (nums == null || nums.length == 0) {
	        	return null;
	        }
	        return sortedArrayToBST(nums, 0, nums.length - 1);
	    }
		private TreeNode sortedArrayToBST(int[] nums, int i, int j) {
			if (i > j) {
				return null;
			}
			if (i == j) {
				return new TreeNode(nums[i]);
			}
			int root_index = (i + j) / 2;
			TreeNode root = new TreeNode(nums[root_index]);
			root.left = sortedArrayToBST(nums, i, root_index - 1);
			root.right = sortedArrayToBST(nums, root_index + 1, j);
			return root;
		}
	}
}
