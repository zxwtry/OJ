package leetcode;

import java.util.LinkedList;
import java.util.List;

import tools.TreeNode辅助.TreeNode;

public class P144_BinaryTreePreorderTraversal {
	public static void main(String[] args) {
		Solution s = new Solution();
		TreeNode root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
				0,
				1,Integer.MIN_VALUE
//				3,4,5,6
		});
		tools.Utils.B_打印List_Integer(s.preorderTraversal(root));
	}
	/*
	 * 	1 ms
	 *  29.64%
	 */
	static class Solution {
		List<Integer> ans = new LinkedList<>();
	    public List<Integer> preorderTraversal(TreeNode root) {
	    	if (root == null) {
	    		return ans;
	    	}
	    	LinkedList<TreeNode> q = new LinkedList<TreeNode>();
	    	q.add(root);
	    	while (! q.isEmpty()) {
	    		TreeNode root_now = q.pollFirst();
	    		ans.add(root_now.val);
	    		if (root_now.right != null)
	    			q.addFirst(root_now.right);
	    		if (root_now.left != null)
	    			q.addFirst(root_now.left);
	    	}
	        return ans;
	    }
	}
	/*
	 * 	作为标准对照
	 */
	static class Solution2 {
		List<Integer> ans = new LinkedList<>();
	    public List<Integer> preorderTraversal(TreeNode root) {
	    	pre_order(root);
	        return ans;
	    }
		private void pre_order(TreeNode root) {
			if (root == null) {
				return;
			}
			ans.add(root.val);
			pre_order(root.left);
			pre_order(root.right);
		}
	}
	
}
