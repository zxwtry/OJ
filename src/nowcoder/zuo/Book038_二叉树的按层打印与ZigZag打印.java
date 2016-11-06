package nowcoder.zuo;

import java.util.LinkedList;
import java.util.Queue;

import tools.TreeNode辅助.TreeNode;

public class Book038_二叉树的按层打印与ZigZag打印 {
	public static void main(String[] args) {
		testBookSolutionLevel();
		
	}
	static void testBookSolutionLevel() {
		TreeNode head = tools.TreeNode辅助.A_生成满二叉树(new int[] {
			1,
			2, 3,
			4, 5, 6, 7,
			8, 9, 10, 11, 12, 13, 14, 15
		});
		BookSolutionLevel bsl = new BookSolutionLevel();
		bsl.printByLevel(head);
	}
	static class BookSolutionLevel {
		public void printByLevel(TreeNode head) {
			if (null == head) {
				return;
			}
			Queue<TreeNode> queue = new LinkedList<>();
			int level = 1;
			TreeNode last = head;
			TreeNode nLast = null;
			queue.offer(head);
			System.out.print("Level " + (level ++) + " : ");
			while (! queue.isEmpty()) {
				head = queue.poll();
				System.out.print(head.val + " ");
				if (head.left != null) {
					queue.offer(head.left);
					nLast = head.left;
				}
				if (head.right != null) {
					queue.offer(head.right);
					nLast = head.right;
				}
				if (head == last && ! queue.isEmpty()) {
					System.out.print("\nLevel " + (level ++) + " : ");
					last = nLast;
				}
			}
			System.out.println();
		}
	}
}
