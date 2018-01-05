package leetcode;

import java.util.LinkedList;
import java.util.List;

import tools.TreeNode辅助.TreeNode;

/*
 * 	Given a binary tree, imagine yourself standing on the right side of it, 
 * 	return the values of the nodes you can see ordered from top to bottom.

	For example:
	Given the following binary tree,
	   1            <---
	 /   \
	2     3         <---
	 \     \
	  5     4       <---
	You should return [1, 3, 4].
 */

public class P199_BinaryTreeRightSideView {
	public static void main(String[] args) {
		int N = Integer.MIN_VALUE;
		TreeNode root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
			1,
			2, 5,
			3, N, N ,N
		});
		Solution s = new Solution();
		System.out.println(s.rightSideView(root));
	}
	/*
	 * 	1 ms
	 * 	77.68%
	 */
	static class Solution {
		int layerValuable = 0;
	    public List<Integer> rightSideView(TreeNode root) {
	    	List<Integer> ans = new LinkedList<Integer>();
	    	if (null != root) {
		    	int layer = 0;
		    	search(root, layer, ans);
	    	}
	    	return ans;
	    }
		private void search(TreeNode root, int layer, List<Integer> ans) {
			//需要先向右，再向左
			if (layer == layerValuable) {
				ans.add(root.val);
				layerValuable ++;
			}
			if (root.right != null) {
				search(root.right, layer + 1, ans);
			}
			if (root.left != null) {
				search(root.left, layer + 1, ans);
			}
		}
	}
}
