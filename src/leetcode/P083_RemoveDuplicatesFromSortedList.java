package leetcode;

/*
 * 	Given a sorted linked list, delete all duplicates 
 * 	such that each element appear only once.
	For example,
	Given 1->1->2, return 1->2.
	Given 1->1->2->3->3, return 1->2->3.
 */

import tools.ListNode辅助.*;
public class P083_RemoveDuplicatesFromSortedList {
	public static void main(String[] args) {
		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {});
		ListNode ans = new Solution().deleteDuplicates(head);
		tools.ListNode辅助.B_打印链表(ans);
	}
	/*
	 * 	1 ms
	 * 	17.15%
	 */
	static class Solution {
	    public ListNode deleteDuplicates(ListNode head) {
	    	if (head == null)	return head;
	    	ListNode cur = head.next, pre = head;
	    	while (cur != null) {
	    		if (cur.val == pre.val) {
	    			cur = cur.next;
	    			pre.next = cur;
	    		} else {
	    			pre = cur;
	    			cur = cur.next;
	    		}
	    	}
	        return head;
	    }
	}
}