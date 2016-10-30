package nowcoder.zuo;

import java.util.HashSet;

import tools.ListNode辅助.ListNode;

/*
 * 	1->2->3->3->4->4->2->1->1->null，删除重复节点之后为1->2->3->4->null	
 */

public class Book022_删除无序单链表里面重复出现的节点 {
	public static void main(String[] args) {
		
	}
	//时间复杂度O(N)，额外空间复杂度O(N)
	static class HashSetSolution {
		public void removeRep(ListNode head) {
			if (head == null) {
				return;
			}
			HashSet<Integer> set = new HashSet<>();
			ListNode pre = head;
			ListNode cur = head.next;
			set.add(head.val);
			while (cur != null) {
				if (set.contains(cur.val)) {
					pre.next = cur.next;
				} else {
					set.add(cur.val);
					pre = cur;
				}
				cur = cur.next;
			}
		}
	}
	//时间复杂度O(N^2)，额外空间复杂度O(1)
	static class SortSolution {
		public void removeRep(ListNode head) {
			ListNode cur = head;
			ListNode pre = null;
			ListNode next = null;
			while (cur != null) {
				pre = cur;
				next = cur.next;
				while (next != null) {
					if (cur.val == next.val) {
						pre.next = next.next;
					} else {
						pre = next;
					}
					next = next.next;
				}
			}
		}
	}
}
