package nowcoder.zuo;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

import org.w3c.dom.ls.LSException;

import com.sun.istack.internal.localization.NullLocalizable;

import tools.TreeNode辅助.TreeNode;

/*
 * 	 所谓ZigZag就是，
 * 	1	3	2	4	5	6	7	15	14	13	12	11	10	9	8
 */

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
	static class BookSolutionZigZag {
		public void printByZigZag(TreeNode head) {
			if (head == null) {
				return;
			}
			Deque<TreeNode> dq = new LinkedList<TreeNode>();
			int level = 1;
			boolean lr = true;
			TreeNode last = head;
			TreeNode nLast = null;
			dq.offerFirst(head);
			printLevelAndOrientation(level ++, lr);
			while (! dq.isEmpty()) {
				if (lr) {
					head = dq.pollFirst();
					if (head.left != null) {
						nLast = nLast == null ? head.left : nLast;
						dq.offerLast(head.left);
					}
					if (head.right != null) {
						nLast = nLast == null ? head.right : nLast;
						dq.offerLast(head.right);
					}
				} else {
					head = dq.pollLast();
					if (head.right == null) {
						nLast = nLast == null ? head.right : nLast;
						dq.offerFirst(head.right);
					}
					if (head.left == null) {
						nLast = nLast == null ? head.left : nLast;
						dq.offerFirst(head.left);
					}
				}
				System.out.print(head.val + " ");
				if (head == last && ! dq.isEmpty()) {
					lr = !lr;
					last = nLast;
					nLast = null;
					System.out.println();
					printLevelAndOrientation(level ++, lr);
				}
			}
			System.out.println();
		}
		void printLevelAndOrientation(int level, boolean lr) {
			System.out.print("Level " + level + " from ");
			System.out.print(lr ? "left to right: " : "right to left: ");
		}
	}
}
