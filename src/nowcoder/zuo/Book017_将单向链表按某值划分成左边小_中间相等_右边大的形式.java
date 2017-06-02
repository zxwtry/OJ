package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

public class Book017_将单向链表按某值划分成左边小_中间相等_右边大的形式 {
	public static void main(String[] args) {
//		testSolution();
		testAdvanSolution();
	}
	static void testAdvanSolution() {
		int n = (int) (Math.random() * 30);
		int min = 0;
		int max = n * n * n + 1;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		tools.Utils.printArray(arr, 100, 5);
		AdvanceSolution s = new AdvanceSolution();
		int pivot = max / 2;
		ListNode head = tools.ListNode辅助.A_一维生成器(arr);
		ListNode ans = s.listPartition1(head, pivot);
		ListNode ansSave = ans;
		int value = -2;
		boolean isTrue = true;
		while (ans != null && isTrue) {
			int newValue = getValue(ans.val, pivot);
			if (value > newValue) {
				isTrue = false;
			} else {
				value = newValue;
				ans = ans.next;
			}
		}
		System.out.println("pivot : " + pivot);
		tools.ListNode辅助.B_打印链表_一行(ansSave, 100);
		System.out.println(isTrue);
	}
	static void testSolution() {
		int n = (int) (Math.random() * 10000);
		int min = 0;
		int max = n * n * n + 1;
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(n, min, max);
		Solution s = new Solution();
		int pivot = max / 2;
		ListNode head = tools.ListNode辅助.A_一维生成器(arr);
		ListNode ans = s.listPartition1(head, pivot);
		int value = -2;
		boolean isTrue = true;
		while (ans != null && isTrue) {
			int newValue = getValue(ans.val, pivot);
			if (value > newValue) {
				isTrue = false;
			} else {
				value = newValue;
				ans = ans.next;
			}
		}
		System.out.println(isTrue);
	}
	static int getValue(int val, int pivot) {
		if (val < pivot) {
			return -1;
		} else if (val == pivot) {
			return 0;
		} else {
			return 1;
		}
	}
	/*
	 * 	假设一个链表9->0->4->5->1，pivot=3
	 * 	将单向链表按某值划分成左边小_中间相等_右边大的形式
	 * 	答案可以是：
	 * 		0->1->4->5->9
	 * 	也可以是：
	 * 		1->0->4->5->9
	 * 	还有很多答案，只要满足要求就行
	 * 	时间O(N)  空间O(N)
	 */
	static class Solution {
		public ListNode listPartition1(ListNode head, int pivot) {
			if (null == head) {
				return head;
			}
			ListNode cur = head;
			int i = 0;
			while (cur != null) {
				i ++;
				cur = cur.next;
			}
			ListNode[] nodeArr = new ListNode[i];
			cur = head;
			for (i = 0; i != nodeArr.length; i ++) {
				nodeArr[i] = cur;
				cur = cur.next;
			}
			arrPartition(nodeArr, pivot);
			for (i = 1; i != nodeArr.length; i ++) {
				nodeArr[i - 1].next = nodeArr[i];
			}
			nodeArr[i - 1].next = null;
			return nodeArr[0];
		}
		private void arrPartition(ListNode[] nodeArr, int pivot) {
			int small = -1;
			int big = nodeArr.length;
			int index = 0;
			while (index != big) {
				if (nodeArr[index].val < pivot) {
					swap(nodeArr, ++ small, index ++);
				} else if (nodeArr[index].val == pivot) {
					index ++;
				} else {
					swap(nodeArr, -- big, index);
				}
			}
		}
		private void swap(ListNode[] nodeArr, int i, int j) {
			ListNode temp = nodeArr[i];
			nodeArr[i] = nodeArr[j];
			nodeArr[j] = temp;
		}
	}
	/*
	 * 	在Solution的基础上，增加如下两个要求：
	 * 		*	在左、中、右三个部分的内部也做顺序要求，
	 * 			要求每部分里的节点从左到右的顺序与原链表中节点的先后次序一致。
	 * 		*	时间O(N) 空间O(1)
	 * 	假设一个链表9->0->4->5->1，pivot=3
	 * 	现在答案只有一个：
	 * 		0->1->9->4->5
	 */
	static class AdvanceSolution {
		public ListNode listPartition1(ListNode head, int pivot) {
			ListNode sH = null;
			ListNode sT = null;
			ListNode eH = null;
			ListNode eT = null;
			ListNode bH = null;
			ListNode bT = null;
			ListNode next = null;
			while (head != null) {
				next = head.next;
				head.next = null;
				if (head.val < pivot) {
					if (sH == null) {
						sH = head;
						sT = head;
					} else {
						sT.next = head;
						sT = head;
					}
				} else if (head.val == pivot) {
					if (eH == null) {
						eH = head;
						eT = head;
					} else {
						eT.next = head;
						eT = head;
					}
				} else {
					if (bH == null) {
						bH = head;
						bT = head;
					} else {
						bT.next = head;
						bT = head;
					}
				}
				head = next;
			}
			//小的和相等的重新连接
			if (sT != null) {
				sT.next = eH;
				eT = eT == null ? sT : eT;
			}
			//所有的重新连接
			if (eT != null) {
				eT.next = bH;
			}
			return sH != null ? sH : (eH != null ? eH : bH);
		}
	}
}
