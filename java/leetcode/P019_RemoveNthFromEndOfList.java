package leetcode;

import tools.ListNode辅助.ListNode;

public class P019_RemoveNthFromEndOfList {
	static class Solution {
		public ListNode removeNthFromEnd(ListNode h, int n) {
		    ListNode u = h, v = h;
		    int c = 0;
		    for (; c < n && u != null; c ++) u = u.next;
		    if (u == null && c < n)   return h;
		    if (u == null && c == n)  return h.next;
		    if (u.next == null) {
		        if (h.next == null) return null;
		        h.next = h.next.next;
		        return h;
		    }
		    u = u.next;
		    while (u != null) {
		        u = u.next;
		        v = v.next;
		    }
		    if (v != null && v.next != null) v.next = v.next.next;
		    return h;
		}
	}
}