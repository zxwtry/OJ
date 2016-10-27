package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

public class Book020_两个单链表相交的一系列问题 {
	public static void main(String[] args) {
		
	}
	/*
	 * 	给定两个单链表的头结点head1和head2，
	 * 	这两个链表可能相交，也可能不相交。
	 * 	如果两个链表相交，返回相交的第一个节点
	 * 	如果两个链表不相交，返回null
	 * 	要求：时间O(M+N)，空间复杂度O(1)
	 */
	/*
	 * 	将问题分解成三个小问题
	 * 		1,	如何判断一个链表是否有环，如果有返回第一个进入环的节点，没有返回null
	 * 		2,	如何判断两个无环链表是否相交，相交则返回第一个相交的节点，不相交返回null
	 * 		3,	如何判断两个有环链表是否相交，相交则返回第一个相交节点，不相交返回null
	 */
	static class Solution {
		/*
		 * 	1,	如何判断一个链表是否有环，如果有返回第一个进入环的节点，没有返回null
		 * 	方法：	快指针，一次两步
		 * 			慢指针，一次一步
		 */
		public ListNode getLoopNode(ListNode head) {
			if (head == null || head.next == null || head.next.next == null) {
				return null;
			}
			ListNode slowNode = head.next;
			ListNode fastNode = head.next.next;
			while (slowNode != fastNode) {
				if (fastNode.next == null || fastNode.next.next == null) {
					return null;
				}
				slowNode = slowNode.next;
				fastNode = fastNode.next.next;
			}
			fastNode = head;
			while (slowNode != fastNode) {
				slowNode = slowNode.next;
				fastNode = fastNode.next;
			}
			return slowNode;
		}
		/*
		 * 	2,	如何判断两个无环链表是否相交，相交则返回第一个相交的节点，不相交返回null
		 * 		
		 */
		public ListNode noLoop(ListNode head1, ListNode head2) {
			int len1 = 0, len2 = 0;
			ListNode travel1 = head1, travel2 = head2;
			while (travel1 != null) {
				len1 ++;
				travel1 = travel1.next;
			}
			while (travel2 != null) {
				len2 ++;
				travel2 = travel2.next;
			}
			if (len1 > len2) {
				return noLoop(head1, len1, head2, len2);
			} else {
				return noLoop(head2, len2, head1, len1);
			}
		}
		private ListNode noLoop(ListNode head1, int len1, ListNode head2, int len2) {
			int cut = len1 - len2;
			while (cut != 0) {
				head1 = head1.next;
				cut --;
			}
			while (head1 != head2) {
				head1 = head1.next;
				head2 = head2.next;
			}
			return head1;
		}
		/*
		 * 	3,	如何判断两个有环链表是否相交，相交则返回第一个相交节点，不相交返回null
		 * 	有环有三种情况：
		 * 		a,	两个链表先相交，相交之后再共同成环	
		 * 		b,	两个链表单独成环，就没有相交
		 * 		c,	两个链表先走进环，再在环上相交。
		 */
		public ListNode bothLoop(ListNode head1, ListNode head2) {
			
		}
	}
}
