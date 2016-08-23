package leetcode;

public class P019_RemoveNthFromEndOfList {
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		ListNode node7 = new ListNode(7);
		ListNode node8 = new ListNode(8);
		node1.next = node2;
		node2.next = node3;
		node3.next = node4;
		node4.next = node5;
		node5.next = node6;
		node6.next = node7;
		node7.next = node8;
		ListNode ans = new Solution().removeNthFromEnd(null, 8);
		while (ans != null) {
			System.out.println(ans.val);
			ans = ans.next;
		}
	}
	/*
	 * 	1ms
	 * 	5.49%
	 */
	static class Solution {
		public ListNode removeNthFromEnd(ListNode head, int n) {
			ListNode cur = head, pre = head;
			int count = 0;
			while (cur != null) {
				cur = cur.next;
				++ count;
				if (count == n) {
					break;
				}
			}
			if (cur == null && count == n)
				return head.next;
			if (count != n)
				return head;
			while (cur.next != null) {
				pre = pre.next;
				cur = cur.next;
			}
			pre.next = pre.next.next;
		    return head;
		}
	}
	static class ListNode {
		int val;
		ListNode next;
		public ListNode(int val) {
			this.val = val;
		}
	}
}