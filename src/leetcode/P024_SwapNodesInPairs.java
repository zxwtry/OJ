package leetcode;

/*
 * 	4. Swap Nodes in Pairs  QuestionEditorial Solution  My Submissions
	Total Accepted: 115287
	Total Submissions: 318220
	Difficulty: Easy
	Given a linked list, swap every two adjacent nodes and 
	return its head.

	For example,
	Given 1->2->3->4, you should return the list as 2->1->4->3.

	Your algorithm should use only constant space. 
	You may not modify the values in the list, 
	only nodes itself can be changed.
 */

import tools.ListNode辅助.ListNode;

public class P024_SwapNodesInPairs {
	public static void main(String[] args) {
		ListNode input = tools.ListNode辅助.A_一维生成器(new int[] {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16});
		tools.ListNode辅助.B_打印链表(new Solution1().swapPairs(input));
	}
	/*
	 * 	0 ms
	 * 	6.30%
	 */
	static  class Solution1 {
	    public ListNode swapPairs(ListNode head) {
	    	if (head == null || head.next == null)
	    		return head;
	        ListNode pre = head, cur = pre.next.next, tmp = null, ans = head.next;
	        pre.next.next = head;
	        head.next = cur;
	        boolean isOdd = true;
	        while (cur != null) {
	        	if (! isOdd) {
	        		tmp = cur.next;
	        		cur.next = pre.next;
	        		pre.next.next = tmp;
	        		pre.next = cur;
	        		pre = cur.next;
	        		cur = pre.next;
	        	} else {
		        	cur = cur.next;
	        	}
	        	isOdd = ! isOdd;
	        }
	        return ans;
	    }
	}
}
