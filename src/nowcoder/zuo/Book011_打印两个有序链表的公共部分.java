package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

public class Book011_打印两个有序链表的公共部分 {
	static class Solution {
		public void printCommonPart(ListNode head1, ListNode head2) {
			while (head1 != null && head2 != null) {
				if (head1.val < head2.val) {
					head1 = head1.next;
				} else if (head1.val > head2.val) {
					head2 = head2.next;
				} else {
					System.out.print(head1.val + " ");
					head1 = head1.next;
					head2 = head2.next;
				}
			}
			System.out.println();
		}
	}
}
