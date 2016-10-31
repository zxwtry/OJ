package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

public class Book030_分别用递归和非递归方式实现二叉树先序_中序_后序的遍历 {
	public static void main(String[] args) {
		
	}
	static class RecursionSoltuion {
		public void preOrder(TreeNode head) {
			if (head == null) {
				return;
			}
			System.out.print(head.val + " ");
			preOrder(head.left);
			preOrder(head.right);
		}
		public void inOrder(TreeNode head) {
			if (head == null) {
				return;
			}
			inOrder(head.left);
			System.out.print(head.val + " ");
			inOrder(head.right);
		}
		public void posOrder(TreeNode head) {
			if (head == null) {
				return;
			}
			posOrder(head.left);
			posOrder(head.right);
			System.out.print(head.val + " ");
		}
	}
}
