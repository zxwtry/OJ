package leetcode;

/*
 * 25. Reverse Nodes in k-Group  QuestionEditorial Solution  My Submissions
	Total Accepted: 67154
	Total Submissions: 235170
	Difficulty: Hard
	Given a linked list, reverse the nodes of a linked list k at a time and
	 return its modified list.

	If the number of nodes is not a multiple of k then left-out nodes in the end 
	should remain as it is.

	You may not alter the values in the nodes, only nodes itself may be changed.

	Only constant memory is allowed.

	For example,
	Given this linked list: 1->2->3->4->5

	For k = 2, you should return: 2->1->4->3->5

	For k = 3, you should return: 3->2->1->4->5
 */

import tools.ListNode辅助.ListNode;;

public class P025_ReverseNodesInKGroup {
	static class Solution1 {
	    public ListNode reverseKGroup(ListNode h, int k) {
	        if (k < 2) return h;
	        ListNode ans = null;
	        ListNode b = null, t = h;
	        ListNode p = null;
	        ListNode head = null, tail = null;
	        while (true) {
	            int i = 0; 
	            head = tail = null;
	            for (; i < k && t != null; i ++) {
	                b = t.next;

	                t.next = head;
	                head = t;
	                if (tail == null) tail = t;
	                
	                t = b;
	            }
	            
	            if (i == k || head == null) {
                    if (p != null) {
                        p.next = head;
                    }
                    if (ans == null) ans = head;
                    p = tail;
	            } else {
	                ListNode nh = null, nt = null, ns = null, tt = head;
	                while (true) {
	                    ns = tt.next;
	                    
	                    tt.next = nh;
	                    nh = tt;
	                    if (nt == null) nt = tt;
	                    if (tt == tail) break;
	                    
	                    tt = ns;
	                }
	                if (p != null) {
	                    p.next = nh;
	                }
	                if (ans == null) ans = nh;
	                p = nt;        //虽然没用
	            }
	            if (i != k) break;
	        }
	        return ans;
	    }
	}
}
