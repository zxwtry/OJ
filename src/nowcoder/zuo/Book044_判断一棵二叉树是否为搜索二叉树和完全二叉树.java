package nowcoder.zuo;

import java.util.LinkedList;
import java.util.Queue;

import tools.TreeNode辅助.TreeNode;

public class Book044_判断一棵二叉树是否为搜索二叉树和完全二叉树 {
	public static void main(String[] args) {
		testBSTSolution();
	}
	static void testBSTSolution() {
		int level = 9;
		int min = 0;
		int max = 1 << 30;
		double nullPercent = 0.1;
		TreeNode head = tools.BST辅助.A_随机生成一个搜索二叉树(level, min, max, nullPercent);
		BSTSolution s = new BSTSolution();
		System.out.println(s.isBST(head));
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
					if (pre != null && pre.val > cur1.val) {
						res = false;
					}
				}
				pre = cur1;
				cur1 = cur1.right;
			}
			return res;
		}
	}
	static class CSTSolution {
		public boolean isCBT(TreeNode head) {
			if (head == null) {
				return true;
			}
			Queue<TreeNode> q = new LinkedList<TreeNode>();
			boolean leaf = false;
			TreeNode l = null;
			TreeNode r = null;
			q.offer(head);
			while (! q.isEmpty()) {
				head = q.poll();
				l = head.left;
				r = head.right;
				if ((leaf && (l != null || r != null)) || (l == null && r != null)) {
					return false;
				}
				if (l != null) {
					q.offer(l);
				}
				if (r != null) {
					q.offer(r);
				} else {
					leaf = true;
				}
			}
			return true;
		}
	}
}
