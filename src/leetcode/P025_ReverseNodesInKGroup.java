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
    public static void main(String[] args) {
        ListNode h = tools.ListNode辅助.A_一维生成器(new int[] {
                1, 2, 3
        });
        int k = 4;
        ListNode ans = new Solution1().reverseKGroup(h, k);
        tools.ListNode辅助.B_打印链表(ans, 100, 4);
    }
	static class Solution1 {
	    public ListNode reverseKGroup(ListNode h, int k) {
	        ListNode ans = null;
	        ListNode a = null, b = null, t = h;
	        ListNode p = null;
	        while (true) {
	            int i = 0; 
	            for (; i < k && t != null; i ++) {
	                b = t.next;
	                
	                t.next = a;
	                if (a != null) a.next = b;
	                if (p != null) p.next = t;
	                
	                t = b;
	            }
	            
	            if (p != null && ans == null) ans = a;
	            p = b;
	            if (t == null) break;
	        }
//	        if (a != null) a.next = null;
	        return ans;
	    }
	}
}
