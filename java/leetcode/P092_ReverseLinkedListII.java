package leetcode;


/*
 * 	Reverse a linked list from position m to n. Do it in-place and in one-pass.

	For example:
	Given 1->2->3->4->5->NULL, m = 2 and n = 4,
	
	return 1->4->3->2->5->NULL.
	
	Note:
	Given m, n satisfy the following condition:
	1 ≤ m ≤ n ≤ length of list.
 */



import tools.ListNode辅助.ListNode;;

public class P092_ReverseLinkedListII {
	/*
	 * 	1 ms
	 */
	static class Solution {
	    public ListNode reverseBetween(ListNode head, int m, int n) {
	    	if (m >= n) {
	    		return head;
	    	}
	    	ListNode tra = head, one_last = null, two_first = null,
	    			two_last = null, three_first = null, save_tra_next = null;
	    	int count = 1;
	    	while (tra != null) {
	    		save_tra_next = tra.next;
	    		if (count == m - 1) {
	    			one_last = tra;
	    		} else if (count == m) {
	    			two_first = tra;
	    			two_last = tra;
	    		} else if (count == n + 1) {
	    			three_first = tra;
	    		} else if (count > m && count <= n) {
	    			tra.next = two_first;
	    			two_first = tra;
	    		}
	    		tra = save_tra_next;
	    		count ++;
	    	}
	    	if (one_last != null) {
	    		one_last.next = two_first;
	    	}
	    	if (two_last != null) {
	    		two_last.next = three_first;
	    	}
	    	if (one_last == null) {
	    		return two_first;
	    	}
	        return head;
	    }
	}
}
