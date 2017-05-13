package leetcode;

import java.util.LinkedList;
import java.util.Queue;


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
	static class Solution1 {
	    public int sumNumbers(TreeNode root) {
	    	if (root == null) return 0;
	    	int[] sum = {0};
	    	search(root, 0, sum);
	        return sum[0];
	    }
		private void search(TreeNode root, int val, int[] sum) {
		    if (root.left == null && root.right == null)
		        sum[0] += val * 10 + root.val;
			if (root.left != null)
			    search(root.left, val * 10 + root.val, sum);
			if (root.right != null)
			    search(root.right, val * 10 + root.val, sum);
		}
	}
}
