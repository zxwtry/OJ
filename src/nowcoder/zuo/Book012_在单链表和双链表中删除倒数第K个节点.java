package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

public class Book012_在单链表和双链表中删除倒数第K个节点 {
	public static void main(String[] args) {
		
	}
	static class SolutionSingleList {
		public ListNode removeLastKthNode(ListNode head, int lastKth) {
			if (head == null || lastKth < 1) {
				return head;
			}
			ListNode cur = head;
			while (cur != null) {
				lastKth --;
				cur = cur.next;
			}
			if (lastKth == 0) {
				head = head.next;
			}
			if (lastKth < 0) {
				cur = head;
				while (++ lastKth != 0) {
					cur = cur.next;
				}
				cur.next = cur.next.next;
			}
			return head;
		}
	}
}
