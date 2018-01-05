package leetcode;

import tools.ListNode辅助.ListNode;

/**
 * 	Write a function to delete a node (except the tail) in a singly linked list, 
 * 	given only access to that node.

	Supposed the linked list is 1 -> 2 -> 3 -> 4 and you are given the third node
	 with value 3, the linked list should become 1 -> 2 -> 4 after calling your function.
 */

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P237_DeleteNodeinaLinkedList.java
 * @type        P237_DeleteNodeinaLinkedList
 * @date        2016年12月10日 下午10:23:39
 * @details     Solution: AC 2ms 0.30%
 */
public class P237_DeleteNodeinaLinkedList {
	static class Solution {
	    public void deleteNode(ListNode node) {
	        ListNode n = node.next;
	        node.val = n.val;
	        node.next = n.next;
	    }
	}
}
