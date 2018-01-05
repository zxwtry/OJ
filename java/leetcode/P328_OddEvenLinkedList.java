package leetcode;

import tools.ListNode辅助.ListNode;

/**
 * 	Given a singly linked list, group all odd nodes together followed by the even nodes.
 *  Please note here we are talking about the node number and not the value in the nodes.

 * 	You should try to do it in place. The program should run in O(1) space complexity
 * 	 and O(nodes) time complexity.
 * 	
 * 	Example:
 * 	Given 1->2->3->4->5->NULL,
 * 	return 1->3->5->2->4->NULL.
 * 	
 * 	Note:
 * 	The relative order inside both the even and odd groups should remain as it was 
 * 	in the input. 
 * 	The first node is considered odd, the second node even and so on ...
 */
/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P328_OddEvenLinkedList.java
 * @type        P328_OddEvenLinkedList
 * @date        2017年1月10日 下午10:00:45
 * @details     Solution1: AC 1ms 4.63%
 */
public class P328_OddEvenLinkedList {
	static class Solution1 {
	    public ListNode oddEvenList(ListNode head) {
	        if (head == null || head.next == null)
	            return head;
	        ListNode headEven = head;
	        ListNode headOdd = head.next;
	        ListNode nowEven = head;
	        ListNode nowOdd = head.next;
	        while (true) {
	            ListNode nextEven = nowOdd != null ? nowOdd.next : null;
	            ListNode nextOdd = nextEven != null ? nextEven.next : null;
                if (nextOdd == null && nextEven == null)
                    break;
                nowEven.next = nextEven;
                nowOdd.next = nextOdd;
	            nowEven = nextEven;
	            nowOdd = nextOdd;
	        }
	        nowEven.next = headOdd;
	        return headEven;
	    }
	}
}
