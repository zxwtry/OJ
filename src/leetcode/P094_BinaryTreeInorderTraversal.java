package leetcode;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;

import tools.TreeNode辅助.TreeNode;;

/*
 * 	Given a binary tree, return the inorder traversal of its nodes' values.

	For example:
	Given binary tree [1,null,2,3],
	   1
	    \
	     2
	    /
	   3
	return [1,3,2].	
 */


public class P094_BinaryTreeInorderTraversal {
	public static void main(String[] args) {
		TreeNode t0 = new TreeNode(0);
		TreeNode t1 = new TreeNode(1);
		TreeNode t2 = new TreeNode(2);
		TreeNode t3 = new TreeNode(3);
		TreeNode t4 = new TreeNode(4);
		TreeNode t5 = new TreeNode(5);
		TreeNode t6 = new TreeNode(6);
		t0.left = t1;
		t0.right = t2;
		t1.left = t3;
		t1.right = t4;
		t2.left = t5;
		t2.right = t6;
		List<Integer> ans = new Solution5().inorderTraversal(t0);
		tools.Utils.B_打印List_Integer(ans);
	}
	/*
	 * 	AC
	 * 	0 ms
	 * 	这个时候递归版本
	 */
	static class Solution1 {
		List<Integer> ans = new LinkedList<Integer>();
	    public List<Integer> inorderTraversal(TreeNode root) {
	    	in(root);
	        return ans;
	    }
	    void in(TreeNode root) {
	    	if (root == null) {
	    		return;
	    	}
	    	in(root.left);
	    	ans.add(root.val);
	    	in(root.right);
	    }
	}
	/*
	 * 	写了一个层序遍历
	 */
	static class Solution2 {
		List<Integer> ans = new LinkedList<Integer>();
    	Queue<TreeNode> queue = new LinkedList<TreeNode>();
	    public List<Integer> inorderTraversal(TreeNode root) {
	    	if (root == null) {
	    		return ans;
	    	}
	    	queue.add(root);
	    	while (! queue.isEmpty()) {
	    		TreeNode root_now = queue.poll();
	    		ans.add(root_now.val);
	    		if (root_now.left != null) {
	    			queue.add(root_now.left);
	    		}
	    		if (root_now.right != null) {
		    		queue.add(root_now.right);
	    		}
	    	}
	        return ans;
	    }
	}
	/*
	 * 	写了一个先序遍历
	 */
	static class Solution3 {
		List<Integer> ans = new LinkedList<>();
		public List<Integer> inorderTraversal(TreeNode root) {
			if (root == null) {
				return ans;
			}
			Stack<TreeNode> stack = new Stack<>();
			stack.push(root);
			while (! stack.isEmpty()) {
				TreeNode root_now = stack.pop();
				ans.add(root_now.val);
				if (root_now.right != null) {
					stack.push(root_now.right);
				}
				if (root_now.left != null) {
					stack.push(root_now.left);
				}
			}
			return ans;
		}
	}
	/*
	 * 	写了一个后序遍历
	 */
	static class Solution4 {
		List<Integer> ans = new LinkedList<>();
		public List<Integer> inorderTraversal(TreeNode root) {
			if (root == null) {
				return ans;
			}
			Stack<TreeNode> stack = new Stack<>();
			stack.push(root);
			while (! stack.isEmpty()) {
				TreeNode root_now = stack.peek();
				if (null != root_now.left && root != root_now.left && root != root_now.right) {
					stack.push(root_now.left);
				} else if (null != root_now.right && root != root_now.right) {
					stack.push(root_now.right);
				} else {
					ans.add(stack.pop().val);
					root = root_now;
				}
			}
			return ans;
		}
	}
	/*
	 * 	写了一个后序遍历
	 */
	static class Solution5 {
		List<Integer> ans = new LinkedList<>();
		public List<Integer> inorderTraversal(TreeNode root) {
			if (null == root) {
				return ans;
			}
			Stack<TreeNode> stack1 = new Stack<>();
			Stack<TreeNode> stack2 = new Stack<>();
			stack1.push(root);
			while (! stack1.isEmpty()) {
				TreeNode root_now = stack1.pop();
				stack2.push(root_now);
				if (null != root_now.left) {
					stack1.push(root_now.left);
				}
				if (null != root_now.right) {
					stack1.push(root_now.right);
				}
			}
			while (! stack2.isEmpty()) {
				ans.add(stack2.pop().val);
			}
			return ans;
		}
	}
}
