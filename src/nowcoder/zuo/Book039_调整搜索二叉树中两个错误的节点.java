package nowcoder.zuo;

import java.util.Stack;

import tools.TreeNode辅助.TreeNode;

/*
 * 	就是给一个搜索二叉树
 * 	所有的节点值都不一样
 * 	现在交换两个节点的位置，使得搜索二叉树不再成立
 * 	现在需要找到这两个节点
 */

public class Book039_调整搜索二叉树中两个错误的节点 {
	public static void main(String[] args) {
//		testBookSolutionRoot();
		testBookSolutionMiddle();
	}
	static void testBookSolutionMiddle() {
		int[] arr = new int[] {
			6,
			10, 3,
			1, 4, 7, 12
		};
		TreeNode head = tools.TreeNode辅助.A_生成满二叉树(arr);
		BookSolution bs = new BookSolution();
		TreeNode[] tns = bs.getTwoErrNodes(head);
		System.out.println(tns[0] == null ? "null" : tns[0].val);
		System.out.println(tns[1] == null ? "null" : tns[1].val);
	}
	static void testBookSolutionRoot() {
		int[] arr = new int[] {
			1,
			3, 10,
			6, 4, 7, 12
		};
		TreeNode head = tools.TreeNode辅助.A_生成满二叉树(arr);
		BookSolution bs = new BookSolution();
		TreeNode[] tns = bs.getTwoErrNodes(head);
		System.out.println(tns[0] == null ? "null" : tns[0].val);
		System.out.println(tns[1] == null ? "null" : tns[1].val);
	}
	static class BookSolution {
		public TreeNode[] getTwoErrNodes(TreeNode head) {
			TreeNode[] errs = new TreeNode[2];
			if (head == null) {
				return errs;
			}
			Stack<TreeNode> stack = new Stack<TreeNode>();
			TreeNode pre = null;
			while (! stack.isEmpty() || head != null) {
				if (head != null) {
					stack.push(head);
					head = head.left;
				} else {
					head = stack.pop();
					if (pre != null && pre.val > head.val) {
						errs[0] = errs[0] == null ? pre : errs[0];
						errs[1] = head;
					}
					pre = head;
					head = head.right;
				}
			}
			return errs;
		}
	}
}