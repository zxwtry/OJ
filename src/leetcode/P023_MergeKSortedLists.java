package leetcode;

public class P023_MergeKSortedLists {
	public static void main(String[] args) {
		ListNode[] input = C_二维生成器(new int[][] {
			{1,1,2,3,4},
			{},
			{2,2,2}
		});
		ListNode ans = new Solution4().mergeKLists(input);
		B_打印链表(ans);
//		ans = new Solution3().mergeKLists(new ListNode[] {
//				new ListNode(2)
//		});
//		B_打印链表(ans);
	}
	/*
	 * 	TLE
	 */
	static class Solution1 {
	    public ListNode mergeKLists(ListNode[] lists) {
	    	if (lists == null || lists.length == 0)
	    		return null;
	    	int num_null = 0;
	    	for (int i = 0; i != lists.length; i ++)
	    		if (lists[i] == null)
	    			num_null ++;
	    	if (num_null == lists.length)
	    		return null;
	    	if (num_null != 0) {
		    	ListNode[] tmp = new ListNode[lists.length - num_null];
		    	num_null = 0;
		    	for (int i = 0; i != lists.length; i ++)
		    		if (lists[i] != null)
		    			tmp[num_null ++] = lists[i];
		    	lists = tmp;
	    	}
	    	int i = 0, mini = -1;
	    	moveMinTo0(lists);
	    	ListNode[] cur = new ListNode[lists.length];
	    	cur[0] = lists[0].next;
	    	for (i = 1; i != cur.length; i ++)
	    		cur[i] = lists[i];
	    	ListNode pre = lists[0];
	    	while (true) {
	    		mini = -1;
	    		for (i = 0; i != cur.length; i ++) {
	    			if (cur[i] == null)
	    				continue;
	    			if (mini == -1 || cur[mini].val > cur[i].val)
	    				mini = i;
	    		}
	    		if (mini == -1) {
	    			break;
	    		} else if (mini == 0) {
	    			pre = cur[mini];
	    			cur[mini] = cur[mini].next;
	    		} else {
	    			ListNode temp = cur[mini].next;
	    		 	cur[mini].next = pre.next;
	    		 	pre.next = cur[mini];
	    		 	pre = cur[mini];
	    			cur[mini] = temp;
	    		}
	    		
	    	}
	        return lists[0];
	    }
		private void moveMinTo0(ListNode[] lists) {
			int mini = 0;
			for (int i = lists.length - 1; i != 0; i --)
				if (lists[mini].val > lists[i].val)
					mini = i;
			if (mini != 0) {
				ListNode temp = lists[0];
				lists[0] = lists[mini];
				lists[mini] = temp;
			}
		}
	}
	/*
	 * 	省点时间吧
	 * 	还是TLE
	 */
	static class Solution2 {
	    public ListNode mergeKLists(ListNode[] lists) {
	    	if (lists == null || lists.length == 0)
	    		return null;
	    	int i = 0, mini = -1;
	    	boolean isAllNull = lists[0] == null;
			for (i = lists.length - 1, mini = 0; i != 0; i --) {
				if (lists[mini] == null) {
					if (lists[i] != null) {
						mini = i;
						isAllNull = false;
					}
				} else {
					isAllNull = false;
					if (lists[i] != null && lists[mini].val > lists[i].val)
						mini = i;
				}
			}
			if (isAllNull)
				return null;
			if (mini != 0) {
				ListNode temp = lists[0];
				lists[0] = lists[mini];
				lists[mini] = temp;
			}
	    	ListNode[] cur = new ListNode[lists.length];
	    	cur[0] = lists[0].next;
	    	for (i = 1; i != cur.length; i ++)
	    		cur[i] = lists[i];
	    	ListNode pre = lists[0];
	    	while (true) {
	    		mini = -1;
	    		for (i = 0; i != cur.length; i ++) {
	    			if (cur[i] == null)
	    				continue;
	    			if (mini == -1 || cur[mini].val > cur[i].val)
	    				mini = i;
	    		}
	    		if (mini == -1) {
	    			break;
	    		} else if (mini == 0) {
	    			pre = cur[mini];
	    			cur[mini] = cur[mini].next;
	    		} else {
	    			ListNode temp = cur[mini].next;
	    		 	cur[mini].next = pre.next;
	    		 	pre.next = cur[mini];
	    		 	pre = cur[mini];
	    			cur[mini] = temp;
	    		}
	    		
	    	}
	        return lists[0];
	    }
	}
	/*
	 * 	1 2方法太垃圾了，
	 * 	改用两次合并法
	 * 	7ms 
	 * 	62.23%
	 * 	还可以做很多改进
	 */
	static class Solution3 {
	    public ListNode mergeKLists(ListNode[] lists) {
	    	if (lists == null || lists.length == 0)
	    		return null;
		    int i = 0, j = 0, len = lists.length, iend = (len + 1) >>> 1;
		    while (iend != len) {
		    	for (i = 0; i != iend; i ++) {
		    		j = len - 1 - i;
		    		if (i == j || lists[j] == null)
		    			continue;
		    		if (lists[i] == null) {
		    			lists[i] = lists[j];
		    			continue;
		    		}
		    		if (lists[i].val > lists[j].val) {
		    			ListNode temp = lists[i];
		    			lists[i] = lists[j];
		    			lists[j] = temp;
		    		}
		    		mergeTwoLists(lists[i], lists[j]);
		    	}
		    	len = iend;   iend = (len + 1) >>> 1;
		    }
		    return lists[0];
	    }
	    private void mergeTwoLists(ListNode c1, ListNode c2) {
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
	    }
	}
	/*
	 * 	更换更加高效的两链表合并算法
	 * 	5ms
	 * 	65.95%
	 */
	static class Solution4 {
	    public ListNode mergeKLists(ListNode[] lists) {
	    	if (lists == null || lists.length == 0)
	    		return null;
		    int i = 0, j = 0, len = lists.length, iend = (len + 1) >>> 1;
		    while (iend != len) {
		    	for (i = 0; i != iend; i ++) {
		    		j = len - 1 - i;
		    		if (i == j || lists[j] == null)
		    			continue;
		    		if (lists[i] == null) {
		    			lists[i] = lists[j];
		    			continue;
		    		}
		    		if (lists[i].val > lists[j].val) {
		    			ListNode temp = lists[i];
		    			lists[i] = lists[j];
		    			lists[j] = temp;
		    		}
		    		mergeTwoLists(lists[i], lists[j]);
		    	}
		    	len = iend;   iend = (len + 1) >>> 1;
		    }
		    return lists[0];
	    }
	    private void mergeTwoLists(ListNode c1, ListNode c2) {
	    	ListNode pre = c1; c1 = c1.next;
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
	    }
	}
	static class ListNode {
		int val;
		ListNode next;
		public ListNode(int val) {
			this.val = val;
		}
	}
	static ListNode[] C_二维生成器(int[][] nums) {
		ListNode[] ans = new ListNode[nums.length];
		for (int j = 0; j != nums.length; j ++) {
			ListNode pre = null, head = null;
			for (int i = nums[j].length - 1; i != -1; i --) {
				head = new ListNode(nums[j][i]);
				head.next = pre;
				pre = head;
			}
			ans[j] = head;
		}
		return ans;
	}
	static void B_打印链表(ListNode head) {
		ListNode cur = head;
		while (cur != null) {
			System.out.println(cur.val);
			cur = cur.next;
		}
	}
}
