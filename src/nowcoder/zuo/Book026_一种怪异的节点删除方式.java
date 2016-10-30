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
