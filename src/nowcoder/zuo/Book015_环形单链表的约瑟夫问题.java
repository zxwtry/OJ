package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

/*
 * 	给一个环形单向链表(头节点head)和报数的值m
 * 	返回最后生存下来的节点，且这个节点自己组成环形单向链表，其他节点都删除
 */

public class Book015_环形单链表的约瑟夫问题 {
	public static void main(String[] args) {
//		testStandardSolution();
		testFastSolution();
	}
	static void testFastSolution() {

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
		int m = 1;
		FastSolution s = new FastSolution();
		ListNode ansMy= s.josephusKill(headMy, m);
		ListNode headBook = tools.ListNode辅助.A_一维生成器(arr);
		ListNode travelBook = headBook;
		while (travelBook.next != null) {
			travelBook = travelBook.next;
		}
		travelBook.next = headBook;
		StandardSolution ss = new StandardSolution();
		ListNode ansBook = ss.josephusKillBook(headBook, m);
		System.out.println(ansMy.val == ansBook.val);
	
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
		int m = 1;
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
			if (m == 1) {
				ListNode travel = head;
				while (travel.next != head) {
					travel = travel.next;
				}
				travel.next = travel;
				return travel;
			}
			int count = 1;
			while (head.next != head) {
				if (count == m - 1) {
					head.next = head.next.next;
					count = 0;
				}
				head = head.next;
				++ count;
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
			if (head == null || m < 1 || head.next == head) {
				return head;
			}
			ListNode cur = head.next;
			int length = 1;
			while (cur != head) {
				cur = cur.next;
				length ++;
			}
			int position = getLive(length, m);
			while (-- position != 0) {
				head = head.next;
			}
			head.next = head;
			return head;
		}

		private int getLive(int length, int m) {
			if (length == 1) {
				return 1;
			}
			return (getLive(length - 1, m) + m - 1) % length + 1;
		}
	}
}
