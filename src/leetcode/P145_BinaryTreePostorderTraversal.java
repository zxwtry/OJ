package leetcode;

import java.util.LinkedList;
import java.util.List;

import tools.TreeNode辅助.TreeNode;

public class P145_BinaryTreePostorderTraversal {
	public static void main(String[] args) {
		TreeNode root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
				0,
				1,2,
				3,4,5,6
		});
		Solution s = new Solution();
		List<Integer> ans = s.postorderTraversal(root);
		tools.Utils.B_打印List_Integer(ans);
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(100, 0, 127);
		System.out.println(tools.Utils.LEETCODE_int_array_序列化_(arr));
	}
	static class Solution {
		List<Integer> ans = new LinkedList<>();
	    public List<Integer> postorderTraversal(TreeNode root) {
	    	if (root == null) {
	    		return ans;
	    	}
	    	LinkedList<TreeNode> q = new LinkedList<>();
	    	LinkedList<Boolean> q_isPolled = new LinkedList<>();
	    	q.add(root);
	    	q_isPolled.add(false);
	    	while (! q.isEmpty()) {
	    		TreeNode root_now = q.poll();
	    		boolean isPolled = q_isPolled.poll();
	    		if (isPolled) {
	    			ans.add(root_now.val);
	    			continue;
	    		}
	    		q.addFirst(root_now);
	    		q_isPolled.addFirst(true);
	    		if (root_now.right != null) {
	    			q.addFirst(root_now.right);
	    			q_isPolled.addFirst(false);
	    		}
	    		if (root_now.left != null) {
	    			q.addFirst(root_now.left);
	    			q_isPolled.addFirst(false);
	    		}
	    	}
	        return ans;
	    }
	}
}