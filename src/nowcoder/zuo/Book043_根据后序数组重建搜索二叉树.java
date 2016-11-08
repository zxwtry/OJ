package nowcoder.zuo;

import tools.TreeNode辅助.TreeNode;

public class Book043_根据后序数组重建搜索二叉树 {
	public static void main(String[] args) {
		testBookSolution();
	}
	static void testBookSolution() {
		//生成搜索二叉树
		int level = 3;
		TreeNode head = tools.BST辅助.A_生成一个满搜索二叉树_0(level);
		int[] pos = tools.TreeNode辅助.C_后序数组(head);
		BookSolution s = new BookSolution();
		System.out.println(s.isBSTPos(pos));
	}
	/*
	 * 	给定一个整型数组arr，已知其中没有重复值，
	 * 	判断arr是否可能是搜索二叉树后序遍历的结果
	 */
	static class BookSolution {
		public boolean isBSTPos(int[] arr) {
			if (arr == null || arr.length == 0) {
				return false;
			}
			return isBSTPos(arr, 0, arr.length - 1);
		}
		private boolean isBSTPos(int[] arr, int sti, int eni) {
			if (sti == eni) {
				return true;
			}
			int less = -1;
			int more = eni;
			for (int i = sti; i < eni; i ++) {
				if (arr[eni] > arr[i]) {
					less = i;
				} else {
					more = more == eni ? i : more;
				}
			}
			if (less == -1 || more == eni) {
				return isBSTPos(arr, sti, eni - 1);
			}
			if (less != more - 1) {
				return false;
			}
			return isBSTPos(arr, sti, less) && isBSTPos(arr, more, eni - 1);
		}
	}
}
