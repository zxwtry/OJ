package leetcode;

import tools.TreeNode辅助.TreeNode;

/*
 * 	Given a complete binary tree, count the number of nodes.
	
	Definition of a complete binary tree from Wikipedia:
	In a complete binary tree every level, except possibly the last, 
	is completely filled, and all nodes in the last level are as far left as possible.
	 It can have between 1 and 2h nodes inclusive at the last level h.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P222_CountCompleteTreeNodes.java
 * @type        P222_CountCompleteTreeNodes
 * @date        2016年12月29日 下午10:17:14
 * @details     Solution1: AC 63ms 91.49%
 */
public class P222_CountCompleteTreeNodes {
	static class Solution1 {
		public int countNodes(TreeNode n) {
			if (n == null) return 0;
			return bin(n, 1, l2n(n, 1));
		}
		private int bin(TreeNode n, int l, int h) {
			if (l == h) return 1;
			if (l2n(n.right, l+1) == h) return (1 << (h-l)) + bin(n.right, l + 1, h);
			else return (1 << (h-l-1)) + bin(n.left, l + 1, h);
		}
		private int l2n(TreeNode n, int i) {
			while (n != null) {
				i ++;
				n  = n.left;
			}
			return i-1;
		}
	}
}
