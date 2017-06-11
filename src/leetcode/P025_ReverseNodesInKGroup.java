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
	/*
	 * 	花了很长时间
	 * 	代码写得非常乱，今后需要整理一下
	 * 	可能还有先全反转，对尾部二次反转的做法，可能更快
	 * 	1 ms
	 * 	4.84%
	 */
	static class Solution1 {
	    public ListNode reverseKGroup(ListNode head, int k) {
	    	if (head == null || head.next == null)
	    		return head;
	    	int count = 1;
	    	ListNode ans = head, pre = null, cur = null, next_pre = null, next_cur = null, rev_cur = null, rev_pre = null, temp = null;
	    	while (ans != null && count != k) {
	    		ans = ans.next;
	    		count ++;
	    	}
	    	cur = ans;
	    	if (ans == null)
	    		return head;
	    	while (cur != null) {
	    		if (count != k) {
	    			count ++;
	    			cur = cur.next;
	    		} else {
	    			if (pre == null) {
	    				next_pre = head;
	    				rev_cur = next_pre;
	    			} else {
	    				next_pre = pre.next;
	    				rev_cur = next_pre;
	    				pre.next = cur;
	    			}
	    			next_cur = cur.next;
	    			rev_pre = next_cur;
	    			while (rev_cur != next_cur) {
	    				temp = rev_cur.next;
	    				rev_cur.next = rev_pre;
	    				rev_pre = rev_cur;
	    				if (rev_cur == cur)
	    					break;
	    				rev_cur = temp;
	    			}
	    			cur = next_cur;
	    			pre = next_pre;
	    			count = 1;
	    		}
	    	}
	    	return ans;
	    }
	}
}
