package nowcoder.zuo;

import java.util.Stack;

import tools.ListNode辅助.ListNode;

public class Book021_将单链表的每K个节点之间逆序 {
	public static void main(String[] args) {
		
	}
	//时间复杂度O(N)，额外空间复杂度O(K)
	static class StackSolution {
		public ListNode reverseKNodes(ListNode head, int K) {
			if (K < 2) {
				return head;
			}
			Stack<ListNode> stack = new Stack<>();
			ListNode newHead = head;
			ListNode cur = head;
			ListNode pre = null;
			ListNode next = null;
			while (cur != null) {
				next = cur.next;
				stack.push(cur);
				if (stack.size() == K) {
					pre = resign(stack, pre, next);
					newHead = newHead == head ? cur : newHead;
				}
			}
			return newHead;
		}
		private ListNode resign(Stack<ListNode> stack, ListNode left, ListNode right) {
			ListNode cur = stack.pop();
			if (left != null) {
				left.next = cur;
			}
			ListNode next = null;
			while (! stack.isEmpty()) {
				next = stack.pop();
				cur.next = next;
				cur = next;
			}
			cur.next = right;
			return cur;
		}
	}
}
