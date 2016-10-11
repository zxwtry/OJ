package leetcode;
import tools.ListNode辅助.ListNode;

public class P206_ReverseLinkedList {
	public static void main(String[] args) {
		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {1, 2, 3, 4});
		Solution s = new Solution();
		ListNode ans = s.reverseList(head);
		tools.ListNode辅助.B_打印链表(ans);
	}
	/*
	 * 	0 ms
	 * 	31.90%
	 */
	static class Solution {
	    public ListNode reverseList(ListNode head) {
	    	if (head == null || head.next == null) {
	    		return head;
	    	}
	    	ListNode travel = head.next, travelSave = null;
	    	ListNode travelLast = head, travelLastSave = null;
	    	head.next = null;
	    	while (travel != null) {
	    		travelSave = travel.next;
	    		travelLastSave = travel;
	    		travel.next = travelLast;
	    		travel = travelSave;
	    		travelLast = travelLastSave;
	    	}
	        return travelLast;
	    }
	}
}