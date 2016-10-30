package nowcoder.zuo;

import java.util.LinkedList;
import java.util.Queue;

import tools.TreeNode辅助.TreeNode;

/*
 * 	二叉树的left对应双向链表的last
 * 	二叉树的right对应双向链表的next
 */

public class Book024_将搜索二叉树转换称为双向链表 {
	public static void main(String[] args) {
		
	}
	//时间复杂度O(N)，额外空间复杂度O(N)
	static class QueueSolution {
		public TreeNode convert(TreeNode head) {
			Queue<TreeNode> queue = new LinkedList<>();
			inOrderToQueue(head, queue);
			if (queue.isEmpty()) {
				return head;
			}
			head = queue.poll();
			TreeNode pre = head;
			pre.left = null;
			TreeNode cur = null;
			while (! queue.isEmpty()) {
				cur = queue.poll();
				pre.right = cur;
				cur.left = pre;
				pre = cur;
			}
			pre.right = null;
			return head;
		}
		private void inOrderToQueue(TreeNode head, Queue<TreeNode> queue) {
			if (head == null) {
				return;
			}
			inOrderToQueue(head.left, queue);
			queue.offer(head);
			inOrderToQueue(head.right, queue);
		}
	}
}
