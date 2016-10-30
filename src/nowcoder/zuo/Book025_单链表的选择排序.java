package nowcoder.zuo;

import com.sun.corba.se.impl.orbutil.graph.Node;

import tools.ListNode辅助.ListNode;

/*
 * 额外空间复杂度：O(1)	
 */

public class Book025_单链表的选择排序 {
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
