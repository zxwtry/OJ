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
	static class Solution2 {
	    public int sumNumbers(TreeNode root) {
	        if (root == null) return 0;
	        Queue<TreeNode> q = new LinkedList<TreeNode>();
	        Queue<Integer> v = new LinkedList<Integer>();
	        q.add(root);
	        v.add(0);
	        int ans = 0, t = 0;
	        TreeNode n = null;
	        while (! q.isEmpty()) {
	            n = q.poll();
	            t = v.poll();
	            if (n.left == null && n.right == null)
	                ans += t*10+n.val;
	            if (n.left != null) {
	                q.add(n.left);
	                v.add(t*10 + n.val);
	            }
	            if (n.right != null) {
	                q.add(n.right);
	                v.add(t*10 + n.val);
	            }
	        }
	        return ans;
	    }
	}
}
