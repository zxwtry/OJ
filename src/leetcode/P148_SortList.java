package leetcode;

import tools.ListNode辅助.ListNode;

/*
 * 	Sort a linked list in O(n log n) time using constant space complexity.
 * 	排序啊，时间O(n log n) 空间 O(1)
 * 	只能快排和堆排，但是这两种排序都是需要对数据随机访问的啊啊啊啊啊
 * 	好久没有写归并排序了
 */

public class P148_SortList {
	public static void main(String[] args) {
//		ListNode head = tools.ListNode辅助.A_一维生成器(tools.Random随机生成器.A_生成一个随机数据(10, 0, 900));
//		ListNode head = tools.ListNode辅助.A_一维生成器_从0_N_1(9);
//		ListNode l1 = tools.ListNode辅助.A_一维生成器_从0_N_1(5);
//		ListNode l2 = tools.ListNode辅助.A_一维生成器_从0_N_1(5);
//		ListNode l1 = new ListNode(1);
//		ListNode l2 = new ListNode(0);
//		Solution s = new Solution();
//		ListNode ans = s.mergeTwoSortedList(l1, 1, l2, 1);
//		tools.ListNode辅助.B_打印链表_一行(ans, 100);
//		s.generateMergedList(head);
//		ListNode ans = s.sortList(head);
//		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {9, 0, 8, 1, 7, 2, 6, 3, 5, 4});
//		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {9, 0, 8, 1});
		ListNode head = tools.ListNode辅助.A_一维生成器(
//				tools.Random随机生成器.A_生成一个随机数据(3, 0, 900)
				new int[] {1, 1, 1, 1, 1}
				);
		Solution s = new Solution();
//		ListNode head = tools.ListNode辅助.A_一维生成器(new int[] {2});
//		s.generateMergedList(head);
		ListNode ans = s.sortList(head);
		tools.ListNode辅助.B_打印链表_一行(ans, 100);
		
		int[] arr = tools.Random随机生成器.A_生成一个随机数据(100, 0, 200);
		System.out.println(tools.Utils.LEETCODE_int_array_序列化_(arr));
	}
	/*
	 * 	16 ms
	 * 	2.91%
	 */
	static class Solution {
		ListNode head1 = null, head2 = null;
	    int count = 1; //第count次打印ListNode
		public ListNode sortList(ListNode head) {
	    	/*
	    	 * 	找到最小值，并将对应ListNode设置成为head
	    	 */
//			System.out.printf("第%d次打印\t", count ++);
//	    	tools.ListNode辅助.B_打印链表_一行(head, 100);
//	    	head = findMinValAndMoveToHead(head);
//			System.out.printf("第%d次打印\t", count ++);
//	    	tools.ListNode辅助.B_打印链表_一行(head, 100);
//	    	head.next = generateMergedList(head.next);
	    	return generateMergedList(head);
	    }
	    public ListNode generateMergedList(ListNode head) {
	    	/*
	    	 * 	只是简单将list分成两段
	    	 * 	时刻需要注意两段的长度
	    	 * 	如果len是偶数 len1 = len2
	    	 * 	如果len是奇数 len1 = len2 - 1
	    	 */
	    	ListNode speed_1 = head, speed_1_save = null;
	    	ListNode speed_2 = head.next;
	    	int len1 = 0, lenSum = 1;
	    	while (speed_2 != null) {
	    		len1 ++;
	    		speed_1_save = speed_1;
	    		speed_1 = speed_1.next;
	    		lenSum += speed_2.next == null ? 1 : 2;
	    		speed_2 = speed_2.next == null ? null : speed_2.next.next;
	    	}
	    	
	    	if (speed_1_save !=  null) {
	    		speed_1_save.next = null;
	    	}
	    	
	    	if (lenSum == 1) {
	    		speed_1 = null;
	    	}
	    	
	    	return mergeTwoSortedList(head, len1, speed_1, lenSum - len1, false);
	    	
//	    	System.out.println((speed_1_save == null ? -1 : speed_1_save.val)  + "..." + (speed_1 == null ? -1 : speed_1.val));
//	    	System.out.println(len1 + "..." + lenSum);
		}
	    
	    public ListNode mergeTwoSortedList(ListNode l1, int len1, ListNode l2, int len2, boolean isMerge) {
	    	if ((len1 > 1 || len2 > 1) && ! isMerge) {
	    		return mergeTwoSortedList(generateMergedList(l1), len1, generateMergedList(l2), len2, true);
	    	}
	    	if (l1 == null || l2 == null) {
	    		return l1 == null ? l2 : l1;
	    	}
	    	ListNode ans = null;
	    	if (l1.val < l2.val) {
	    		ans = l1;
	    		l1 = l1.next;
	    	} else {
	    		ans = l2;
	    		l2 = l2.next;
	    	}
	    	ListNode travel = ans;
	    	while (l1 != null && l2 != null) {
	    		if (l1.val < l2.val) {
	    			travel.next = l1;
	    			l1 = l1.next;
	    		} else {
	    			travel.next = l2;
	    			l2 = l2.next;
	    		}
	    		travel = travel.next;
	    	}
	    	if (l1 != null) {
	    		travel.next = l1;
	    	}
	    	if (l2 != null) {
	    		travel.next = l2;
	    	}
	    	return ans;
	    }
		
	    public void findMiddle(ListNode head) {
	    	ListNode speed_1 = head, speed_1_save = null;
	    	ListNode speed_2 = head.next;
	    	while (speed_2 != null) {
	    		speed_1_save = speed_1;
	    		speed_1 = speed_1.next;
	    		speed_2 = speed_2.next == null ? null : speed_2.next.next;
	    	}
	    	System.out.println((speed_1_save == null ? -1 : speed_1_save.val) + "..." + speed_1.val);
	    }
	    
 		public ListNode findMinValAndMoveToHead(ListNode head) {
			ListNode min_node = null, min_node_pre = null;
	    	ListNode travel = head, travel_pre = null;
	    	while (travel != null) {
	    		if (min_node == null || travel.val < min_node.val) {
	    			min_node = travel;
	    			min_node_pre = travel_pre;
	    		}
	    		travel_pre = travel;
	    		travel = travel.next;
	    	}
	    	if (min_node_pre == null) {
	    		return head;
	    	} else {
	    		min_node_pre.next = min_node.next;
	    		min_node.next = head;
	    		head = min_node;
	    		return head;
	    	}
		}
	}
}
