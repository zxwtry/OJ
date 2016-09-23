package leetcode;

/*
 * 	Given a binary tree containing digits from 0-9 only, 
 * 	each root-to-leaf path could represent a number.
	
	An example is the root-to-leaf path 1->2->3 which represents the number 123.
	
	Find the total sum of all root-to-leaf numbers.
	
	For example,
	
	    1
	   / \
	  2   3
	The root-to-leaf path 1->2 represents the number 12.
	The root-to-leaf path 1->3 represents the number 13.
	
	Return the sum = 12 + 13 = 25.
 */

import tools.TreeNode辅助.TreeNode;

public class P129_SumRoottoLeafNumbers {
	public static void main(String[] args) {
		
	}
	/*
	 * 	1 ms
	 * 	27.61%
	 */
	static class Solution {
		int sum = 0;
	    public int sumNumbers(TreeNode root) {
	    	if (root == null) {
	    		return sum;
	    	}
	    	search(root, 0);
	        return sum;
	    }
		private void search(TreeNode root, int val) {
			if (root == null) {
				sum += val;
				return;
			}
			search(root.left, val + root.val);
			search(root.right, val + root.val);
		}
	}
}
