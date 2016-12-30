package leetcode;

import tools.TreeNode辅助.TreeNode;

/**
 * 	Given a binary search tree, write a function kthSmallest to find the kth 
 *  smallest element in it.

	Note: 
	You may assume k is always valid, 1 ≤ k ≤ BST's total elements.
	
	Follow up:
	What if the BST is modified (insert/delete operations) often and you 
	need to find the kth smallest frequently? How would you optimize 
	the kthSmallest routine?
	
	Hint:
	
	Try to utilize the property of a BST.
	What if you could modify the BST node's structure?
	The optimal runtime complexity is O(height of BST).
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P230_KthSmallestElementInABST.java
 * @type        P230_KthSmallestElementInABST
 * @date        2016年12月30日 上午10:02:18
 * @details     Solution1: AC 1ms 51.78%
 */
public class P230_KthSmallestElementInABST {
	static class Solution1 {
		int c = 0;
		int save = 0;
	    public int kthSmallest(TreeNode root, int k) {
	    	c = k;
	    	in(root);
	        return save;
	    }
		private void in(TreeNode root) {
			if (root == null) return;
			in(root.left);
			c --;
			if (c == 0) save = root.val;
			in(root.right);
		}
	}
}
