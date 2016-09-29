package leetcode;


import tools.ListNode辅助.ListNode;

/*
 * 	Given a linked list, return the node where the cycle begins. 
 * 	If there is no cycle, return null.
	
	Note: Do not modify the linked list.
	
	Follow up:
	Can you solve it without using extra space?
 */

public class P142_LinkedListCycleII {
	public static void main(String[] args) {
		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {
				0, 1, 2, 3, 4, 5, 6,
				7, 8, 9, 10, 11, 12});
//		ListNode tra = head;
//		int rukou_val = 12, tail_val = 12;
//		ListNode rukou = null, tail = null;
//		while (tra != null ) {
//			if (tra.val == rukou_val) {
//				rukou = tra;
//			}
//			if (tra.val == tail_val) {
//				tail = tra;
//				break;
//			}
//			tra = tra.next;
//		}
//		tail.next = rukou;
		Solution s = new Solution();
		ListNode ans = s.detectCycle(head);
		System.out.println(ans == null ? -1 : ans.val);
	}
	/*
	 * 	2 ms
	 * 	10.37%
	 */
	static class Solution {
	    public ListNode detectCycle(ListNode head) {
	    	if (head == null) {
	    		return null;
	    	}
	    	ListNode speed_1 = head.next;
	    	ListNode speed_2 = speed_1 == null ? null : speed_1.next;
	    	while (speed_1 != null && speed_2 != null) {
	    		if (speed_1 == speed_2) {
	    			break;
	    		}
	    		speed_1 = speed_1.next;
	    		speed_2 = speed_2.next == null ? null : speed_2.next.next;
	    	}
	    	if (speed_1 == null || speed_2 == null) {
	    		return null;
	    	}
	    	speed_1 = head;
	    	while (speed_1 != speed_2) {
	    		speed_1 = speed_1.next;
	    		speed_2 = speed_2.next;
	    	}
	        return speed_1;
	    }
	}
}
