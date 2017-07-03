package nowcoder.zuo;

import tools.DoubleNode辅助.DoubleNode;
import tools.ListNode辅助.ListNode;

public class Book012_在单链表和双链表中删除倒数第K个节点 {
	public static void main(String[] args) {
		testSingle();
		testDouble();
	}
	static void testDouble() {
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(10, 0, 100);
		DoubleNode head = tools.DoubleNode辅助.A_双向链表生成器(arr);
		tools.DoubleNode辅助.B_打印双向链表(head);
		SolutionDoubleList sdl = new SolutionDoubleList();
		int lastKth = 2;
		head = sdl.removeLastKthNode(head, lastKth);
		tools.DoubleNode辅助.B_打印双向链表(head);
	}
	static void testSingle() {
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(10, 0, 100);
		ListNode head = tools.ListNode辅助.A_一维生成器(arr);
		tools.ListNode辅助.B_打印链表(head, 100, 5);
		SolutionSingleList ssl = new SolutionSingleList();
		int lastKth = 3;
		head = ssl.removeLastKthNode(head, lastKth);
		tools.ListNode辅助.B_打印链表(head, 100, 5);
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
	static class SolutionDoubleList {
		public DoubleNode removeLastKthNode(DoubleNode head, int lastKth) {
			if (head == null || lastKth < 1) {
				return head;
			}
			DoubleNode cur = head;
			while (cur != null) {
				lastKth --;
				cur = cur.next;
			}
			if (lastKth == 0) {
				head = head.next;
				head.last = null;
			}
			if (lastKth < 0) {
				cur = head;
				while (++ lastKth != 0) {
					cur = cur.next;
				}
				DoubleNode newNext = cur.next.next;
				cur.next = newNext;
				if (newNext != null) {
					newNext.last = cur;
				}
			}
			return head;
		}
	}
}
