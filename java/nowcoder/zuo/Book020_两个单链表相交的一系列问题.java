package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

public class Book020_两个单链表相交的一系列问题 {
	public static void main(String[] args) {
//		test1没有环_不相交();
//		test2没有环_相交();
//		test3有环_先相交后成环();
//		test4有环_不相交();
//		test5有环_环上相交();
	}
	static void test5有环_环上相交() {
		int n = 3;
		int min = 0;
		int max = 1000;
		boolean isAllTrue = true;
		for (n = 2; n < 1000; n ++) {
			ListNode head1 = tools.ListNode辅助.A_随机生成器_最大长度N_范围min_max(n, min, max);
			ListNode head2 = tools.ListNode辅助.A_随机生成器_最大长度N_范围min_max(n, min, max);
			ListNode circle = tools.ListNode辅助.A_一维生成器(tools.Random随机生成器.A_生成一个随机数据(n, min, max));
			ListNode tailCircle = circle;
			while (tailCircle.next != null) {
				tailCircle = tailCircle.next;
			}
			tailCircle.next = circle;
			ListNode loop1 = circle, loop2 = tailCircle;
			ListNode tail1 = head1, tail2 = head2;
			if (tail1 == null) {
				head1 = loop1;
			} else {
				while (tail1.next != null) {
					tail1 = tail1.next;
				}
				tail1.next = loop1;
			}
			if (tail2 == null) {
				head2 = loop2;
			} else {
				while (tail2 != null && tail2.next != null) {
					tail2 = tail2.next;
				}
				tail2.next = loop2;
			}
			Solution s = new Solution();
			ListNode intersectNode = s.getIntersectNode(head1, head2);
			System.out.println(intersectNode.val == loop1.val || intersectNode.val == loop2.val);
			isAllTrue &= intersectNode.val == loop1.val || intersectNode.val == loop2.val;
		}
		System.out.println(isAllTrue);
	}
	static void test4有环_不相交() {
		int n = 3;
		int min = 0;
		int max = 1000;
		boolean isAllTrue = true;
		for (n = 1; n < 1000; n ++) {
			ListNode head1 = tools.ListNode辅助.A_随机生成器_最大长度N_范围min_max(n, min, max);
			ListNode head2 = tools.ListNode辅助.A_随机生成器_最大长度N_范围min_max(n, min, max);
			ListNode circle1 = tools.ListNode辅助.A_一维生成器(tools.Random随机生成器.A_生成一个随机数据(n, min, max));
			ListNode circle2 = tools.ListNode辅助.A_一维生成器(tools.Random随机生成器.A_生成一个随机数据(n, min, max));
			ListNode tailCircle1 = circle1;
			while (tailCircle1.next != null) {
				tailCircle1 = tailCircle1.next;
			}
			tailCircle1.next = circle1;
			ListNode tailCircle2 = circle2;
			while (tailCircle2.next != null) {
				tailCircle2 = tailCircle2.next;
			}
			tailCircle2.next = circle2;
			ListNode tail1 = head1, tail2 = head2;
			if (tail1 == null) {
				head1 = circle1;
			} else {
				while (tail1.next != null) {
					tail1 = tail1.next;
				}
				tail1.next = circle1;
			}
			if (tail2 == null) {
				head2 = circle2;
			} else {
				while (tail2.next != null) {
					tail2 = tail2.next;
				}
				tail2.next = circle2;
			}
			Solution s = new Solution();
			ListNode intersectNode = s.getIntersectNode(head1, head2);
			System.out.println(intersectNode == null);
			isAllTrue &= intersectNode == null;
		}
		System.out.println(isAllTrue);
	}
	static void test3有环_先相交后成环() {
		int n = 3;
		int min = 0;
		int max = 1000;
		boolean isAllTrue = true;
		for (n = 1; n < 1000; n ++) {
			ListNode head1 = tools.ListNode辅助.A_随机生成器_最大长度N_范围min_max(n, min, max);
			ListNode head2 = tools.ListNode辅助.A_随机生成器_最大长度N_范围min_max(n, min, max);
			int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
			ListNode common = tools.ListNode辅助.A_一维生成器(arr);
			ListNode tail1 = head1, tail2 = head2;
			if (tail1 == null) {
				head1 = common;
			} else {
				while (tail1.next != null) {
					tail1 = tail1.next;
				}
				tail1.next = common;
			}
			if (tail2 == null) {
				head2 = common;
			} else {
				while (tail2 != null && tail2.next != null) {
					tail2 = tail2.next;
				}
				tail2.next = common;
			}
			ListNode circle = tools.ListNode辅助.A_一维生成器(tools.Random随机生成器.A_生成一个随机数据(n, min, max));
			ListNode tailCircle = circle;
			while (tailCircle.next != null) {
				tailCircle = tailCircle.next;
			}
			tailCircle.next = circle;
			ListNode tailCommon = common;
			while (tailCommon.next != null) {
				tailCommon = tailCommon.next;
			}
			tailCommon.next = circle;
			Solution s = new Solution();
			ListNode intersectNode = s.getIntersectNode(head1, head2);
			System.out.println(intersectNode.val == arr[0]);
			isAllTrue &= intersectNode.val == arr[0];
		}
		System.out.println(isAllTrue);
	}
	static void test2没有环_相交() {
		int n = 100;
		boolean isAllTrue = true;
		for (n = 1; n < 1000; n ++) {
			int min = 0;
			int max = 1000;
			ListNode head1 = tools.ListNode辅助.A_随机生成器_最大长度N_范围min_max(n, min, max);
			ListNode head2 = tools.ListNode辅助.A_随机生成器_最大长度N_范围min_max(n, min, max);
			int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
			ListNode common = tools.ListNode辅助.A_一维生成器(arr);
			ListNode tail1 = head1, tail2 = head2;
			if (tail1 == null) {
				head1 = common;
			} else {
				while (tail1.next != null) {
					tail1 = tail1.next;
				}
				tail1.next = common;
			}
			if (tail2 == null) {
				head2 = common;
			} else {
				while (tail2 != null && tail2.next != null) {
					tail2 = tail2.next;
				}
				tail2.next = common;
			}
			Solution s = new Solution();
			ListNode intersectNode = s.getIntersectNode(head1, head2);
			System.out.println(intersectNode.val == arr[0]);
			isAllTrue &= intersectNode.val == arr[0];
		}
		System.out.println(isAllTrue);
	}
	static void test1没有环_不相交() {
		int n = 100;
		int min = 0;
		int max = 1000;
		boolean isAllTrue = true;
		for (n = 1; n < 1000; n ++) {
			ListNode head1 = tools.ListNode辅助.A_随机生成器_最大长度N_范围min_max(n, min, max);
			ListNode head2 = tools.ListNode辅助.A_随机生成器_最大长度N_范围min_max(n, min, max);
			Solution s = new Solution();
			ListNode intersectNode = s.getIntersectNode(head1, head2);
			System.out.println(intersectNode == null);
			isAllTrue &= intersectNode == null;
		}
		System.out.println(isAllTrue);
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
		 * 		假设	链表1的第一个入环节点是loop1
		 * 			链表2的第一个入环节点是loop2
		 * 	有环有三种情况：
		 * 		a,	两个链表先相交，相交之后再共同成环	loop1 == loop2
		 * 		b,	两个链表单独成环，就没有相交		loop1 != loop2 && loop1走一圈没有遇见loop2
		 * 		c,	两个链表先走进环，再在环上相交		loop1 != loop2 && loop1走一圈遇见loop2
		 */
		public ListNode bothLoop(ListNode head1, ListNode loop1, ListNode head2, ListNode loop2) {
			ListNode cur1 = null, cur2 = null;
			ListNode intersectNode = null;
			if (loop1 == loop2) {
				cur1 = head1;
				cur2 = head2;
				int len1 = 0, len2 = 0;
				while (cur1 != loop1) {
					len1 ++;
					cur1 = cur1.next;
				}
				while (cur2 != loop2) {
					len2 ++;
					cur2 = cur2.next;
				}
				if (len1 > len2) {
					intersectNode = bothLoop(head1, len1, head2, len2);
				} else {
					intersectNode = bothLoop(head2, len2, head1, len1);
				}
			} else {
				cur1 = loop1.next;
				boolean isOneCircle = false;
				while (cur1 != loop1 && ! isOneCircle) {
					if (cur1 == loop2) {
						isOneCircle = true;
					} else {
						cur1 = cur1.next;
					}
				}
				intersectNode = isOneCircle ? loop1 : null;
			}
			return intersectNode;
		}
		private ListNode bothLoop(ListNode cur1, int len1, ListNode cur2, int len2) {
			int cut = len1 - len2;
			while (cut != 0) {
				cur1 = cur1.next;
				cut --;
			}
			while (cur1 != cur2) {
				cur1 = cur1.next;
				cur2 = cur2.next;
			}
			return cur1;
		}
		/*
		 * 	实现功能的主方法
		 */
		public ListNode getIntersectNode(ListNode head1, ListNode head2) {
			if (head1 == null || head2 == null) {
				return null;
			}
			ListNode loop1 = getLoopNode(head1);
			ListNode loop2 = getLoopNode(head2);
			if (loop1 == null && loop2 == null) {
				return noLoop(head1, head2);
			}
			if (loop1 != null && loop2 != null) {
				return bothLoop(head1, loop1, head2, loop2);
			}
			return null;
		}
	}
}
