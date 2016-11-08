package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

public class Book044_判断一棵二叉树是否为搜索二叉树和完全二叉树 {
	public static void main(String[] args) {
		
	}
	static class BSTSolution {
		public boolean isBST(TreeNode head) {
			if (null == head) {
				return true;
			}
			boolean res = true;
			TreeNode pre = null;
			TreeNode cur1 = head;
			TreeNode cur2 = null;
			while (cur1 != null) {
				cur2 = cur1.left;
				if (cur2 != null) {
					while (cur2.right != null && cur2.right != cur1) {
						cur2 = cur2.right;
					}
					if (cur2.right == null) {
						cur2.right = cur1;
						cur1 = cur1.left;
						continue;
					} else {
						cur2.right = null;
					}
					if (pre != null && pre.val < cur1.val) {
						res = false;
					}
					pre = cur1;
					cur1 = cur1.right;
				}
			}
			return res;
		}
	}
}
