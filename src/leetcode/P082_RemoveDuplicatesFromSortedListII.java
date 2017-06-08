package leetcode;

/*
 * 	Given a sorted linked list, delete all nodes that have duplicate numbers,
 *  leaving only distinct numbers from the original list.

	For example,
	Given 1->2->3->3->4->4->5, return 1->2->5.
	Given 1->1->1->2->3, return 2->3.
 */

import tools.ListNode辅助.*;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P082_RemoveDuplicatesFromSortedListII.java
 * @date        2017年6月8日 下午10:24:44
 * @details     Solution: AC
 */
public class P082_RemoveDuplicatesFromSortedListII {
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
