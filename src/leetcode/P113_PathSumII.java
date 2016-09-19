package leetcode;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import tools.TreeNode辅助.TreeNode;

public class P113_PathSumII {
	public static void main(String[] args) {
		
	}
	/*
	 * 	AC
	 * 	3 ms
	 */
	static class Solution {
		List<List<Integer>> ans = new LinkedList<>();
		ArrayList<Integer> path = new ArrayList<>();
	    public List<List<Integer>> pathSum(TreeNode root, int sum) {
	    	if (root == null) {
	    		return ans;
	    	}
	    	search(root, sum, 0);
	        return ans;
	    }
		private void search(TreeNode root, int sum, int now) {
			if (root.left == null && root.right == null && now + root.val == sum) {
				List<Integer> temp = new LinkedList<>();
				for (int i = 0; i < path.size();i ++) {
					temp.add(path.get(i));
				}
				ans.add(temp);
				return;
			}
			if (root.left != null) {
				path.add(root.val);
				search(root, sum, now + root.val);
				path.remove(path.size() - 1);
			}
			if (root.right != null) {
				path.add(root.val);
				search(root, sum, now + root.val);
				path.remove(path.size() - 1);
			}
		}
	}
}
