package leetcode;

import tools.ListNode辅助.ListNode;

public class P021_MergeTwoSortedLists {
	static class Solution {
	    public ListNode mergeTwoLists(ListNode l, ListNode r) {
	        if (l == null || r == null) return l == null ? r : l;
	        if (l.val > r.val) return mergeTwoLists(r, l);
	        ListNode p = l;
	        ListNode lc = l.next;
	        ListNode rc = r;
	        while (lc != null && rc != null) {
	            if (lc.val < rc.val) {
	                p.next = lc;
	                p = lc;
	                lc = lc.next;
	            } else {
	                p.next = rc;
	                p = rc;
	                rc = rc.next;
	            }
	        }
	        if (lc != null) p.next = lc;
	        if (rc != null) p.next = rc;
	        return l;
	    }
	}
}