package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

public class Book013_删除链表的中间节点和a_b处的节点 {
	public static void main(String[] args) {
		testListMiddle();
	}
	static void testListMiddle() {
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(20, 0, 100);
		ListNode head = tools.ListNode辅助.A_一维生成器(arr);
		tools.ListNode辅助.B_打印链表_一行(head, 100);
		SolutionListMiddle slm = new SolutionListMiddle();
		head = slm.removeMiddleNode(head);
		tools.ListNode辅助.B_打印链表_一行(head, 100);
	}
	static class SolutionListMiddle {
		public ListNode removeMiddleNode(ListNode head) {
			if (head == null || head.next == null) {
				return head;
			}
			if (head.next.next == null) {
				return head.next;
			}
			ListNode pre = head;
			ListNode cur = head.next.next;
			while (cur.next != null && cur.next.next != null) {
				pre = pre.next;
				cur = cur.next.next;
			}
			pre.next = pre.next.next;
			return head;
		}
	}
}
