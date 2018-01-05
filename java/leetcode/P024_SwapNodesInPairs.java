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
	static class Solution {
	    public ListNode swapPairs(ListNode h) {
	    	if (h == null || h.next == null)
	    		return h;
	    	ListNode ans = h.next;
	    	ListNode p = null;
	    	ListNode a = h, b = null;
	    	ListNode next = null;
	    	while (a != null) {
	    	    next = a.next == null ? null : a.next.next;
	    	    
	    	    b = a.next;
	    	    if (b != null) b.next = a;
	    	    if (p != null) p.next = b == null ? a : b;
	    	    
	    	    p = a;
	    	    a = next;
	    	}
	    	if (p != null) p.next = null;
	    	
	    	return ans;
	    }
	}
}
