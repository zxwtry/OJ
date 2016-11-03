package nowcoder.zuo;

import java.util.Stack;

import tools.TreeNode辅助.TreeNode;

public class Book030_分别用递归和非递归方式实现二叉树先序_中序_后序的遍历 {
	public static void main(String[] args) {
		testPre();
	}
	static void testPre() {
		RecursionSoltuion rs = new RecursionSoltuion();
		NoneRecursionSolution nrs = new NoneRecursionSolution();
		int maxLevel = 5;
		int min = 0;
		int max = 100;
		double nullPercent = 0.1;
		TreeNode head = tools.TreeNode辅助.A_生成随机二叉树(maxLevel, min, max, nullPercent);
		rs.preOrder(head);
		nrs.preOrder(head);
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
	static class NoneRecursionSolution {
		public void preOrder(TreeNode head) {
			System.out.print("pre-order: ");
			if (head != null) {
				Stack<TreeNode> stack = new Stack<TreeNode>();
				stack.add(head);
				while (! stack.isEmpty()) {
					head = stack.pop();
					System.out.println(head.val + " ");
					if (head.right != null) {
						stack.push(head.right);
					}
					if (head.left != null) {
						stack.push(head.left);
					}
				}
			}
		}
		public void inOrder(TreeNode head) {
			System.out.println("in-order: ");
			if (head != null) {
				Stack<TreeNode> stack = new Stack<TreeNode>();
				while (! stack.isEmpty()) {
					if (head != null) {
						stack.push(head);
						head = head.left;
					} else {
						head = stack.pop();
						System.out.println(head.val + " ");
						head = head.right;
					}
				}
			}
			System.out.println();
		}
		public void posOrder(TreeNode head) {
			System.out.println("pos-order: ");
			if (head != null) {
				Stack<TreeNode> s1 = new Stack<TreeNode>();
				Stack<TreeNode> s2 = new Stack<TreeNode>();
				s1.push(head);
				while (! s1.isEmpty()) {
					head = s1.pop();
					s2.push(head);
					if (head.left != null) {
						s1.push(head.left);
					}
					if (head.right != null) {
						s1.push(head.right);
					}
				}
				while (! s2.isEmpty()) {
					System.out.print(s2.pop().val + " ");
				}
			}
			System.out.println();
		}
		public void posOrder_OneStack(TreeNode head) {
			System.out.println("pos-order: ");
			if (head != null) {
				Stack<TreeNode> stack = new Stack<>();
				stack.push(head);
				TreeNode curHead = null;
				while (! stack.isEmpty()) {
					curHead = stack.peek();
					if (curHead.left == null && head!= curHead.left && head != curHead.right) {
						stack.push(curHead.left);
					} else if (curHead.right != null && head != curHead.right) {
						stack.push(curHead.left);
					} else {
						System.out.print(stack.pop().val + " ");
						head = curHead;
					}
				}
			}
		}
	}
}
