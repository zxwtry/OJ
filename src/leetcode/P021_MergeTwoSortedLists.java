package leetcode;

import tools.ListNode辅助.ListNode;

public class P021_MergeTwoSortedLists {
	public static void main(String[] args) {
		ListNode node1 = new ListNode(1);
		ListNode node2 = new ListNode(2);
		ListNode node3 = new ListNode(3);
		ListNode node4 = new ListNode(4);
		ListNode node5 = new ListNode(5);
		ListNode node6 = new ListNode(6);
		ListNode node7 = new ListNode(7);
		ListNode node8 = new ListNode(8);
		node1.next = node3;
		node3.next = node5;
		node5.next = node7;
		node2.next = node4;
		node4.next = node6;
		node6.next = node8;
//		ListNode ans = new Solution().mergeTwoLists(node1, node2);
		
//		node1.next = node6;
//		node6.next = node7;
//		node7.next = node8;
//		node2.next = node3;
//		node3.next = node4;
//		node4.next = node5;
		
//		node1.next = node2;
//		node2.next = node3;
//		node3.next = node4;
//		node5.next = node6;
//		node6.next = node7;
//		node7.next = node8;
		
//		node2.next = node3;
//		node3.next = node4;
//		node4.next = node5;
//		node5.next = node6;
//		node6.next = node7;
//		node7.next = node8;
		
//		node1.next = node2;
//		node2.next = node4;
		
//		node1.next = node2;
//		node2.next = node4;
//		node4.next = node5;
		
//		ListNode node1_ghost = new ListNode(1);
//		ListNode node6_ghost = new ListNode(6);
//		ListNode node3_ghost = new ListNode(3);
//		
//		node1.next = node2;
//		node2.next = node3;
//		node3.next = node6;
//		
//		node1_ghost.next = node3_ghost;
//		node3_ghost.next = node6_ghost;
		
		
		node1 = tools.ListNode辅助.A_一维生成器(new int[] {1, 3, 5, 7});
		node2 = tools.ListNode辅助.A_一维生成器(new int[] {2, 4, 6, 8});
		
		ListNode ans = new Solution3().mergeTwoLists(node1, node2);
		while (ans != null) {
			System.out.println(ans.val);
			ans = ans.next;
		}
	}
	// 注意，最好不要新建ListNode
	/*
	 * 	1ms
	 * 	10.81%
	 */
	static class Solution1 {
	    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	        if (l1 == null)
	        	return l2;
	        else if (l2 == null)
	        	return l1;
	        if (l1.val > l2.val)
	        	return mergeTwoLists(l2, l1);
	        // 将l2拆调，返回l1
	        ListNode c1 = l1, c2 = l2;
	        while (c1.next != null && c2.next != null) {
	        	if (c1.val <= c2.val) {
	        		if (c1.next.val >= c2.val) {
		        		ListNode temp = c2.next;
		        		c2.next = c1.next;
		        		c1.next = c2;
		        		c2 = temp;
	        		} else {
	        			c1 = c1.next;
	        		}
	        	} else {
	        		if (c2.next.val >= c1.val) {
		        		ListNode temp = c1.next;
		        		c1.next = c2.next;
		        		c2.next = c1;
		        		c1 = temp;
	        		} else {
	        			c2 = c2.next;
	        		}
	        	}
	        }
	        if (c1.next == null) {
	        	c1.next = c2;
	        } else {
		        while (c1.next != null) {
		        	if (c1.next.val > c2.val)
		        		break;
		        	c1 = c1.next;
		        }
		        if (c2 != null) {
		        	if (c1.next != null) {
		        		c2.next = c1.next;
		        	}
		        	c1.next = c2;
		        }
	        }
	        return l1;
	    }
	}
	/*
	 * 	1的代码还是太麻烦了
	 * 	肯定有更加简单的写法
	 * 	23行，完美！
	 * 	1ms
	 * 	10.81%
	 */
	static class Solution2 {
	    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	    	if (l1 == null || l2 == null)
	    		return l1 == null ? l2 : l1;
	    	if (l1.val > l2.val)
	    		return mergeTwoLists(l2, l1);
	    	ListNode pre = l1, c1 = l1.next, c2 = l2;
	    	while (c2 != null) {
	    		while (c1 != null && c1.val <= c2.val) {
	    			pre = c1;
	    			c1 = c1.next;
	    		}
	    		if (c1 == null) {
	    			pre.next = c2;
	    			break;
	    		}
	    		pre.next = c2;
	    		while (c2 != null && c2.val <= c1.val) {
	    			pre = c2;
	    			c2 = c2.next;
	    		}
	    		pre.next = c1;
	    	}
	    	return l1;
	    }
	}
	/*
	 * 	代码可以更加简单
	 * 	25行，逻辑清晰
	 * 	1ms
	 * 	10.81%
	 */
	static class Solution3 {
	    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
	    	if (l1 == null || l2 == null)
	    		return l1 == null ? l2 : l1;
	    	if (l1.val > l2.val)
	    		return mergeTwoLists(l2, l1);
	    	ListNode pre = l1, c1 = l1.next, c2 = l2;
	    	while (true) {
	    		while (c1 != null && c1.val <= c2.val) {
	    			pre = c1;
	    			c1 = c1.next;
	    		}
	    		pre.next = c2;
	    		if (c1 == null)
	    			break;
	    		while (c2 != null && c2.val <= c1.val) {
	    			pre = c2;
	    			c2 = c2.next;
	    		}
	    		pre.next = c1;
	    		if (c2 == null)
	    			break;
	    	}
	    	return l1;
	    }
	}
}