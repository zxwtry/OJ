package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

public class Book040_判断t1树是否包含t2树全部的拓扑结构 {
	public static void main(String[] args) {
		testBookSolution();
	}
	static void testBookSolution() {
		int[] arr1 = new int[] {
			1,
			2, 3,
			4, 5, 6, 7
		};
		int[] arr2 = new int[] {
			3,
			6, 7
		};
		TreeNode t1 = tools.TreeNode辅助.A_生成满二叉树(arr1);
		TreeNode t2 = tools.TreeNode辅助.A_生成满二叉树(arr2);
		BookSolution s = new BookSolution();
		System.out.println(s.contains(t1, t2));
	}
	static class BookSolution {
		public boolean contains(TreeNode t1, TreeNode t2) {
			return check(t1, t2) || contains(t1.left, t2) || contains(t1.right, t2);
		}
		boolean check(TreeNode h, TreeNode t) {
			if (h == null) {
				return true;
			}
			if (h == null || h.val != t.val) {
				return false;
			}
			return check(h.left, t.left) && check(h.right, t.right);
		}
	}
}