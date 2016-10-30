package nowcoder.zuo;

import java.util.Stack;

import tools.ListNode辅助.ListNode;

/*
 * 	给链表1->2->3->4->null, num=3，链表调整后为：1->2->4->null
 */

public class Book023_在单链表中删除指定值的节点 {
	public static void main(String[] args) {
		
	}
	//时间复杂度O(N)，额外空间复杂度O(N)
	static class StackSolution {
		public ListNode removeVal(ListNode head, int num) {
			Stack<ListNode> stack = new Stack<>();
			while (head != null) {
				if (head.val != num) {
					stack.push(head);
				}
				head = head.next;
			}
			while (! stack.isEmpty()) {
				stack.peek().next = head;
				head = stack.pop();
			}
			return head;
		}
	}
}
