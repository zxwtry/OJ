package leetcode;

import tools.ListNode辅助.*;

/*
 * 	Given a linked list and a value x, partition it such that all nodes less
 *  than x come before nodes greater than or equal to x.

	You should preserve the original relative order of the nodes in each of
	 the two partitions.
	
	For example,
	Given 1->4->3->2->5->2 and x = 3,
	return 1->2->2->4->3->5.
 */

public class P086_PartitionList {
	public static void main(String[] args) {
		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {1, 4, 3, 2, 5, 2});
//		head = tools.ListNode辅助.A_一维生成器(new int[] {6, 4, 4, 2, 5, 2});
//		head = null;
//		head = tools.ListNode辅助.A_一维生成器(new int[] {6});
//		head = tools.ListNode辅助.A_一维生成器(new int[] {1, 1, 1, 1, 1});
//		head = tools.ListNode辅助.A_一维生成器(new int[] {1, 2, 3});
//		head = tools.ListNode辅助.A_一维生成器(new int[] {3, 1, 2});
		ListNode ans = new Solution().partition(head, 3);
		tools.ListNode辅助.B_打印链表(ans);
	}
	/*
	 * 	昨天一天没有弄清楚，其实一个循环完成一个功能才是上上策。
	 * 	AC
	 * 	1 ms
	 */
	static class Solution {
	    public ListNode partition(ListNode head, int x) {
	    	ListNode first_big = head, first_small = head;
	    	int first_big_index = 0, first_small_index = 0;
	    	while (first_big != null) {
	    		if (first_big.val >= x) {
	    			break;
	    		} else {
	    			first_big = first_big.next;
	    			first_big_index ++;
	    		}
	    	}
	    	while (first_small != null) {
	    		if (first_small.val < x) {
	    			break;
	    		} else {
	    			first_small = first_small.next;
	    			first_small_index ++;
	    		}
	    	}
	    	if (first_big == null || first_small == null) {
	    		return head;
	    	}
	    	ListNode ans = null, last_small = null, cur = null, cur_pre = null;
	    	if (first_big_index > first_small_index) {
	    		ans = first_small;
	    		last_small = first_small;
	    		while (last_small.next.val < x) {
	    			last_small = last_small.next;
	    		}
	    		cur = first_big.next;
	    		cur_pre = first_big;
	    		while (cur != null) {
	    			if (cur.val < x) {
	    				break;
	    			} else {
	    				cur_pre = cur;
	    				cur = cur.next;
	    			}
	    		}
	    		if (cur == null) {
	    			return ans;
	    		}
	    	} else if (first_big_index < first_small_index) {
	    		ans = first_small;
	    		last_small = first_small;
	    		ListNode temp = last_small.next;
	    		while (temp != null) {
	    			if (temp.val >= x) {
	    				break;
	    			} else {
	    				last_small = temp;
	    				temp = temp.next;
	    			}
	    		}
	    		ListNode last_big = first_big;
	    		while(last_big.next != first_small) { 
	    			last_big = last_big.next;
	    		}
	    		last_big.next = last_small.next;
	    		last_small.next = first_big;
	    		cur = last_big.next;
	    		cur_pre = last_big;
	    	} else {
	    		return head;
	    	}
	    	while (cur != null) {
	    		if (cur.val >= x) {
	    			cur_pre = cur;
	    			cur = cur.next;
	    		} else {
	    			ListNode save_cur = cur.next;
	    			cur.next = first_big;
	    			last_small.next = cur;
	    			last_small = cur;
	    			cur_pre.next = save_cur;
	    			cur = save_cur;
	    		}
	    	}
	    	return ans;
	    }
	}
}