package tools;

import java.util.LinkedList;
import java.util.List;

/*
 * 	生成List<ListNode>非常麻烦
 * 	这里就是一个通过数组去生成的方法。
 */

public class ListNode辅助 {
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
	public static void B_打印链表(ListNode head, int n, int placeHolderNumber) {
		ListNode cur = head;
		int i = 0;
		while (cur != null) {
            System.out.printf(String.format("%%%dd" + ((i % n == n - 1 || cur.next == null)
                    ? "\n" : ""), placeHolderNumber), cur.val);
            cur = cur.next;
            i ++;
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
		@Override
		public String toString() {
		    return "val: " + val + "   next: " + 
		(next == null ? "null" : String.valueOf(next.val));
		}
	}
}
