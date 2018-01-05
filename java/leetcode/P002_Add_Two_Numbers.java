package leetcode;

import tools.ListNode辅助.ListNode;

/*

You are given two linked lists representing two non-negative numbers.
 The digits are stored in reverse order and each of their nodes contain 
 a single digit. Add the two numbers and return it as a linked list.

Input: (2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 0 -> 8

中文意思，每位存储在一个Node里面，一个数就是一个链表。
把两个链表相加一下。

*/

public class P002_Add_Two_Numbers {
	public static void main(String[] args) {
	    ListNode l1 = tools.ListNode辅助.A_一维生成器(new int[] {9});
	    ListNode l2 = tools.ListNode辅助.A_一维生成器(new int[] {1, 8});
		ListNode ans = new Solution2().addTwoNumbers(l1, l2);
		tools.ListNode辅助.B_打印链表(ans, 100, 5);
	}
	/*
	 * 	31.63%
	 */
	static class Solution1 {
	    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	        ListNode ans = null, cur = null;
	        int carry = 0;
	        while (l1 != null || l2 != null) {
	        	if (l1 != null) {
	        		carry += l1.val;
	        		l1 = l1.next;
	        	}
	        	if (l2 != null) {
	        		carry += l2.val;
	        		l2 = l2.next;
	        	}
	        	ListNode node = new ListNode(carry % 10);
	        	carry = carry / 10;
	        	if (ans == null) {
	        		ans = node;
	        		cur = node;
	        	} else {
	        		cur.next = node;
	        		cur = cur.next;
	        	}
	        }
	        if (carry != 0)
	        	cur.next = new ListNode(carry);
	        return ans;
	    }
	}
	/*
	 * 	 31.63%
	 */
	static class Solution2 {
	    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
	    	ListNode head = l1, current = l1;
	    	int assist = 0;
	    	while (l1 != null) {
	    		assist += l1.val;
	    		if (l2 != null) {
	    			assist += l2.val;
	    			l2 = l2.next;
	    		}
	    		l1.val = assist % 10;
	    		assist = assist / 10;
	    		current = l1;
	    		l1 = l1.next;
	    	}
	    	if (l2 != null) {
		    	current.next = l2;
		    	while (l2 != null) {
		    		if (assist == 0)
		    			break;
		    		assist += l2.val;
		    		l2.val = assist % 10;
		    		assist = assist / 10;
		    		current = l2;
		    		l2 = l2.next;
		    	}
	    	}
	    	if (assist != 0)
	    		current.next = new ListNode(assist);
	    	return head;
	    }
	}
}
