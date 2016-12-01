package nowcoder.zuo;

import tools.ListNode辅助.ListNode;

/*
 * 额外空间复杂度：O(1)	
 */

public class Book025_单链表的选择排序 {
	public static void main(String[] args) {
		test();
	}
	private static void test() {
		int N = 100;
		int min = 0;
		int max = 10000;
		ListNode list = tools.ListNode辅助.A_随机生成器_最大长度N_范围min_max(N, min, max);
		SelectSolution s = new SelectSolution();
		ListNode ans = s.sort(list);
		tools.ListNode辅助.B_打印链表(ans);
	}
	static class SelectSolution {
		public ListNode sort(ListNode head) {
			ListNode tail = null;		//排序部分尾部
			ListNode cur = head;		//未排序部分的头部
			ListNode smallPre = null;	//最小节点的前一个节点
			ListNode small = null;		//最小节点
			while (cur != null) {
				small = cur;
				smallPre = getSmallestPreNode(cur);
				if (smallPre != null) {
					small = smallPre.next;
					smallPre.next = small.next;
				}
				cur = cur == small ? cur.next : cur;
				if (tail == null) {
					head = small;
				} else {
					tail.next = small;
				}
				tail = small;
			}
			return head;
		}
		private ListNode getSmallestPreNode(ListNode head) {
			ListNode smallPre = null;
			ListNode small = head;
			ListNode pre = head;
			ListNode cur = head.next;
			while (cur != null) {
				if (cur.val < small.val) {
					smallPre = pre;
					small = cur;
				}
				pre = cur;
				cur = cur.next;
			}
			return smallPre;
		}
	}
}
