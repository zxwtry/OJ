package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

public class Book037_找到二叉树中符合二叉树条件的最大拓扑结构 {
	public static void main(String[] args) {
		
	}
	/*
	 * 	这个方法的时间复杂度是O(N^2)
	 */
	static class Solution {
		public int bstTopoSize1(TreeNode head) {
			if (head == null) {
				return 0;
			}
			int max = maxTopo(head, head);
			max = Math.max(bstTopoSize1(head.left), max);
			max = Math.max(bstTopoSize1(head.right), max);
			return max;
		}
		private int maxTopo(TreeNode h, TreeNode n) {
			if (h != null && n != null && isBSTNode(h, n, n.val)) {
				return maxTopo(h, n.left) + maxTopo(h, n.right);
			}
			return 0;
		}
		private boolean isBSTNode(TreeNode h, TreeNode n, int val) {
			if (h == null) {
				return false;
			}
			if (h == n) {
				return true;
			}
			return isBSTNode(h.val > val ? h.left : h.right, n, val);
		}
	}
	static class Solution2 {
		
	}
}
