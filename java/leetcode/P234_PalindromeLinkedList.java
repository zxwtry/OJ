package leetcode;

import tools.ListNode辅助.ListNode;

/**
 * 	Given a singly linked list, determine if it is a palindrome.

	Follow up:
	Could you do it in O(n) time and O(1) space?
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P234_PalindromeLinkedList.java
 * @type        P234_PalindromeLinkedList
 * @date        2016年12月9日 下午10:13:29
 * @details     Solution: AC 1ms 89.64%
 */
public class P234_PalindromeLinkedList {
	static class Solution {
	    public boolean isPalindrome(ListNode head) {
	    	if (head == null) return true;
	        int len = 0;
	        ListNode t = head;
	        while (t != null) {
	        	len ++;
	        	t = t.next;
	        }
	        t = head;
	        for (int i = len / 2; i > 0; i --) t = t.next;
	        ListNode n = t.next, ts, ns;
	        while (n != null) {
	        	ts = n;
	        	ns = n.next;
	        	n.next = t;
	        	t = ts;
	        	n = ns;
	        }
	        n = t;
	        t = head;
	        for (int i = len/2; i > 0; i --) {
	        	if (t.val != n.val) return false;
	        	t = t.next;
	        	n = n.next;
	        }
	        return true;
	    }
	}
}
