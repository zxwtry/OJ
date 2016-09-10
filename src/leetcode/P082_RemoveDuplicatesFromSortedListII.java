package leetcode;

/*
 * 	Given a sorted linked list, delete all nodes that have duplicate numbers,
 *  leaving only distinct numbers from the original list.

	For example,
	Given 1->2->3->3->4->4->5, return 1->2->5.
	Given 1->1->1->2->3, return 2->3.
 */

import tools.ListNode辅助.*;

public class P082_RemoveDuplicatesFromSortedListII {
	public static void main(String[] args) {
//		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {1, 2, 3, 3, 4, 4, 5});
		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {4});
		ListNode ans = new Solution().deleteDuplicates(head);
		tools.ListNode辅助.B_打印链表(ans);
	}
	/*
	 * 	一次AC
	 * 	之前一直晕晕沉沉，清醒之后，好好分析，真的不难。
	 * 	1 ms
	 */
	static class Solution {
	    public ListNode deleteDuplicates(ListNode head) {
	    	if (head == null || head.next == null)
	    		return head;
	    	ListNode newHead = new ListNode(head.val == 0 ? -1 : 0);
	    	newHead.next = head;
	    	ListNode pre = newHead, cur = head.next;
	    	while (cur != null) {
	    		if (pre.next.val != cur.val) {
	    			if (pre.next.next == cur) {
	    				pre = pre.next;
	    				cur = cur.next;
	    			} else {
	    				pre.next = cur;
	    			}
	    		} else {
	    			cur = cur.next;
	    		}
	    	}
	    	if (pre.next.next != null)
	    		pre.next = null;
	        return newHead.next;
	    }
	}
}
