package leetcode;

/*
 * 	Write a program to find the node at which the intersection 
 * 	of two singly linked lists begins.


	For example, the following two linked lists:
	
	A:          a1 → a2
	                   	↘
	                     c1 → c2 → c3
	                   	↗            
	B:     b1 → b2 → b3
	begin to intersect at node c1.
	
	
	Notes:
	
	If the two linked lists have no intersection at all, return null.
	The linked lists must retain their original structure after
	 the function returns.
	You may assume there are no cycles anywhere 
	in the entire linked structure.
	Your code should preferably run in O(n) time and use only O(1) memory.
 */

import tools.ListNode辅助.ListNode;

public class P160_IntersectionofTwoLinkedLists {
	public static void main(String[] args) {
		int count = 0;
		for (int i = 0; i < 10000; i ++) {
			int lenA = (int) ( Math.random() * 1000 );
			int lenB = (int) ( Math.random() * 1000 );
			int lenC = (int) ( Math.random() * 1000 );
			ListNode headA = tools.ListNode辅助.A_一维生成器(tools.Random随机生成器.A_生成一个不重复随机数据(lenA, 0, lenA * 2));
			ListNode headB = tools.ListNode辅助.A_一维生成器(tools.Random随机生成器.A_生成一个不重复随机数据(lenB, 0, lenB * 2));
			ListNode headC = tools.ListNode辅助.A_一维生成器(tools.Random随机生成器.A_生成一个不重复随机数据(lenC, 0, lenC * 2));
			if (headA == null) {
				headA = headC;
			} else {
				ListNode tempA = headA;
				while (tempA.next != null) {
					tempA = tempA.next;
				}
				tempA.next = headC;
			}
			if (headB == null) {
				headB = headC;
			} else {
				ListNode tempB = headB;
				while (tempB.next != null) {
					tempB = tempB.next;
				}
				tempB.next = headC;
			}
			Solution s = new Solution();
			if ( s.getIntersectionNode(headA, headB) != headC ) {
				count ++;
			}
		}
		System.out.println(count);
	}
	/*
	 * 	2 ms
	 * 	37.10%
	 */
	static class Solution {
	    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
	    	if (headA == null || headB == null) {
	    		return null;
	    	}
	    	ListNode tempA = headA, tempB = headB;
	    	int countA = 0, countB = 0;
	    	while (tempA.next != null) {
	    		countA ++;
	    		tempA = tempA.next;
	    	}
	    	while (tempB.next != null) {
	    		countB ++;
	    		tempB = tempB.next;
	    	}
	    	if (tempA != tempB) {
	    		return null;
	    	}
	    	// tempA 长于 tempB
	    	if (countA > countB) {
	    		tempA = headA;
	    		tempB = headB;
	    	} else {
	    		tempA = headB;
	    		tempB = headA;
	    	}
	    	for (int i = Math.abs(countA - countB); i > 0; i --) {
	    		tempA = tempA.next;
	    	}
	    	while (tempA != tempB) {
	    		tempA = tempA.next;
	    		tempB = tempB.next;
	    	}
	    	return tempA;
	    }
	}
}
