package leetcode;

import java.util.LinkedList;
import java.util.List;

import tools.TreeNode辅助.TreeNode;

/**
 * Given a binary tree, return all root-to-leaf paths.

	For example, given the following binary tree:
	
	   1
	 /   \
	2     3
	 \
	  5
	All root-to-leaf paths are:
	
	["1->2->5", "1->3"]
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P257_BinaryTreePaths.java
 * @type        P257_BinaryTreePaths
 * @date        2016年12月12日 下午10:05:01
 * @details     Solution: AC 3ms  25.79%
 */
public class P257_BinaryTreePaths {
	static class  Solution {
		List<String> ans = new LinkedList<String>();
	    public List<String> binaryTreePaths(TreeNode root) {
	    	if (root == null) return ans;
	        StringBuilder st = new StringBuilder();
	        s(root, st, root);
	        return ans;
	    }
		private void s(TreeNode n, StringBuilder st, TreeNode root) {
			if (n == null) {
				ans.add(st.toString());
				return;
			}
			st.append((n == root ? "" : "->")+n.val);
			if (n.left == null && n.right == null) {
				s(null, st, root);
			} else {
				if (n.left != null) {
					s(n.left, st, root);
					st.delete(st.lastIndexOf("->") , st.length());
				}
				if (n.right != null) {
					s(n.right, st, root);
					st.delete(st.lastIndexOf("->") , st.length());
				}
			}
		}
	}
}
