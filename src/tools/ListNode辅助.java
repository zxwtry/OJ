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
	public static ListNode A_一维生成器(int[] nums) {
		ListNode pre = null, head = null;
		for (int i = nums.length - 1; i != -1; i --) {
			head = new ListNode(nums[i]);
			head.next = pre;
			pre = head;
		}
		return head;
	}
	public static ListNode A_一维生成器_从0_N_1(int N) {
		ListNode pre = null, head = null;
		for (int i = N - 1; i != -1; i --) {
			head = new ListNode(i);
			head.next = pre;
			pre = head;
		}
		return head;
	}
	public static ListNode A_随机生成器_最大长度N_范围min_max(int N, int min, int max) {
		int len = (int)(Math.random() * (N + 1));
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(len, min, max);
		return tools.ListNode辅助.A_一维生成器(arr);
	}
	public static void B_打印链表(ListNode head) {
		ListNode cur = head;
		while (cur != null) {
			System.out.println(cur.val);
			cur = cur.next;
		}
	}
	public static void B_打印链表_一行(ListNode head, int sizeOfLine) {
		ListNode cur = head;
		int count = 0;
		while (cur != null) {
			System.out.print(cur.val + "\t");
			cur = cur.next;
			count ++;
			if (count % sizeOfLine == 0 || cur == null) {
				System.out.println();
			}
		}
		
	}
	public static List<ListNode> C_二维生成器(int[][] nums) {
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
	public static ListNode[] D_二维生成器_数组版(int[][] nums) {
		ListNode[] ans = new ListNode[nums.length];
		for (int j = 0; j != nums.length; j ++) {
			ListNode pre = null, head = null;
			for (int i = nums[j].length - 1; i != -1; i --) {
				head = new ListNode(nums[j][i]);
				head.next = pre;
				pre = head;
			}
			ans[j] = head;
		}
		return ans;
	}
	public static class ListNode {
		public int val;
		public ListNode next;
		public ListNode(int val) {
			this.val = val;
		}
	}
}
