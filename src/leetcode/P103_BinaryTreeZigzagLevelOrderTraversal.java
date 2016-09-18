package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import tools.TreeNode辅助.TreeNode;

/*
 * 	Given a binary tree, return the zigzag level order traversal of its nodes' values.
 *  (ie, from left to right, then right to left for the next level and 
 *  alternate between).

	For example:
	Given binary tree [3,9,20,null,null,15,7],
	    3
	   / \
	  9  20
	    /  \
	   15   7
	return its zigzag level order traversal as:
	[
	  [3],
	  [20,9],
	  [15,7]
	]
 */

public class P103_BinaryTreeZigzagLevelOrderTraversal {
	public static void main(String[] args) {
		TreeNode root = null;
		root = tools.TreeNode辅助.A_生成满二叉树(new int[] {
//				1,
//				2, 2,
//				3, 4, 4, 3,
//				5, 6, 7, 8, 8, 7, 6, 5
				
				1,
				2, 3,
				4, 5, 6, 7,
				8, 9, 10, 11, 12, 13, 14, 15
				
		});
		Solution s = new Solution();
		System.out.println(s.zigzagLevelOrder(root));
	}
	/*
	 * 	AC
	 * 	2 ms
	 */
	static class Solution {
		List<List<Integer>> ans = new LinkedList<>();
	    public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
			if (root == null) {
				return ans;
			}
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			Queue<Integer> q_int = new LinkedList<Integer>();
			q.add(root);
			q_int.add(1);
			int pre_int = -1;
			ArrayList<Integer> this_list = null;
			while (! q.isEmpty()) {
				TreeNode root_now = q.poll();
				int q_now = q_int.poll();
				if (q_now != pre_int) {
					if (pre_int != -1) {
						if (pre_int % 2 == 0) {
							ArrayList<Integer> temp = new ArrayList<>(this_list.size());
							for (int i = this_list.size() - 1; i > -1; i --) {
								temp.add(this_list.get(i));
							}
							ans.add(temp);
						} else {
							ans.add(this_list);
						}
					}
					this_list = new ArrayList<>();
					pre_int = q_now;
				}
				this_list.add(root_now.val);
				if (root_now.left != null) {
					q.add(root_now.left);
					q_int.add(q_now + 1);
				}
				if (root_now.right != null) {
					q.add(root_now.right);
					q_int.add(q_now + 1);
				}
			}
			if (this_list != null) {
				if (pre_int % 2 == 0) {
					ArrayList<Integer> temp = new ArrayList<>(this_list.size());
					for (int i = this_list.size() - 1; i > -1; i --) {
						temp.add(this_list.get(i));
					}
					ans.add(temp);
				} else {
					ans.add(this_list);
				}
			}
	        return ans;
	    }
	}
}
