package leetcode;

import tools.ListNode辅助.ListNode;

public class P021_MergeTwoSortedLists {
	static class Solution {
	    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	    	if (l1 == null || l2 == null)
	    		return l1 == null ? l2 : l1;
	    	if (l1.val > l2.val)
	    		return mergeTwoLists(l2, l1);
	    	ListNode pre = l1, c1 = l1.next, c2 = l2;
	    	while (true) {
	    		while (c1 != null && c1.val <= c2.val) {
	    			pre = c1;
	    			c1 = c1.next;
	    		}
	    		pre.next = c2;
	    		if (c1 == null)
	    			break;
	    		while (c2 != null && c2.val <= c1.val) {
	    			pre = c2;
	    			c2 = c2.next;
	    		}
	    		pre.next = c1;
	    		if (c2 == null)
	    			break;
	    	}
	    	return l1;
	    }
	}
}