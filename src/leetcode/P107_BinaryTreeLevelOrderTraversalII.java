package leetcode;

import java.util.Iterator;

/*
 * 	Given a binary tree, return the bottom-up level order
 *  traversal of its nodes' values. (ie, from left to right,
 *   level by level from leaf to root).

	For example:
	Given binary tree [3,9,20,null,null,15,7],
	    3
	   / \
	  9  20
	    /  \
	   15   7
	return its bottom-up level order traversal as:
	[
	  [15,7],
	  [9,20],
	  [3]
	]
 */

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tools.TreeNode辅助.TreeNode;

public class P107_BinaryTreeLevelOrderTraversalII {
	static int N = Integer.MIN_VALUE;
	public static void main(String[] args) {
		TreeNode root = null;
		List<List<Integer>> ans = null;
		root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
			1,
			2, 3,
			4, 5, 6, 7
		});
		root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
			3,
			9, 20,
			N, N, 15, 7
		});
		root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
			3
		});
		ans = new Solution().levelOrderBottom(root);
		System.out.println(ans.size());
		Iterator<List<Integer>> it = ans.iterator();
		while (it.hasNext()) {
			tools.Utils.B_打印List_Integer_OneLine(it.next());
		}
	}
	/*
	 * 	AC
	 * 	3 ms
	 */
	static class Solution {
		List<List<Integer>> ans = new LinkedList<>();
	    public List<List<Integer>> levelOrderBottom(TreeNode root) {
	        if (root == null) {
	        	return ans;
	        }
	    	Queue<TreeNode> q1 = new LinkedList<TreeNode>();
	    	Queue<Integer> q2 = new LinkedList<Integer>();
	    	q1.add(root);
	    	q2.add(1);
	    	int pre_int = -1;
	    	List<Integer> list_root = null;
	        while (! q1.isEmpty()) {
	        	TreeNode root_now = q1.poll();
	        	int root_int = q2.poll();
	        	if (root_int != pre_int) {
	        		if (pre_int != - 1) {
	        			ans.add(0, list_root);
	        		}
	        		pre_int = root_int;
	        		list_root = new LinkedList<>();
	        	}
	        	list_root.add(root_now.val);
	        	if (root_now.left != null) {
	        		q1.add(root_now.left);
	        		q2.add(root_int + 1);
	        	}
	        	if (root_now.right != null) {
	        		q1.add(root_now.right);
	        		q2.add(root_int + 1);
	        	}
	        }
	        if (pre_int != -1 && list_root != null) {
	        	ans.add(0, list_root);
	        }
	    	return ans;
	    }
	}
}
