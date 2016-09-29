package leetcode;


import tools.ListNode辅助.ListNode;

/*
 * 	Given a linked list, determine if it has a cycle in it.
	
	Follow up:
	Can you solve it without using extra space?
 */

public class P141_LinkedListCycle {
	public static void main(String[] args) {
		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {1, 2, 3, 4, 5});
		ListNode tail = head;
		ListNode head_next = head.next;
		while (tail.next != null) {
			tail = tail.next;
		}
		tail.next = head_next;
		Solution s = new Solution();
		System.out.println(s.hasCycle(head));
	}
	/*
	 * 	1 ms
	 * 	9.58%
	 */
	static class Solution {
	    public boolean hasCycle(ListNode head) {
	    	if (head == null) {
	    		return false;
	    	}
	    	ListNode speed_1 = head.next;
	    	ListNode speed_2 = speed_1 == null ? null : speed_1.next;
	    	while (speed_1 != null && speed_2 != null) {
	    		if (speed_1 == speed_2) {
	    			return true;
	    		}
	    		speed_1 = speed_1.next;
	    		speed_2 = speed_2.next == null ? null : speed_2.next.next;
	    	}
	        return false;
	    }
	}
}
