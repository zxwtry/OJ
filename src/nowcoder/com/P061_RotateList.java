package nowcoder.com;

import tools.ListNode辅助.*;

/*
 * 	Given a list, rotate the list to the right by k places, 
 * 	where k is non-negative.

	For example:
	Given 1->2->3->4->5->NULL and k = 2,
	return 4->5->1->2->3->NULL.
 */

public class P061_RotateList {
	public static void main(String[] args) {
		ListNode input = tools.ListNode辅助.A_一维生成器(new int[] {1});
		ListNode ans = new Solution().rotateRight(input,0);
		tools.ListNode辅助.B_打印链表(ans);
	}
	/*
	 * 	1 ms
	 * 	9.52%
	 * 	一次AC，好爽!!!
	 */
	static class Solution {
	    public ListNode rotateRight(ListNode head, int k) {
	    	if (head == null)
	    		return null;
	        int len = 1;
	        ListNode cur = head, pre = null, last = head;
	        while (last.next != null) {
	        	len ++;
	        	last = last.next;
	        };
	        k = k % len;
	        if (k == 0)
	        	return head;
	        cur = head;	pre = head;
	        for (int i = -1; i != k; i ++)
	        	cur = cur.next;
//	        System.out.println("cur: " + cur.val);
	        while (cur != null) {
	        	cur = cur.next;
	        	pre = pre.next;
	        }
//	        System.out.println("pre: " + pre.val);
	        cur = pre.next;
	        last.next = head;
	        pre.next = null;
	        return cur;
	    }
	}
}
