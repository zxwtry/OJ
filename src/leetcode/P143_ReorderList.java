package leetcode;

import tools.ListNode辅助.ListNode;

/*
 * 	Given a singly linked list L: L0→L1→…→Ln-1→Ln,
	reorder it to: L0→Ln→L1→Ln-1→L2→Ln-2→…
	
	You must do this in-place without altering the nodes' values.
	
	For example,
	Given {1,2,3,4}, reorder it to {1,4,2,3}.
 */

public class P143_ReorderList {
	public static void main(String[] args) {
//		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {0, 1, 2, 3, 4, 5});
		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {1, 2, 3, 4});
		Solution s = new Solution();
		s.reorderList(head);
//		ListNode ans = s.reverse(head);
		tools.ListNode辅助.B_打印链表(head);
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(100, 0, 900);
		System.out.println(tools.Utils.LEETCODE_int_array_序列化_(arr));
	}
	/*
	 * 	3 ms
	 * 	18.91%
	 */
	static class Solution {
	    public void reorderList(ListNode head) {
	    	if (head == null) {
	    		return;
	    	}
	        ListNode tra = head;
	        int len = 0;
	        while (tra != null) {
	        	tra = tra.next;
	        	len ++;
	        }
	        int second_break_move = (len + 1) / 2;
	        ListNode second_break = head;
	        ListNode second_break_pre = null;
	        int second_break_count = 0;
	        while (second_break_count != second_break_move) {
	        	second_break_pre = second_break;
	        	second_break = second_break.next;
	        	second_break_count ++;
	        }
	        second_break_pre.next = null;
	        ListNode second_break_reverse_head = reverse(second_break);
	        ListNode head1 = head.next, head1_save = null;
	        ListNode head2 = second_break_reverse_head, head2_save = null;
	        tra = head;
	        while (head1 != null) {
	        	head1_save = head1.next;
	        	head2_save = head2.next;
	        	tra.next = head2;
	        	tra = tra.next;
	        	tra.next = head1;
	        	tra = tra.next;
	        	head1 = head1_save;
	        	head2 = head2_save;
	        }
	        if (head2 != null) {
	        	tra.next = head2;
	        }
	    }
		public ListNode reverse(ListNode second_break) {
			if (second_break == null) {
				return second_break;
			}
			ListNode n1, n2, n3;
			n1 = second_break;
			n2 = n1.next;
			n1.next = null;
			while (n2 != null) {
				n3 = n2.next;
				n2.next = n1;
				n1 = n2;
				n2 = n3;
			}
			return n1;
		}
	}
}
