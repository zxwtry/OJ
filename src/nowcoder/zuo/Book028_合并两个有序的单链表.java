package nowcoder.zuo;

import java.util.Arrays;

import tools.ListNode辅助.ListNode;

public class Book028_合并两个有序的单链表 {
	public static void mai(String[] args) {
		int n = 100;
		int min = 0;
		int max = 10000;
		int[] arr1 = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
		int[] arr2 = tools.Random随机生成器.A_生成一个不重复随机数据(n, min, max);
		Arrays.sort(arr1);
		Arrays.sort(arr2);
		ListNode head1 = tools.ListNode辅助.A_一维生成器(arr1);
		ListNode head2 = tools.ListNode辅助.A_一维生成器(arr2);
		tools.Utils.printArray(arr1, n + 2);
		tools.Utils.printArray(arr2, n + 2);
		MergeSolution ms = new MergeSolution();
		ListNode head = ms.merge(head1, head2);
		tools.ListNode辅助.B_打印链表_一行(head, n * 2 + 100);
	}
	static class MergeSolution {
		public ListNode merge(ListNode head1, ListNode head2) {
			if (head1 == null || head2 == null) {
				return head1 == null ? head2 : head1;
			}
			ListNode head = head1.val < head2.val ? head1 : head2;
			ListNode cur1 = head == head1 ? head1 : head2;
			ListNode cur2 = head == head1 ? head2 : head1;
			ListNode pre = null;
			ListNode next = null;
			while (cur1 != null && cur2 != null) {
				if (cur1.val <= cur2.val) {
					pre = cur1;
					cur1 = cur1.next;
				} else {
					next = cur2.next;
					pre.next = cur2;
					cur2.next = cur1;
					pre = cur2;
					cur2 = next;
				}
			}
			pre.next = cur1 == null ? cur2 : cur1;
			return head;
		}
	}
	static class MySolution {
		public ListNode merge(ListNode head1, ListNode head2) {
			if (head1 == null || head2 == null) {
				return head1 == null ? head2 : head1;
			}
			if (head1.val > head2.val) {
				return merge(head2, head1);
			}
			ListNode cur1 = head1;
			ListNode cur2 = head2;
			ListNode pre = null;
			ListNode next = null;
			while (cur1 != null && cur2 != null) {
				if (cur1.val <= cur2.val) {
					pre = cur1;
					cur1 = cur1.next;
				} else {
					next = cur2.next;
					pre.next = cur2;
					cur2.next = cur1;
					pre = cur2;
					cur2 = next;
				}
			}
			pre.next = cur1 == null ? cur2 : cur1;
			return head1;
		}
	}
}
