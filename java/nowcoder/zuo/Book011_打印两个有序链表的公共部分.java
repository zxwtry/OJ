package nowcoder.zuo;

import java.util.Arrays;

import tools.ListNode辅助.ListNode;

public class Book011_打印两个有序链表的公共部分 {
	public static void main(String[] args) {
		test();
	}
	static void test() {
		int[] arr1 = tools.Random随机生成器.A_生成一个随机数据(100, 0, 1000);
		Arrays.sort(arr1);
		int[] arr2 = tools.Random随机生成器.A_生成一个随机数据(100, - 1000, -2);
		Arrays.sort(arr2);
		int[] arr3 = tools.Random随机生成器.A_生成一个随机数据(10, 2000, 3000);
		Arrays.sort(arr3);
		ListNode head1 = tools.ListNode辅助.A_一维生成器(arr1);
		ListNode head2 = tools.ListNode辅助.A_一维生成器(arr2);
		ListNode head3 = tools.ListNode辅助.A_一维生成器(arr3);
		ListNode tail1 = head1, tail2 = head2;
		while (tail1.next != null) {
			tail1 = tail1.next;
		}
		while (tail2.next != null) {
			tail2 = tail2.next;
		}
		tail1.next = head3;
		tail2.next = head3;
		Solution s = new Solution();
		s.printCommonPart(head1, head2);
		for (int i = 0; i < arr3.length; i ++) {
			System.out.print(arr3[i] + " ");
		}
		System.out.println();
	}
	static class Solution {
		public void printCommonPart(ListNode head1, ListNode head2) {
			while (head1 != null && head2 != null) {
				if (head1.val < head2.val) {
					head1 = head1.next;
				} else if (head1.val > head2.val) {
					head2 = head2.next;
				} else {
					System.out.print(head1.val + " ");
					head1 = head1.next;
					head2 = head2.next;
				}
			}
			System.out.println();
		}
	}
}
