package tools;

import java.util.LinkedList;
import java.util.List;

/*
 * 	生成List<ListNode>非常麻烦
 * 	这里就是一个通过数组去生成的方法。
 */

public class ListNode辅助 {
	public static void main(String[] args) {
//		ListNode head = A_一维生成器(new int[] {1,2,3,4});
//		B_打印链表(head);
		List<ListNode> ans = C_二维生成器(new int[][] {
			{1, 2, 3, 4, 5},
			{6, 7, 8, 9, 10},
			{1, 3, 5, 7, 9},
			{2, 4, 6, 8, 10, 100},
			{100000}
		});
		for (int i = 0; i < ans.size(); i ++) {
			B_打印链表(ans.get(i));
			System.out.println("=======================");
		}
	}
	static ListNode A_一维生成器(int[] nums) {
		ListNode pre = null, head = null;
		for (int i = nums.length - 1; i != -1; i --) {
			head = new ListNode(nums[i]);
			head.next = pre;
			pre = head;
		}
		return head;
	}
	static void B_打印链表(ListNode head) {
		ListNode cur = head;
		while (cur != null) {
			System.out.println(cur.val);
			cur = cur.next;
		}
	}
	static List<ListNode> C_二维生成器(int[][] nums) {
		List<ListNode> ans = new LinkedList<ListNode>();
		for (int j = 0; j != nums.length; j ++) {
			ListNode pre = null, head = null;
			for (int i = nums[j].length - 1; i != -1; i --) {
				head = new ListNode(nums[j][i]);
				head.next = pre;
				pre = head;
			}
			ans.add(head);
		}
		return ans;
	}
	static class ListNode {
		int val;
		ListNode next;
		public ListNode(int val) {
			this.val = val;
		}
	}
}
