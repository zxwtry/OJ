package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

/*
 * 	 平衡二叉树的性质是：
 * 		要么是一棵空树，
 * 		要么任何一个节点的左右子树高度差的绝对值不超过1
 * 		给定一棵二叉树的头结点head，判断这棵二叉树是否为平衡树
 */

public class Book042_判断二叉树是否为平衡二叉树 {
	public static void main(String[] args) {
		
	}
	static class BookSolution {
		public boolean isBalance(TreeNode head) {
			boolean[] res = new boolean[1];
			res[0] = true;
			getHeight(head, level, res)
			return res[0];
		}
		public int getHeight(TreeNode head, int level, boolean[] res) {
			if (head == null) {
				return level;
			}
			int lH = getHeight(head.left, level + 1, res);
			if (! res[0]) {
				return level;
			}
			int rH = getHeight(head.right, level + 1, res);
			if (! res[0]) {
				return level;
			}
			if (Math.abs(lH - rH) > 1 ) {
				res[0] = false;
			}
			return Math.max(lH, rH);
		}
	}
}
