package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

public class Book017_将单向链表按某值划分成左边小_中间相等_右边大的形式 {
	public static void main(String[] args) {
//		testSolution();
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
}
