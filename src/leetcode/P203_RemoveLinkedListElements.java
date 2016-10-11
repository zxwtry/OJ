package leetcode;

import tools.ListNode辅助.ListNode;

public class P203_RemoveLinkedListElements {
	public static void main(String[] args) {
		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {
			6,2,3,4,5,6,7,7,6,5,43,3,6
		});
		Solution s = new Solution();
		ListNode ans = s.removeElements(head, 6);
		tools.ListNode辅助.B_打印链表(ans);
	}
	/*
	 * 	1 ms
	 * 	48.64%
	 */
	static class Solution {
	    public ListNode removeElements(ListNode head, int val) {
	    	while (head != null && head.val == val) {
	    		head = head.next;
	    	}
	    	if (head == null) {
	    		return null;
	    	}
	    	ListNode travel = head.next, travelParent = head;
	    	while(travel != null) {
	    		if (travel.val == val) {
	    			travelParent.next = travel.next;
	    			travel = travel.next;
	    		} else {
	    			travelParent = travel;
	    			travel = travel.next;
	    		}
	    	}
	        return head;
	    }
	}
}
