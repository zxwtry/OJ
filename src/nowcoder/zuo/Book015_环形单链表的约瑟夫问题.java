package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

/*
 * 	给一个环形单向链表(头节点head)和报数的值m
 * 	返回最后生存下来的节点，且这个节点自己组成环形单向链表，其他节点都删除
 */

public class Book015_环形单链表的约瑟夫问题 {
	public static void main(String[] args) {
		testStandardSolution();
	}
	static void testStandardSolution() {
		int[] arr = new int[12312];
		for (int i = 0; i < arr.length; i ++) {
			arr[i] = i;
		}
		ListNode headMy = tools.ListNode辅助.A_一维生成器(arr);
		ListNode travelMy = headMy;
		while (travelMy.next != null) {
			travelMy = travelMy.next;
		}
		travelMy.next = headMy;
		int m = 3;
		StandardSolution s = new StandardSolution();
		ListNode ansMy= s.josephusKill(headMy, m);
		ListNode headBook = tools.ListNode辅助.A_一维生成器(arr);
		ListNode travelBook = headBook;
		while (travelBook.next != null) {
			travelBook = travelBook.next;
		}
		travelBook.next = headBook;
		ListNode ansBook = s.josephusKillBook(headBook, m);
		System.out.println(ansMy.val == ansBook.val);
	}
	static class StandardSolution {
		public ListNode josephusKill(ListNode head, int m) {
			if (head == null || m < 1) {
				return head;
			}
			int count = 0;
			while (head.next != head) {
				++ count;
				if (count == m - 1) {
					head.next = head.next.next;
					count = 0;
				}
				head = head.next;
			}
			return head;
		}
		public ListNode josephusKillBook(ListNode head, int m) {
			if (head == null || m < 1) {
				return head;
			}
			ListNode last = head;
			while (last.next != head) {
				last = last.next;
			}
			int count = 0;
			while (head != last) {
				if (++ count == m) {
					last.next = head.next;
					count = 0;
				} else {
					last = last.next;
				}
				head = last.next;
			}
			return head;
		}
	}
	static class FastSolution {
		public ListNode josephusKill(ListNode head, int m) {
			return head;
		}
	}
}
