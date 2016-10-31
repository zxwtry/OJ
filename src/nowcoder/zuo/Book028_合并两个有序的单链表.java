package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

public class Book028_合并两个有序的单链表 {
	public static void main(String[] args) {
		
	}
	static class MergeSolution {
		public ListNode merge(ListNode head1, ListNode head2) {
			if (head1 == null || head2 == null) {
				return head1 == null ? head2 : head1;
			}
			ListNode head = head1.val < head2.val ? head1 : head2;
			ListNode cur1 = head == head1 ? head1 : head2;
			ListNode cur2 = head == head1 ? head2 : head1;
			ListNode pre = null;
			ListNode next = null;
			while (cur1 != null && cur2 != null) {
				if (cur1.val <= cur2.val) {
					pre = cur1;
					cur1 = cur1.next;
				} else {
					next = cur2.next;
					pre.next = cur2;
					cur2.next = cur1;
					pre = cur2;
					cur2 = next;
				}
			}
			pre.next = cur1 == null ? cur2 : cur1;
			return head;
		}
	}
}
