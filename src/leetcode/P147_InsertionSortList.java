package leetcode;

import tools.ListNode辅助.ListNode;

public class P147_InsertionSortList {
	public static void main(String[] args) {
		Solution s = new Solution();
		ListNode head = tools.ListNode辅助.A_一维生成器(tools.Random随机生成器.A_生成一个随机数据(3, 0, 90));
//		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {2, 1});
		ListNode ans = s.insertionSortList(head);
		tools.ListNode辅助.B_打印链表(ans);
	}
	/*
	 * 	44 ms
	 * 	22.41%
	 */
	static class Solution {
	    public ListNode insertionSortList(ListNode head) {
	    	if (head == null) {
	    		return head;
	    	}
	    	ListNode list1 = head.next, list1_next = null, sorted_last = head;
	    	ListNode travel_pre = null, travel = null;
	    	head.next = null;
	    	/*
	    	 * 	排好序的第一个是head，最后一个是sorted_last
	    	 */
	    	while (list1 != null) {
	    		list1_next = list1.next;
				list1.next = null;
	    		travel = head;
	    		travel_pre = null;
	    		while (true) {
	    			if (travel.val > list1.val) {
	    				if (travel_pre == null) {
	    					list1.next = travel;
	    					head = list1;
	    				} else {
	    					list1.next = travel;
	    					travel_pre.next = list1;
	    				}
	    				break;
	    			} else {
	    				travel_pre = travel;
	    			}
	    			if (travel == sorted_last) {
	    				sorted_last.next = list1;
	    				sorted_last = list1;
	    				break;
	    			}
	    			travel = travel.next;
	    		}
	    		list1 = list1_next;
	    	}
	        return head;
	    }
	}
}
