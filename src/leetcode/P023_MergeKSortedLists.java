package leetcode;


public class P023_MergeKSortedLists {
	public static void main(String[] args) {
		ListNode ans = new Solution1().mergeKLists(C_二维生成器(new int[][] {
			{1, 4, 7},
			{2, 5, 8}
		}));
		B_打印链表(ans);
		ans = new Solution1().mergeKLists(new ListNode[] {
				null,
				null
		});
		B_打印链表(ans);
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
