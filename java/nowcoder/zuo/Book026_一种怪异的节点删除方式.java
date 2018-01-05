package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

/*
 * 	给定一个链表的节点node，但不给定整个链表的头结点。
 * 	如何在链表中删除node。
 * 	空间O(1)
 * 	如果节点是比较复杂的结构，这样删除会带来非常多的问题。
 */

public class Book026_一种怪异的节点删除方式 {
	public static void main(String[] args) {
		test();
	}
	static void test() {
		int len = (int)(Math.random() * 100);
		int[] arr = new int[len];
		for (int i = 0; i < len; i ++) {
			arr[i] = i;
		}
		ListNode head = tools.ListNode辅助.A_一维生成器(arr);
		int select = (int)(Math.random() * len);
		ListNode toBeDeleted = head;
		while (head.val != select) {
			toBeDeleted = toBeDeleted.next;
		}
		MySolution ms = new MySolution();
		ms.solve(toBeDeleted);
		System.out.println("len is " + len);
		int sizeOfLine = len + 100;
		tools.ListNode辅助.B_打印链表(head, sizeOfLine, 5);
	}
	//无法完成
	static class MySolution {
		public void solve(ListNode toBeDeleted) {
			if (toBeDeleted.next != null) {
				toBeDeleted.val = toBeDeleted.next.val;
				toBeDeleted.next = toBeDeleted.next.next;
			} else {
				//这里是无法删除的。
			}
		}
	}
	//一样无法完成，作者就是告诉大家，这样可以，但是不要用。
	static class BookSolution {
		public void solve(ListNode toBeDeleted) {
			if (toBeDeleted == null) {
				return;
			}
			ListNode next = toBeDeleted.next;
			if (next == null) {
				throw new RuntimeException("不能删除最后一个节点");
			}
			toBeDeleted.val = next.val;
			toBeDeleted.next = next.next;
		}
	}
}
