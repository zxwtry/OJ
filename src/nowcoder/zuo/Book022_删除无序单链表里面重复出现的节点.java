package nowcoder.zuo;

import java.util.HashSet;

import tools.ListNode辅助.ListNode;

/*
 * 	1->2->3->3->4->4->2->1->1->null，删除重复节点之后为1->2->3->4->null	
 */

public class Book022_删除无序单链表里面重复出现的节点 {
	public static void main(String[] args) {
		testHashMap();
	}
	static void testHashMap() {
		int min = 0;
		int max = 2;
		int n = 10;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		ListNode head = tools.ListNode辅助.A_一维生成器(arr);
		HashSetSolution hs = new HashSetSolution();
		System.out.println("原来的链表是：");
		int sizeOfLine = n + 100;
		tools.ListNode辅助.B_打印链表_一行(head, sizeOfLine);
		hs.removeRep(head);
		System.out.println("修正之后的链表是：");
		tools.ListNode辅助.B_打印链表_一行(head, sizeOfLine);
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
