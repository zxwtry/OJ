package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

/*
 * 	前N/2算作左半区，其余算作右半区
 */

public class Book029_按照左右半区的方式重新组合单链表 {
	public static void main(String[] args) {
		test();
	}
	static void test() {
		int len = (int)(Math.random() * 1000);
		int[] arr = new int[len];
		for (int i = 0; i < len; i ++) {
			arr[i] = i;
		}
		ListNode head = tools.ListNode辅助.A_一维生成器(arr);
		System.out.println("len is " + len);
		RelocateSolution rs = new RelocateSolution();
		rs.relocate(head);
		int sizeOfLine = len + 2;
		tools.ListNode辅助.B_打印链表_一行(head, sizeOfLine);
	}
	static class RelocateSolution {
		public void relocate(ListNode head) {
			if (null == head || head.next == null) {
				return;
			}
			ListNode mid = head;
			ListNode right = head.next;
			while (right.next != null && right.next.next != null) {
				mid = mid.next;
				right = right.next.next;
			}
			right = mid.next;
			mid.next = null;
			mergeLR(head, right);
		}
		private void mergeLR(ListNode left, ListNode right) {
			ListNode next = null;
			while (left.next != null) {
				next = right.next;
				right.next = left.next;
				left.next = right;
				left = right.next;
				right = next;
			}
			left.next = right;
		}
	}
}
