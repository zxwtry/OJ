package nowcoder.zuo;

import java.util.Stack;

import tools.ListNode辅助.ListNode;

public class Book021_将单链表的每K个节点之间逆序 {
	public static void main(String[] args) {
//		testStackSolution();
		testAdjustSolution();
	}
	static void testAdjustSolution() {
		int sizeOfLine = 20;
		int K = 5;
		int n = 1;
		for (n = 0; n < sizeOfLine; n ++) {
			int[] arr = new int[n];
			for (int i = 0; i < n; i ++) {
				arr[i] = i;
			}
			ListNode head = tools.ListNode辅助.A_一维生成器(arr);
			AdjustSolution ss = new AdjustSolution();
			ListNode newHead = ss.reverseKNodes(head, K);
			tools.ListNode辅助.B_打印链表_一行(newHead, sizeOfLine);
		}
	}
	static void testStackSolution() {
		int sizeOfLine = 20;
		int K = 5;
		int n = 1;
		for (n = 0; n < sizeOfLine; n ++) {
			int[] arr = new int[n];
			for (int i = 0; i < n; i ++) {
				arr[i] = i;
			}
			ListNode head = tools.ListNode辅助.A_一维生成器(arr);
			StackSolution ss = new StackSolution();
			ListNode newHead = ss.reverseKNodes(head, K);
			tools.ListNode辅助.B_打印链表_一行(newHead, sizeOfLine);
		}
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
	//时间复杂度O(N)，额外空间复杂度O(1)
	static class AdjustSolution {
		public ListNode reverseKNodes(ListNode head, int K) {
			if (K < 2) {
				return head;
			}
			ListNode cur = head;
			ListNode pre = null;
			ListNode next = null;
			ListNode start = null;
			int count = 1;
			while (null != cur) {
				next = cur.next;
				if (count == K) {
					start = pre == null ? head : pre.next;
					head = pre == null ? cur : head;
					resign(pre, start, cur, next);
					pre = start;
					count = 0;
				}
				count ++;
				cur = next;
			}
			return head;
		}
		private void resign(ListNode left, ListNode start, ListNode end, ListNode right) {
			ListNode pre = start;
			ListNode cur = start.next;
			ListNode next = null;
			while (cur != right) {
				next = cur.next;
				cur.next = pre;
				pre = cur;
				cur = next;
			}
			if (left != null) {
				left.next = end;
			}
			start.next = right;
		}
	}
}
