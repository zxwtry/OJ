package leetcode;

import java.util.ArrayList;
import java.util.Comparator;

import tools.ListNode辅助.ListNode;

/**
 * @author      zxwtry
 * @email       zxwtry@qq.com
 * @project     OJ
 * @package     leetcode
 * @file        P148_SortList.java
 * @date        2017年6月5日 下午8:39:40
 * @details     Solution1: AC 21ms 3.70%
 * @details     Solution2: AC 16ms 5.72%
 */
public class P148_SortList {
	static class Solution1 {
	    public ListNode sortList(ListNode head) {
	        if (head == null) return null;
	        ArrayList<ListNode> list = new ArrayList<>();
	        ListNode node = head;
	        while (node != null) {
	            list.add(node);
	            node = node.next;
	        }
	        list.sort(new Comparator<ListNode>() {
                @Override
                public int compare(ListNode o1, ListNode o2) {
                    if (o1.val > o2.val) return 1;
                    return o1.val < o2.val ? -1 : 0;
                }
            });
	        node = null;
	        //LinkedList.sort就是这么做的
	        for (int index = list.size() - 1; index > -1; index --) {
	            head = list.get(index);
	            head.next = node;
	            node = head;
	        }
	        return list.get(0);
	    }
	}
	static class Solution2 {
	    private int getListNodeLength(ListNode h) {
	        int len = 0;
	        while (h != null) {
	            h = h.next;
	            len ++;
	        }
	        return len;
	    }
	    public ListNode sortList(ListNode h) {
	        ListNode vh = new ListNode(0);
	        vh.next = h;
	        ListNode p = vh, ln = null, rn = null, nn = null;
	        boolean le, re;
	        int len = getListNodeLength(h);
	        for (int s = 1; s < len; s <<= 1) {
	            p = vh;
	            ln = vh.next;
	            rn = vh.next;
	            while (rn != null) {
    	            for (int si = 0; si < s; si ++)
    	                rn = rn == null ? null : rn.next;
    	            if (rn == null) {
    	                p.next = ln;
    	                break;
    	            }
    	            int lc = 0, rc = 0;
    	            while (lc < s || rc < s) {
    	                re = (rn == null || rc == s);
    	                le = (ln == null || lc == s);
    	                if (le || re) {
                            if (le && re) break;
                            if (re) {
                                p.next = ln;
                                p = ln;
                                ln = ln.next;
                                lc ++;
                            } else if (le) {
                                nn = rn.next;
                                p.next = rn;
                                p = rn;
                                rn = rn.next;
                                rc ++;
                            }
    	                } else {
                            if (ln.val < rn.val) {
                                p.next = ln;
                                p = ln;
                                ln = ln.next;
                                lc ++;
                            } else {
                                nn = rn.next;
                                p.next = rn;
                                p = rn;
                                rn = rn.next;
                                rc ++;
                            }
    	                }
    	            }
    	            p.next = null;
    	            ln = rn = nn;
	            }
	        }
	        return vh.next;
	    }
	}
}
